package com.kustudents.issuetracker.controller;

import java.util.List;

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

    //Niekur nėra panaudotas:
//    @GetMapping("/data")
//    public Page<Importance> getPaginatedImportance(
//        @RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE) Integer page,
//        @RequestParam(defaultValue = DefaultPagination.DEFAULT_PAGE_SIZE) Integer size,
//        @RequestParam(defaultValue = "id") String orderBy,
//        @RequestParam(defaultValue = "1") Boolean ascending) {
//        return importanceService.getAllImportancesPaginatedAndFiltered(page,
//                                                                        size,
//                                                                        orderBy,
//                                                                        ascending);
//    }

    @GetMapping("/{id}")
    public Importance getImportanceById(@PathVariable("id") Long id) {
        return importanceService.getImportanceByID(id);
    }

    @PutMapping("/")
    public void updateImportanceList(@RequestBody List<Importance> importances){
        importanceService.rewriteImportances(importances);
    }

    @PostMapping("/new")
    public Importance createImportance(@RequestBody String importanceName){
        return importanceService.createImportance(importanceName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        importanceService.deleteImportanceById(id);
    }

}
