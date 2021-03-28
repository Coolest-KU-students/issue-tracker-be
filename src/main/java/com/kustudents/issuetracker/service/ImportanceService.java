package com.kustudents.issuetracker.service;

import java.util.List;

import com.kustudents.issuetracker.model.entity.Importance;
import com.kustudents.issuetracker.repository.ImportanceRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ImportanceService {

    private final ImportanceRepository importanceRepo;

    public Importance createImportance(Importance importance){
        return importanceRepo.save(importance);
    }

    public Importance createImportance(String importanceName){
        Long importanceCount = importanceRepo.count();
        Importance importance = new Importance();
        importance.setName(importanceName);
        importance.setSortOrder(importanceCount+1);
        return importanceRepo.save(importance);
    }

    public List<Importance> getImportanceList(){
        return importanceRepo.findAll(Sort.by("sortOrder"));
    }

    public Importance getImportanceByID(Long ID){
        return importanceRepo.findById(ID).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "This importance does not exist"));
    }

    public void rewriteImportances(List<Importance> importances){
        importances.forEach((importance) ->{
            updateImportance(importance);
        });
    }

    public void deleteImportanceById(Long id){
        if(importanceRepo.count() == 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least 1 importance must remain");
        }
        Importance importanceToDelete = importanceRepo.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "This importance does not exist"));
        List<Importance> importances = importanceRepo.findAll();
        importances.removeIf(importance -> importance.getSortOrder().compareTo(importanceToDelete.getSortOrder())<=0);//Removes all Importances that have a lower Importance order
        importances.forEach(importance -> {importance.setSortOrder(importance.getSortOrder()-1);});//decreases the leftover sort orders by 1
        importanceRepo.delete(importanceToDelete);
        importanceRepo.saveAll(importances);
    }

    private void updateImportance(Importance importance){
        Importance importanceToUpdate = importanceRepo.findById(importance.getId()).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "This importance does not exist")); 
        importanceToUpdate.setName(importance.getName());
        importanceToUpdate.setSortOrder(importance.getSortOrder());
        importanceRepo.save(importanceToUpdate);
    }

    //TODO: Implement if needed
    public Page<Importance> getAllImportancesPaginatedAndFiltered(int page, int size, String orderBy, Boolean ascending){
        return Page.empty();
    }

}
