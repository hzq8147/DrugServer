package com.drug.controller;

import com.drug.model.entity.DrugActionEntity;
import com.drug.model.entity.DrugEntity;
import com.drug.model.request.DrugDeleteRequest;
import com.drug.model.response.ResponseMessage;
import com.drug.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

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
        ResponseMessage responseMsg;
        if (name!=null){
            Data=apiService.getDrugsByName(name);
        }else if (id!=null){
            Data=apiService.getDrugsById(id);
        }else{
            Data=apiService.getAllDrugs();
        }
        if (Data==null){
           responseMsg=new ResponseMessage(enumCodes.SEARCH_NODATA,"No Data",Data);
        }else {
           responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",Data);
        }
        return responseMsg;
    }
    @PostMapping("/drugs/create")
    public ResponseMessage createDrug(@RequestBody DrugEntity drug){
        ResponseMessage responseMsg;
        if (drug!=null){
            apiService.insertDrug(drug);
            responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",null);
        }else {
            responseMsg = new ResponseMessage(enumCodes.ERROR_DATAERROR, "Data cant be null", null);
        }
        return responseMsg;
    }
    @PostMapping("/drugs/update")
    public ResponseMessage updateDrug(@RequestBody DrugEntity drug){
        ResponseMessage responseMsg;
         if(drug!=null){
            DrugEntity drugNew=apiService.updateDrug(drug);
            if (drugNew!=null){
                responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",drugNew);
            }
            else{
                responseMsg=new ResponseMessage(enumCodes.SEARCH_NODATA,"Cant find this data",null);
            }
        }else {
            responseMsg = new ResponseMessage(enumCodes.ERROR_DATAERROR, "Data cant be null", null);
        }
        return responseMsg;
    }
    @PostMapping("/drugs/delete")
    public ResponseMessage deleteDrug(@RequestBody DrugDeleteRequest request){
        ResponseMessage responseMsg;
        if (request!=null){
            DrugEntity drug=apiService.deleteDrug(request.getId());
            if (drug !=null){
                responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",null);
            }else {
                responseMsg=new ResponseMessage(enumCodes.SEARCH_NODATA,"Cant find this data",null);
            }
        }else{
            responseMsg =new ResponseMessage(enumCodes.ERROR_DATAERROR,"Id cant be null",null);
        }
        return responseMsg;
    }
    @PostMapping("/drugs/action/create")
    public ResponseMessage deleteDrug(@RequestBody DrugActionEntity drugAction){
        ResponseMessage responseMsg;
        if (drugAction != null) {
            DrugActionEntity action=apiService.insertDrugAction(drugAction);
            if (action!=null){
                responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",action);

            }else{
                responseMsg=new ResponseMessage(enumCodes.ERROR_DATAERROR,"cant find data or action error",null);
            }
        }else{
            responseMsg =new ResponseMessage(enumCodes.ERROR_DATAERROR,"Data cant be null",null);
        }
        return responseMsg;
    }
    @GetMapping("/drugs/action/search")
    public ResponseMessage searchDrugActions(@Param("id") Long id,@Param("drugId") Long drugId,@Param ("userId") Long userId){
        Object Data;
        ResponseMessage responseMsg;
        if (id!=null){
            Data=apiService.getActionById(id);
        }else if(drugId!=null){
            Data=apiService.getActionByDrugId(drugId);
        }else if(userId!=null){
            Data=apiService.getActionByUserId(userId);
        }else{
            Data=apiService.getAllActions();
        }
        if (Data==null){
            responseMsg=new ResponseMessage(enumCodes.SEARCH_NODATA,"No Data",Data);
        }else{
            responseMsg=new ResponseMessage(enumCodes.SUCCESS,"",Data);
        }
        return responseMsg;
    }

}

