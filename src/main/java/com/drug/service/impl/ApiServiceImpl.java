package com.drug.service.impl;

import com.drug.model.entity.DrugEntity;
import com.drug.repository.jpa.DrugRepository;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private final DrugRepository drugRepository;

    @Autowired
    public ApiServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<DrugEntity> getDrugs(String name) {
        List<DrugEntity> drugEntities = drugRepository.getAll();
        return drugEntities;
    }
}
