package com.drug.service.impl;

import com.drug.model.entity.DrugActionEntity;
import com.drug.model.entity.DrugEntity;
import com.drug.repository.jpa.DrugActionRepository;
import com.drug.repository.jpa.DrugRepository;
import com.drug.service.ApiService;
import com.drug.utils.TimeUtils;
import com.mysql.cj.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
            List<DrugEntity> drugEntities = drugRepository.getByNameStartingWith(name);
            return drugEntities;
    }

    @Override
    public DrugEntity getDrugById(Long id){
        Optional<DrugEntity> drugEntities=drugRepository.getById(id);
        if (!drugEntities.isPresent()){
            return null;
        }
        return drugEntities.get();

    }
    //插入信息
    @Override
    public  DrugEntity insertDrug(DrugEntity drug){
        drug.setCreatedAt(TimeUtils.getNowTime());
        drug.setUpdatedAt(TimeUtils.getNowTime());
        return drugRepository.save(drug);
    }
    @Override
    public DrugEntity updateDrug(DrugEntity drug){
        Optional<DrugEntity>drugOpt= drugRepository.getById(drug.getId());
        if (!drugOpt.isPresent()){
            return null;
        }
        DrugEntity drugRepo=drugOpt.get();
        drugRepo.setUpdatedAt(TimeUtils.getNowTime());
        if(drug.getName()!=null){
            drugRepo.setName(drug.getName());
        }
        if(drug.getQuantity()!=null){
            drugRepo.setQuantity(drug.getQuantity());
        }
        if (drug.getRid()!=null){
            drugRepo.setRid(drug.getRid());
        }
        if (drug.getReserves()!=null){
            drugRepo.setReserves(drug.getReserves());
        }
        if (drug.getReservesUnit()!=null){
            drugRepo.setReservesUnit(drug.getReservesUnit());
        }
        if (drug.getSecurityRisk()!=null){
            drugRepo.setSecurityRisk(drug.getSecurityRisk());
        }
        if (drug.getSecurityRiskRate()!=null){
            drugRepo.setSecurityRiskRate(drug.getSecurityRiskRate());
        }
        if (drug.getDanger()!=null){
            drugRepo.setDanger(drug.getDanger());
        }
        if (drug.getReservesMin()!=null){
            drugRepo.setReservesMin(drug.getReservesMin());
        }
        if (drug.getRemark()!=null){
            drugRepo.setRemark(drug.getRemark());
        }
        if (drug.getManagement()!=null){
            drugRepo.setManagement(drug.getManagement());
        }
        if (drug.getPlaceRoom()>0){
            drugRepo.setPlaceRoom(drug.getPlaceRoom());
        }
        return drugRepository.save(drugRepo);
    }
    @Override
    public DrugEntity deleteDrug(Long id){
        Optional<DrugEntity> drugRepo= drugRepository.getById(id);
        if (!drugRepo.isPresent()){
            return null;
        }
        drugRepository.deleteById(id);
        return  drugRepo.get();
    }
    //获取所有药品
    @Override
    public List<DrugEntity> getAllDrugs() {
        return drugRepository.getAll();
    }

    //行为信息
    @Override
    public DrugActionEntity getActionById(Long id){
        Optional<DrugActionEntity> actionEntities=drugActionRepository.getById(id);
        return actionEntities.orElse(null);
    }

    @Override
    public List<DrugActionEntity> getActionByDrugId(Long id){
        return drugActionRepository.getByDrugId(id);
    }
    @Override
    public List<DrugActionEntity> getActionByUserId(Long id){
        return drugActionRepository.getByUserId(id);
    }

    @Override
    public DrugActionEntity insertDrugAction(DrugActionEntity drugAction){
        Optional<DrugEntity>drugOpt=drugRepository.getById(drugAction.getDrugId());
        if (!drugOpt.isPresent()){
            return null;
        }
        DrugEntity drugEntities=drugOpt.get();
        BigDecimal quantity=drugEntities.getQuantity();
        BigDecimal changeNum=drugAction.getChangeNum();
        BigDecimal newNum=quantity.add(changeNum);
        //判断是否够减少的
        if (newNum.compareTo(new BigDecimal("0")) <0){
            return null;
        }

        drugEntities.setQuantity(newNum);
        updateDrug(drugEntities);

        drugAction.setCreatedAt(TimeUtils.getNowTime());
        drugAction.setUpdatedAt(TimeUtils.getNowTime());

        return drugActionRepository.save(drugAction);


    }
    @Override
    public List<DrugActionEntity> getAllActions(){
        return drugActionRepository.getAll();
    }


}
