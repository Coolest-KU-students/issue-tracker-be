package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.exceptions.ResourceNotFoundException;
import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.entity.UserCredentials;
import com.kustudents.issuetracker.model.entity.UserRead;
import com.kustudents.issuetracker.repository.UserReadRepository;
import com.kustudents.issuetracker.repository.UsersCredentialsRepository;
import com.kustudents.issuetracker.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReadRepository userReadRepository;
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final UsersRepository usersRepository;

    public UserRead getUserByLogin(String login) {
        return userReadRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(login + " does not exist"));
    }

    public Iterable<UserRead> getAllUsers() {
        return userReadRepository.findAll();
    }

    public void changeUserExpiration(String login) {
        UserCredentials userCredentials = getUserCredentials(login);
        userCredentials.setIsExpired(!userCredentials.getIsExpired());
        usersCredentialsRepository.save(userCredentials);
    }

    public UserCredentials getUserCredentials(String login) {
        return usersCredentialsRepository.findUserByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(login + " does not exist"));
    }

    public void updateUser(User user) {
        User userToUpdate = usersRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException(user.getLogin() + " does not exist"));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        usersRepository.save(userToUpdate);
    }
}