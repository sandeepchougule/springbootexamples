package com.sandeep.example.sandeep.files.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;
//@Entity
@Builder
@Data
public class EventDetails {


    private String applicationName;
    private String userName;
    private Date dateTime;

    @Tolerate
    public EventDetails() {
    }

}
