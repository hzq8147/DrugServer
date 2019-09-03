package com.drug.service;

import com.drug.model.entity.DrugEntity;

import java.util.List;


public interface ApiService {
   List<DrugEntity> getDrugs(String name);
}
