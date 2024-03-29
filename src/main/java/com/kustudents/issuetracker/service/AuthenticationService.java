package com.kustudents.issuetracker.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kustudents.issuetracker.exceptions.ResourceNotFoundException;
import com.kustudents.issuetracker.model.AuthenticationRequest;
import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.entity.UserCredentials;
import com.kustudents.issuetracker.repository.UsersCredentialsRepository;
import com.kustudents.issuetracker.repository.UsersRepository;
import com.kustudents.issuetracker.utility.TokenConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.transaction.Transactional;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final UsersRepository usersRepository;
    private final int expirationInMinutes;
    private final JWTVerifier verifier;
    private final Algorithm algorithm;

    public AuthenticationService(@Value("${auth.secret}") String secret,
            @Value("${auth.expirationInMinutes}") int expirationInMinutes, UsersRepository usersRepository,
            UsersCredentialsRepository usersCredentialsRepository) {
        this.usersCredentialsRepository = usersCredentialsRepository;
        this.usersRepository = usersRepository;
        this.expirationInMinutes = expirationInMinutes;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).withIssuer("kustudents").build();
    }

    @Transactional
    public String changePasswordAndAuthenticate(AuthenticationRequest request, String newPassword) {
        UserCredentials userCredentials = tryAuthenticate(request);
        userCredentials.setPassword(passwordEncoder.encode(newPassword));
        userCredentials.setLastActive(LocalDateTime.now());
        usersCredentialsRepository.saveAndFlush(userCredentials);
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername(userCredentials.getLogin());
        authenticationRequest.setPassword(newPassword);
        return authenticate(authenticationRequest);
    }

    public String authenticate(AuthenticationRequest request) {
        UserCredentials userCredentials = tryAuthenticate(request);
        if (userCredentials.getIsExpired()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "User has been expired. Please contact system administrator");
        }
        if (userCredentials.getLastActive() == LocalDateTime.MIN) {
            throw new AuthorizationServiceException("Need to Change Password");
        } else {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userCredentials, null, null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            userCredentials.setLastActive(LocalDateTime.now());
            usersCredentialsRepository.save(userCredentials);
            return createNewJWT(userCredentials.getLogin());
        }
    }

    private UserCredentials tryAuthenticate(AuthenticationRequest request) {
        UserCredentials userEntity = usersCredentialsRepository.findUserByLogin(request.getLogin())
                .orElseThrow(supplyAuthorizationException());
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw supplyAuthorizationException().get();
        }
        return userEntity;
    }

    private Supplier<AuthorizationServiceException> supplyAuthorizationException() {
        return () -> new AuthorizationServiceException("Incorrect credentials");
    }

    private Date getExpiration() {
        LocalDateTime expirationDate = LocalDateTime.now().plus(expirationInMinutes, ChronoUnit.MINUTES);

        return java.util.Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void register(String login, String password, String firstName, String lastName,
            Boolean changePasswordOnLogin) {
        if (usersCredentialsRepository.findUserByLogin(login).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this login already exists");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(login);
        userCredentials.setPassword(passwordEncoder.encode(password));
        if (changePasswordOnLogin)
            userCredentials.setLastActive(LocalDateTime.MIN);
        usersCredentialsRepository.save(userCredentials);

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);

        usersRepository.save(user);
    }

    public DecodedJWT decodeToken(String token) {
        return verifier.verify(token);
    }

    public UserCredentials loadUserDetails(String login) {
        return usersCredentialsRepository.findUserByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("User by login not found = " + login));
    }

    public String overwriteExistingToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token.substring(TokenConstants.TOKEN_PREFIX.length()));
        Claim login = decodedJWT.getClaim("login");
        return createNewJWT(login.asString());
    }

    public String getLoginFromToken(String bearerlessToken) {
        DecodedJWT decodedJWT = decodeToken(bearerlessToken);
        Claim login = decodedJWT.getClaim("login");
        return login.asString();
    }

    private String createNewJWT(String login) {
        return JWT.create().withClaim("login", login)
                // .withClaim("importantInfo", "Example")
                .withIssuer("kustudents").withExpiresAt(getExpiration()).sign(algorithm);
    }

    public static String getLoggedInUserLogin() {
        return ((UserCredentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLogin();
    }

}
