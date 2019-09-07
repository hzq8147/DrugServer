package com.drug.repository.jpa;

import com.drug.model.entity.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<DrugEntity, Long> {
    //药物信息查询
    List<DrugEntity> getByNameStartingWith(String name);
    Optional<DrugEntity> getById(Long id);
    DrugEntity save(DrugEntity drug);
    void deleteById(Long id);

    @Query("from DrugEntity")
    List<DrugEntity> getAll();
}
