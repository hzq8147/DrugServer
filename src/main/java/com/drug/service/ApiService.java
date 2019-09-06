package com.drug.service;

import com.drug.model.entity.DrugActionEntity;
import com.drug.model.entity.DrugEntity;

import java.util.List;
import java.util.Optional;


public interface ApiService {
   //药品信息
   List<DrugEntity> getDrugsByName(String name);
   List<DrugEntity> getDrugsById(Long id);

   DrugEntity insertDrug(DrugEntity drug);
   DrugEntity updateDrug(DrugEntity drug);
   DrugEntity deleteDrug(Long id);

   List<DrugEntity> getAllDrugs();
   //行为信息
   List<DrugActionEntity> getActionById(Long id);
   List<DrugActionEntity> getActionByUserId(Long id);
   List<DrugActionEntity> getActionByDrugId(Long id);

   DrugActionEntity insertDrugAction(DrugActionEntity drugAction);

   List<DrugActionEntity> getAllActions();

}
