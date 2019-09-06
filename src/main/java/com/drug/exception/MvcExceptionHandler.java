package com.drug.exception;

import com.drug.model.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.drug.enums.enumCodes;
@RestControllerAdvice
public class MvcExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e) {
        ResponseMessage responseMsg=new ResponseMessage(enumCodes.UNKNOWN_ERROR,e.getMessage(),null);
        switch (e.getMessage()){
            case "No Data":
                responseMsg=new ResponseMessage(enumCodes.SEARCH_NODATA,e.getMessage(),null);
                break;
            default:
                break;
        }
        return responseMsg;
    }
}
