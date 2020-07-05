package com.sandeep.example.sandeep.files.service;

import com.sandeep.example.sandeep.files.dto.FileEventData;
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class FilterFilterService {

    @Autowired
    FileService fileService;

    /**
     * Returns the top N User Names
     * This is just to avoid using DB and return data with files read
     *
     * @param  limitN The limit of top Users you want to find
     * @return      Map<String, Long> Users with top count sorted by max
     */
    public Map<String, Long> readTempFilesAndReturnTopNUsers(Integer limitN) throws IOException {

        List<FileDetail> fileDetailList = fileService.readExtractedFiles();
        List<FileEventData> fileEventData  = fileDetailList.stream().flatMap
                (it -> it.getFileEventDataList().stream()).collect(Collectors.toList());

         return getTopNUsers(fileEventData, limitN);
    }

    /**
     * Returns the top N User Names
     * This is to demonstrate how we can filter using java 8 , Instead of this the MySQL
     * or any Storage engine with an query is most recommended
     *
     * @param  fileEventDataList  The Users data to filter from
     * @param  limitN The limit of top Users you want to find
     * @return      Map<String, Long> Users with top count sorted by max
     */
    public Map<String, Long> getTopNUsers(List<FileEventData> fileEventDataList, Integer limitN) {

        //Here grouping by UserName and counting the values
        Map<String, Long> userNameGroupByCount;
        userNameGroupByCount = fileEventDataList.parallelStream().collect(
                groupingBy(FileEventData::getUserName, counting()));

        //Then sorting the map in reverse order and fetch the specified limit

        Map<String, Long> userNameSortedByCountAndLimit;
        userNameSortedByCountAndLimit = userNameGroupByCount.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(limitN)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        System.out.println("userNameSortedByCountAndLimit"+ userNameSortedByCountAndLimit);

        return userNameSortedByCountAndLimit;
    }

    /**
     * Returns the top N File Names by size
     * This is just to avoid using DB and return data with files read
     *
     * @param  limitN The limit of top Users you want to find
     * @return      Map<String, Long> Users with top count sorted by max
     */
    public Map<String, Long> readTempFilesAndReturnTopNFiles(Integer limitN) throws IOException {

        List<FileDetail> fileDetailList = fileService.readExtractedFiles();
        return getTopNFiles(fileDetailList, limitN);
    }

    /**
     * Returns the top N File Names
     * This is to demonstrate how we can filter using java 8 , Instead of this the MySQL
     * or any Storage engine with an query is most recommended
     *
     * @param  fileDetails  The File data to filter from
     * @param  limitN The limit of top File you want to find
     * @return      Map<String, Long> File with top count sorted by max file size
     */
    public Map<String, Long> getTopNFiles(List<FileDetail> fileDetails, Integer limitN) {

        //Created a Comparator to compare by File Size as I don't see any other comparison field for file
        Comparator<FileDetail> byFileSize =
                Comparator.comparing(FileDetail::getFileRows);
        Map<String, Long> dataReturn =  fileDetails.stream().
                sorted(byFileSize.reversed()).
                limit(limitN).
                collect(Collectors.toMap(FileDetail::getFileName, FileDetail::getFileRows));
        System.out.println("dataReturn:->"+dataReturn);
        return dataReturn;

    }
}
