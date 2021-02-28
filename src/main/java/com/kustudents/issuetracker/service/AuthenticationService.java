package com.kustudents.issuetracker.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.kustudents.issuetracker.model.AuthenticationRequest;
import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.entity.UserCredentials;
import com.kustudents.issuetracker.repository.UsersCredentialsRepository;
import com.kustudents.issuetracker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

}

//public class AuthenticationService {
//
//    private final int expirationInMinutes;
//    private final JWTVerifier verifier;
//    private final Algorithm algorithm;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public AuthenticationService(
//            @Value("${auth.secret}") String secret,
//            @Value("${auth.jwtExpirationInMinutes:5}") int expirationInMinutes,
//            UserRepository userRepository,
//            PasswordEncoder passwordEncoder) {
//
//        this.expirationInMinutes = expirationInMinutes;
//        this.algorithm = Algorithm.HMAC256(secret);
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.verifier = JWT.require(algorithm).withIssuer("devbridge").build();
//    }
//
//    public String authenticate(AuthenticationRequest request) {
//        tryAuthenticate(request);
//
//        return JWT.create()
//                .withClaim("username", request.getUsername())
//                .withClaim("importantInfo", "Spongebob")
//                .withIssuer("devbridge")
//                .withExpiresAt(getExpiration())
//                .sign(algorithm);
//    }
//
//    public DecodedJWT decodeToken(String token) {
//        return verifier.verify(token);
//    }
//
//    public User loadUserDetails(String username) {
//        return userRepository.findByUsername(username)
//                .map(user -> new User(user.getUsername(), "", List.of(new SimpleGrantedAuthority(user.getRole().name())), user.getId()))
//                .orElseThrow(() -> new ResourceNotFoundException("User by username not found=" + username));
//    }
//
//    private void tryAuthenticate(AuthenticationRequest request) {
//        var userEntity = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(supplyAuthorizationException());
//
//        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
//            throw supplyAuthorizationException().get();
//        }
//    }
//
//    private Supplier<AuthorizationServiceException> supplyAuthorizationException() {
//        return () -> new AuthorizationServiceException("Incorrect credentials");
//    }
//
//    private Date getExpiration() {
//        var expirationDate = LocalDateTime.now().plus(expirationInMinutes, ChronoUnit.MINUTES);
//
//        return java.util.Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    public void register(AuthenticationRequest request) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
//        userEntity.setUsername(request.getUsername());
//        userEntity.setRole(UserRole.ROLE_USER);
//
//        userRepository.save(userEntity);
//    }
//}