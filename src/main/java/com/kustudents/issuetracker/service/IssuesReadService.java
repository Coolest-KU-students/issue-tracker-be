package com.kustudents.issuetracker.service;

import com.kustudents.issuetracker.model.IssueRead;
import com.kustudents.issuetracker.repository.IssueReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class IssuesReadService {

    private final IssueReadRepository issueReadRepository;

    public IssueRead getIssueById(Long id) {
        return issueReadRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Issue with id - %d, does not exist", id)));
    }

    public Iterable<IssueRead> getAllIssues() {
        return issueReadRepository.findAll();
    }

    public Page<IssueRead> getAllIssuesPaginatedAndFiltered(Boolean hideClosed, Boolean showUserCreated,
            Boolean showUserResponsible, Integer page, Integer size, String orderBy, Boolean ascending) {
        Pageable pageRequest = PageRequest.of(page, size,
                ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());

        String userCreated = null;
        if (showUserCreated)
            userCreated = AuthenticationService.getLoggedInUserLogin();
        String userResponsible = null;
        if (showUserResponsible)
            userResponsible = AuthenticationService.getLoggedInUserLogin();
        return issueReadRepository.getAllIssuesPaginatedAndFiltered(hideClosed, userCreated, userResponsible,
                pageRequest);
    }

}
