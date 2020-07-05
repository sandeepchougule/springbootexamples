package com.sandeep.example.sandeep.files.controller;


import com.sandeep.example.sandeep.core.BaseController;
import com.sandeep.example.sandeep.files.service.FilterFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class WidgetsController extends BaseController {

    @Autowired
    FilterFilterService filterFilterService;

    @RequestMapping(value = "/api/top/users", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Long> topNUsers(@RequestParam("topLimit") int topLimit) throws IOException {
        return filterFilterService.readTempFilesAndReturnTopNUsers(topLimit);
    }

    @RequestMapping(value = "/api/top/files", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Long> topNFiles(@RequestParam("topLimit") int topLimit) throws IOException {
        return filterFilterService.readTempFilesAndReturnTopNFiles(topLimit);
    }

    @RequestMapping(value = "/api/activitiy/trend", method = {RequestMethod.GET})
    @ResponseBody
    public long [][]  trendOfActivity() throws IOException {
        return filterFilterService.readTempFilesAndReturnTrend();
    }



}
