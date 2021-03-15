package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Importance;
import com.kustudents.issuetracker.repository.ImportanceRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportanceService {

    private final ImportanceRepository importanceRepo;

    public List<Importance> getImportanceList(){
        return importanceRepo.findAll(Sort.by("id"));
    }

    public Importance getImportanceByID(Long ID){
        return importanceRepo.getOne(ID);
    }

    //TODO: Implement
    public Page<Importance> getAllImportancesPaginatedAndFiltered(int page, int size, String orderBy, Boolean ascending){
        return Page.empty();
    }

    public void rewriteImportances(List<Importance> importances){
        importanceRepo.deleteAllInBatch();
        importanceRepo.saveAll(importances);
    }

    public Importance createImportance(Importance importance){
        return importanceRepo.save(importance);
    }
}
