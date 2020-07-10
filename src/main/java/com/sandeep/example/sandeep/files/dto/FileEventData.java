package com.sandeep.example.sandeep.files.dto;

import com.sandeep.example.sandeep.files.Util.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
