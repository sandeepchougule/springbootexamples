package com.sandeep.example.sandeep.files.controller;


import com.sandeep.example.sandeep.files.dto.FileUploadStatus;
import com.sandeep.example.sandeep.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/api/uploadfile", method = {RequestMethod.GET})
    @ResponseBody
    public FileUploadStatus fileUploadStatus(){
        return fileService.processZipFile();
    }
}