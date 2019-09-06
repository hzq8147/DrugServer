package com.drug.controller;

import com.drug.model.response.ResponseMessage;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drug.enums.enumCodes;
import javax.servlet.http.HttpServletRequest;

@RestController
public class WebErrorController implements ErrorController {

    @GetMapping("/error")
    public Object handleError(HttpServletRequest request){
        Integer statusCode=(Integer) request.getAttribute("javax.servlet.error.status_code");
        ResponseMessage responseMsg=new ResponseMessage(enumCodes.ERROR_WRONGPATH,"出了点错",statusCode);
        return responseMsg;

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
