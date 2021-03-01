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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
public class AuthenticationService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final UsersRepository usersRepository;
    private final int expirationInMinutes;
    private final JWTVerifier verifier;
    private final Algorithm algorithm;

    public AuthenticationService(@Value("${auth.secret}") String secret,
                                 @Value("${auth.jwtExpirationInMinutes:5}") int expirationInMinutes,
                                 UsersRepository usersRepository,
                                 UsersCredentialsRepository usersCredentialsRepository) {
        this.usersCredentialsRepository = usersCredentialsRepository;
        this.usersRepository = usersRepository;
        this.expirationInMinutes = expirationInMinutes;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).withIssuer("kustudents").build();
    }

    public String authenticate(AuthenticationRequest request) {
        tryAuthenticate(request);

        return JWT.create()
                .withClaim("login", request.getLogin())
                //.withClaim("importantInfo", "Spongebob")
                .withIssuer("kustudents")
                .withExpiresAt(getExpiration())
                .sign(algorithm);
    }

    private void tryAuthenticate(AuthenticationRequest request) {
        var userEntity = usersCredentialsRepository.findUserByLogin(request.getLogin())
                .orElseThrow(supplyAuthorizationException());

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw supplyAuthorizationException().get();
        }
    }

    private Supplier<AuthorizationServiceException> supplyAuthorizationException() {
        return () -> new AuthorizationServiceException("Incorrect credentials");
    }

    private Date getExpiration() {
        var expirationDate = LocalDateTime.now().plus(expirationInMinutes, ChronoUnit.MINUTES);

        return java.util.Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void register(String login, String password, String firstName, String lastName) {
        if(usersCredentialsRepository.findUserByLogin(login).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this login already exist");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(login);
        userCredentials.setPassword(passwordEncoder.encode(password));
        usersCredentialsRepository.save(userCredentials);

        UserCredentials newSessionUserCredentials = usersCredentialsRepository.findUserByLogin(userCredentials.getLogin()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User credentials creation failed"));

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserCredentials(newSessionUserCredentials);

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
        var decodedJWT = decodeToken(token.substring(TokenConstants.TOKEN_PREFIX.length()));
        Claim login = decodedJWT.getClaim("login");

        return JWT.create()
                .withClaim("login", login.toString())
                .withIssuer("kustudents")
                .withExpiresAt(getExpiration())
                .sign(algorithm);
    }

}
