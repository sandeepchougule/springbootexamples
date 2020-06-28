package com.sandeep.example.sandeep.files.service;

import com.sandeep.example.sandeep.files.dto.FileUploadStatus;
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Arrays.sort;

@Service
public class FileService {
    String destDir = "/opt/tmp/output";
    private static final int BUFFER_SIZE = 4096;
    public FileUploadStatus extractZip(String zipFilePath) throws IOException {
        FileUploadStatus fileUploadStatus =
                FileUploadStatus.builder().status("success").build();
        unzip(zipFilePath, destDir);
        return fileUploadStatus;
    }

    public List<FileDetail> readExtractedFiles() throws IOException {
        List<FileDetail> fileDetailList = new ArrayList<>();
        File folderToSearch = new File(destDir+"/inputFiles");

        File[] inputFiles = folderToSearch.listFiles();

        for (File file : inputFiles) {
            FileDetail fileDetail =
                    FileDetail.builder()
                            .fileName(file.getName())
                            .fileSize(file.length())
                            .build();
            fileDetailList.add(fileDetail);
        }
        System.out.println("fileDetailList:-"+fileDetailList);
        return fileDetailList;
    }


    public static boolean unzip(String zipFilePath, String destDirectory) throws IOException {

        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        return true;
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
