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
            Optional<List<DrugEntity>> drugEntities = drugRepository.getByNameStartingWith(name);
            if (!drugEntities.isPresent()){
                return null;
             }
            return drugEntities.get();
    }

    @Override
    public List<DrugEntity> getDrugsById(Long id){
        Optional<List<DrugEntity>> drugEntities=drugRepository.getById(id);
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
        DrugEntity drugRepo= drugRepository.findById(drug.getId()).get();
        if (drugRepo==null){
            return null;
        }
        drugRepo.setUpdatedAt(TimeUtils.getNowTime());

        drugRepo.setName(drug.getName());
        drugRepo.setQuantity(drug.getQuantity());
        drugRepo.setRid(drug.getRid());
        drugRepo.setReserves(drug.getReserves());
        drugRepo.setReservesUnit(drug.getReservesUnit());
        drugRepo.setSecurityRisk(drug.getSecurityRisk());
        drugRepo.setSecurityRiskRate(drug.getSecurityRiskRate());
        drugRepo.setDanger(drug.getDanger());
        drugRepo.setReservesMin(drug.getReservesMin());
        drugRepo.setRemark(drug.getRemark());
        drugRepo.setManagement(drug.getManagement());
        drugRepo.setPlaceRoom(drug.getPlaceRoom());
        return drugRepository.save(drugRepo);
    }
    @Override
    public DrugEntity deleteDrug(Long id){
        DrugEntity drugRepo= drugRepository.findById(id).get();
        if (drugRepo==null){
            return null;
        }
        drugRepository.deleteById(id);
        return  drugRepo;
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
            return null;
        }
        return actionEntities.get();
    }

    @Override
    public List<DrugActionEntity> getActionByDrugId(Long id){
        Optional<List<DrugActionEntity>> actionEntities=drugActionRepository.getByDrugId(id);
        if (!actionEntities.isPresent()){
            return null;
        }
        return actionEntities.get();
    }
    @Override
    public List<DrugActionEntity> getActionByUserId(Long id){
        Optional<List<DrugActionEntity>> actionEntities=drugActionRepository.getByUserId(id);
        if (!actionEntities.isPresent()){
            return null;
        }
        return actionEntities.get();
    }

    @Override
    public DrugActionEntity insertDrugAction(DrugActionEntity drugAction){
        Optional<List<DrugEntity>> drugEntities=drugRepository.getById(drugAction.getDrugId());
        if (!drugEntities.isPresent()){
            return null;
        }
        BigDecimal quantity=drugEntities.get().get(0).getQuantity();
        BigDecimal changeNum=drugAction.getChangeNum();
        BigDecimal newNum=quantity.add(changeNum);
        //判断是否够减少的
        if (newNum.compareTo(new BigDecimal("0")) <0){
            return null;
        }

        drugEntities.get().get(0).setQuantity(newNum);
        updateDrug(drugEntities.get().get(0));

        drugAction.setCreatedAt(TimeUtils.getNowTime());
        drugAction.setUpdatedAt(TimeUtils.getNowTime());

        DrugActionEntity action=drugActionRepository.save(drugAction);
        return action;


    }
    @Override
    public List<DrugActionEntity> getAllActions(){
        List<DrugActionEntity> actionEntities=drugActionRepository.getAll();
        return actionEntities;
    }


}
