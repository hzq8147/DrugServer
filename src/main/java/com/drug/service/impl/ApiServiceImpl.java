package com.drug.service.impl;

import com.drug.model.entity.DrugActionEntity;
import com.drug.model.entity.DrugEntity;
import com.drug.repository.jpa.DrugActionRepository;
import com.drug.repository.jpa.DrugRepository;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {
    private final DrugRepository drugRepository;
    private final DrugActionRepository drugActionRepository;

    //注入
    @Autowired
    public ApiServiceImpl(DrugRepository drugRepository, DrugActionRepository drugActionRepository) {
        this.drugRepository = drugRepository;
        this.drugActionRepository=drugActionRepository;
    }

    //药品信息
    @Override
    public List<DrugEntity> getDrugsByName(String name) {
            Optional<List<DrugEntity>> drugEntities = drugRepository.getByNameStartingWith(name);
            if (!drugEntities.isPresent()){
                throw new RuntimeException("No Data");
            }
            return drugEntities.get();
    }

    @Override
    public List<DrugEntity> getDrugsById(Long id){
        Optional<List<DrugEntity>> drugEntities=drugRepository.getById(id);
        if (!drugEntities.isPresent()){
            throw new RuntimeException("No Data");
        }
        return drugEntities.get();

    }

    //获取所有药品
    @Override
    public List<DrugEntity> getAllDrugs() {
        List<DrugEntity> drugEntities = drugRepository.getAll();
        return drugEntities;
    }

    //行为信息
    @Override
    public List<DrugActionEntity> getActionById(Long id){
        Optional<List<DrugActionEntity>> actionEntities=drugActionRepository.getById(id);
        if (!actionEntities.isPresent()){
            throw new RuntimeException("No Data");
        }
        return actionEntities.get();
    }

    @Override
    public List<DrugActionEntity> getActionByDrugId(Long id){
        Optional<List<DrugActionEntity>> actionEntities=drugActionRepository.getByDrugId(id);
        if (!actionEntities.isPresent()){
            throw new RuntimeException("No Data");
        }
        return actionEntities.get();
    }
    @Override
    public List<DrugActionEntity> getActionByUserId(Long id){
        Optional<List<DrugActionEntity>> actionEntities=drugActionRepository.getByUserId(id);
        if (!actionEntities.isPresent()){
            throw new RuntimeException("No Data");
        }
        return actionEntities.get();
    }

    @Override
    public List<DrugActionEntity> getAllActions(){
        List<DrugActionEntity> actionEntities=drugActionRepository.getAll();
        return actionEntities;


    }

}
