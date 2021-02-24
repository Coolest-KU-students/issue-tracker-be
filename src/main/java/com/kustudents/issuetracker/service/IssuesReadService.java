package com.kustudents.issuetracker.service;

import java.util.Optional;

import com.kustudents.issuetracker.model.entity.IssueRead;
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
        return issueReadRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Issue with id - %d, does not exist", id)));
    }

    public Iterable<IssueRead> getAllIssues() {
            return issueReadRepository.findAll();
    }


    public Page<IssueRead> getAllIssuesPaginatedAndFiltered(Boolean hideClosed
                                                            , Boolean showUserCreated
                                                            , Boolean showUserResponsible
                                                            , Integer page
                                                            , Integer size
                                                            , String orderBy
                                                            , Boolean ascending){
        Pageable pageRequest = PageRequest.of(page, size, ascending?Sort.by(orderBy).ascending():Sort.by(orderBy).descending());

        Optional<String> userCreated = Optional.empty();
        if(showUserCreated) userCreated = issueReadRepo.GetUserName();
        Optional<String> userResponsible = Optional.empty();
        if(showUserResponsible) userResponsible = issueReadRepo.GetUserName();
        return issueReadRepo.getAllIssuesPaginatedAndFiltered(hideClosed, userCreated.orElse(null), userResponsible.orElse(null), pageRequest);
    }

}
