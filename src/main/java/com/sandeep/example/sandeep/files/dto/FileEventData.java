package com.sandeep.example.sandeep.files.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Entity
@Builder
@Data
public class FileEventData {

    Map<String, String> mapData = new HashMap<>();

    @Tolerate
    public FileEventData() {
    }
}
