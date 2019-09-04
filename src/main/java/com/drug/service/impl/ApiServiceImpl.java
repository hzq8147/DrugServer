package com.drug.service.impl;

import com.drug.model.entity.DrugEntity;
import com.drug.repository.jpa.DrugRepository;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {
    private final DrugRepository drugRepository;

    @Autowired
    public ApiServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<DrugEntity> getDrugs(String name) {
        if (name.length()>0){
            Optional<List<DrugEntity>> drugEntities = drugRepository.getByNameStartingWith(name);
            if (!drugEntities.isPresent()){
                throw new RuntimeException("error");
            }
            return drugEntities.get();
        }else{
            return getAllDrugs();
        }

    }
    //获取所有药品
    public List<DrugEntity> getAllDrugs() {
        List<DrugEntity> drugEntities = drugRepository.getAll();
        return drugEntities;
    }
}
