package com.sandeep.example.sandeep.files.entity.FileDetail;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;

@Entity
@Builder
@ToString
@Data
public class FileDetail {

    private int fileId;
    private String fileName;
    private long fileSize;

    @Tolerate
    public FileDetail() {
    }
}
