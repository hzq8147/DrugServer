package com.drug.service;

import com.drug.model.entity.DrugEntity;

import java.util.List;
import java.util.Optional;


public interface ApiService {
   List<DrugEntity> getDrugs(String name);

   List<DrugEntity> getAllDrugs();
}
