package com.sandeep.example.sandeep.files.entity.FileDetail;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Builder
@ToString
public class FileDetail {

    private int fileId;
    private String fileName;
    private long fileSize;
}
