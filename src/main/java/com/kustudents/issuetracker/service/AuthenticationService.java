package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.entity.UserCredentials;
import com.kustudents.issuetracker.repository.UsersCredentialsRepository;
import com.kustudents.issuetracker.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final UsersRepository usersRepository;

    public void register(String login, String password, String firstName, String lastName) {
        if(usersCredentialsRepository.findUserByLogin(login).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this login already exist");
        }
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin(login);
        userCredentials.setPassword(passwordEncoder.encode(password));
        usersCredentialsRepository.save(userCredentials);

        UserCredentials newSessionUserCredentials = usersCredentialsRepository.findUserByLogin(userCredentials.getLogin()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "User credentials creation failed"));
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserCredentials(newSessionUserCredentials);

        usersRepository.save(user);
    }

}
