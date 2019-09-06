package com.drug.repository.jpa;

import com.drug.model.entity.DrugActionEntity;
import com.drug.model.entity.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugActionRepository extends JpaRepository<DrugActionEntity, Long> {
    //行为信息查询
    Optional<List<DrugActionEntity>> getById(Long id);
    Optional<List<DrugActionEntity>> getByDrugId(Long id);
    Optional<List<DrugActionEntity>> getByUserId(Long id);
    DrugActionEntity save(DrugActionEntity drugAction);


    @Query("from DrugActionEntity")
    List<DrugActionEntity> getAll();
}
