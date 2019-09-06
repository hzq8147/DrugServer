package com.drug.controller;

import com.drug.model.entity.DrugEntity;
import com.drug.model.entity.ResponseMessage;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.drug.enums.enumCodes;
@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/temp")
    public String getUrl(){
        return "http://172.16.5.72:8080/?type=Teacher&token=fa75b93010fa75664e110df7eb3f876883e42cb5&mode=read";
    }

    @GetMapping("/drugs/search")
    public ResponseMessage searchDrugs(@Param("name") String name,@Param("id") Long id) {
        Object Data;

        if (name!=null){
            Data=apiService.getDrugsByName(name);
        }else if (id!=null){
            Data=apiService.getDrugsById(id);
        }else{
            Data=apiService.getAllDrugs();
        }

        ResponseMessage responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",Data);
        return responseMsg;
    }

    @GetMapping("/drugs/action/search")
    public ResponseMessage searchDrugActions(@Param("id") Long id,@Param("drugId") Long drugId,@Param ("userId") Long userId){
        Object Data;
        if (id!=null){
            Data=apiService.getActionById(id);
        }else if(drugId!=null){
            Data=apiService.getActionByDrugId(drugId);
        }else if(userId!=null){
            Data=apiService.getActionByUserId(userId);
        }else{
            Data=apiService.getAllActions();
        }

        ResponseMessage responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",Data);
        return responseMsg;


    }

}

