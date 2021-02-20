package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.IssueRead;
import com.kustudents.issuetracker.repository.IssueReadRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class IssuesReadService {

    private final IssueReadRepository issueReadRepository;

    public IssueRead getIssueById(Long id) {
        return issueReadRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Issue with id - %d, does not exist", id)));
    }

    public List<IssueRead> getAllIssues() {
        return issueReadRepository.findAll();
    }

}
