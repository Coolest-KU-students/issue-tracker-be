package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.IssueRead;
import com.kustudents.issuetracker.repository.IssueReadRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class IssuesReadService {

    private final IssueReadRepo issueReadRepo;

    public IssueRead getIssueById(Long id) {
        return issueReadRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Issue with id - %d, does not exist", id)));
    }

    public List<IssueRead> getAllIssues() {
        return issueReadRepo.findAll();
    }

}
