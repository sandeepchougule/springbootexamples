package com.sandeep.example.sandeep.files.service;

import com.sandeep.example.sandeep.files.dto.FileEventData;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class FilterFilterService {

    /**
     * Returns the top N User Names
     * This is to demonstrate how we can filter using java 8 , Instead of this the MySQL
     * or any Storage engine with an query is most recommended
     *
     * @param  fileEventDataList  The Users data to filter from
     * @param  limitN The limit of top Users you want to find
     * @return      List<String> Users with top count sorted by max
     */
    public List<String> getTopNUsers(List<FileEventData> fileEventDataList, Integer limitN) {

        //Here grouping by UserName and counting the values
        Map<String, Long> userNameGroupByCount;
        userNameGroupByCount = fileEventDataList.parallelStream().collect(
                groupingBy(FileEventData::getUserName, counting()));
        System.out.println("userNameAndCount:->" + userNameGroupByCount);

        //Then sorting the map in reverse order and fetch the specified limit

        Map<String, Long> userNameSortedByCountAndLimit;
        userNameSortedByCountAndLimit = userNameGroupByCount.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(limitN)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
        System.out.println("userNameSortedByCountAndLimit:->" + userNameSortedByCountAndLimit);

        //As expecation is returning top N Usernames
        if (!CollectionUtils.isEmpty(userNameSortedByCountAndLimit)) {
            return new ArrayList(userNameSortedByCountAndLimit.keySet());
        }
        return new ArrayList<>();
    }
}
