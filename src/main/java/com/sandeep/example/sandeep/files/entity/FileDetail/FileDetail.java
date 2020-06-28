package com.sandeep.example.sandeep.files.entity.FileDetail;

import com.sandeep.example.sandeep.files.dto.FileEventData;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import java.util.List;

//@Entity
@Builder
@ToString
@Data
public class FileDetail {

    private int fileId;
    private String fileName;
    private long fileSize;
    private List<FileEventData> fileEventDataList;

    @Tolerate
    public FileDetail() {
    }
}
