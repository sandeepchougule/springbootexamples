package com.sandeep.example.sandeep.files.service;

import com.sandeep.example.sandeep.files.dto.FileUploadStatus;
import org.springframework.stereotype.Service;

@Service
public class FileService {


    public FileUploadStatus processZipFile() {
        FileUploadStatus fileUploadStatus =
                FileUploadStatus.builder().status("success").build();
        return fileUploadStatus;
    }
}
