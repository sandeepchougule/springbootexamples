package com.sandeep.example.sandeep.files.dto;

import com.sandeep.example.sandeep.files.Util.DateUtil;
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
    private Long startTime;
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

    public Long getStartTime() {
        if(mapData.containsKey("DATETIME") ){

            return DateUtil.dateTime(DateUtil.getDateTimeByPosition(mapData.get("DATETIME"), 1)) ;
        }
        return null;
    }


}
