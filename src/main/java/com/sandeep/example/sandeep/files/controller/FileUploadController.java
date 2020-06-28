package com.sandeep.example.sandeep.files.controller;


import com.sandeep.example.sandeep.core.BaseController;
import com.sandeep.example.sandeep.files.dto.FileUploadStatus;
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail;
import com.sandeep.example.sandeep.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FileUploadController extends BaseController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/api/uploadfile", method = {RequestMethod.POST})
    @ResponseBody
    public FileUploadStatus fileUploadStatus(String zipFilePath) throws IOException {
        return fileService.extractZip(zipFilePath);
    }
    @RequestMapping(value = "/api/view/data", method = {RequestMethod.GET} )
    @ResponseBody
    public List<FileDetail> readExtractedFiles() throws IOException {
        return fileService.readExtractedFiles();
    }
}
