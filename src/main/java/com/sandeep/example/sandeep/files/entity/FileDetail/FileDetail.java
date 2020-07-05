package com.sandeep.example.sandeep.files.entity.FileDetail;

import com.sandeep.example.sandeep.files.dto.FileEventData;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import java.util.List;

//@Entity
@Builder
@ToString
@Data
public class FileDetail {

    private int fileId;
    private String fileName;
    private Long fileSize;
    private Long fileRows;
    private List<FileEventData> fileEventDataList;

    @Tolerate
    public FileDetail() {
    }

    public Long getFileRows() {
       // System.out.println("fileEventDataList" + fileEventDataList);
        if(!CollectionUtils.isEmpty(fileEventDataList)){
            // System.out.println("Size returned");
            return Long.valueOf(fileEventDataList.size());
        }
        return fileRows;
    }
}
