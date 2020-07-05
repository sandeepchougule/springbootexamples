package com.sandeep.example.sandeep.core;

import com.sandeep.example.sandeep.core.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public @ResponseBody
    ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex,
                                                HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return errorResponse;
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public @ResponseBody
    ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex,
                                                 HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return errorResponse;
    }



    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    ErrorResponse handleException(Exception ex, HttpServletResponse response, HttpServletRequest request, WebRequest webRequest) throws IOException {
        ErrorResponse res = new ErrorResponse();
        res.setErrorMessage("Oops!!! Something went wrong, inconvenience regretted, please try again later.");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ex.printStackTrace();
        return res;
    }





}
