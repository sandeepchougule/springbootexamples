package com.sandeep.example.sandeep.files.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import java.util.*;

//@Entity
@Builder
@Data
public class FileEventData {

    Map<String, String> mapData = new HashMap<>();

    private String applicationName;
    private Date dateTime;
    private String userName;

    @Tolerate
    public FileEventData() {
    }


    public String getUserName() {
        if(mapData.containsKey("USER_NAME") ){
            return mapData.get("USER_NAME");
        }
        return "";
    }


}
