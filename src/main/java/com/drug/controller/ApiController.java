package com.drug.controller;

import com.drug.model.entity.DrugEntity;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/drugs")
    public List<DrugEntity> getDrugs(@Param("name") String name) {
        return apiService.getDrugs(name);
    }
}
