package com.drug.repository.jpa;

import com.drug.model.entity.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<DrugEntity, Long> {
    Optional<DrugEntity> getByNameContaining(String name);

    Optional<DrugEntity> getByNameStartingWith(String name);

    @Query("from DrugEntity")
    List<DrugEntity> getAll();
}
