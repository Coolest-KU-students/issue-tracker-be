package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.entity.Importance;
import com.kustudents.issuetracker.service.ImportanceService;
import com.kustudents.issuetracker.utility.DefaultPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/importances")
public class ImportanceController {

    private final ImportanceService importanceService;

    @GetMapping("/")
    public Iterable<Importance> getAll() {
        return importanceService.getImportanceList();
    }

    @GetMapping("/data")
    public Page<Importance> getPaginatedIssues(
        @RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE) Integer page,
        @RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE_SIZE) Integer size,
        @RequestParam(defaultValue = "id") String orderBy,
        @RequestParam(defaultValue = "1") Boolean ascending,
        @RequestParam(defaultValue = "1") Boolean hideClosed,
        @RequestParam(defaultValue = "0") Boolean showCreatedByUser,
        @RequestParam(defaultValue = "0") Boolean showIssuesWhereUserIsResponsible) {
        return importanceService.getAllImportancesPaginatedAndFiltered(page,
                                                                        size,
                                                                        orderBy,
                                                                        ascending);
    }

    @GetMapping("/{id}")
    public Importance getIssueById(@PathVariable("id") Long id) {
        return importanceService.getImportanceByID(id);
    }


}