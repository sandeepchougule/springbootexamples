import com.sandeep.example.sandeep.files.dto.FileEventData
import com.sandeep.example.sandeep.files.dto.FileUploadStatus
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail
import com.sandeep.example.sandeep.files.service.FileService
import com.sandeep.example.sandeep.files.service.FilterFilterService
import spock.lang.Specification

class FileFilterServiceTest extends Specification {

    FilterFilterService filterFilterService = new FilterFilterService();
    def setup() {

    }



    def "Top N users from the list" () {
        List<FileEventData> fileEventDataList = new ArrayList<>();
        Map<String, String> mapData1 = new HashMap<>();
        mapData1.put("USER_NAME", "sandeep");
        FileEventData fileEventData1= FileEventData.builder()
                .mapData(mapData1)
                .build();
        fileEventDataList.add(fileEventData1);

        Map<String, String> mapData2 = new HashMap<>();
        mapData2.put("USER_NAME", "sandeep");
        FileEventData fileEventData2= FileEventData.builder()
                .mapData(mapData2)
                .build();

        fileEventDataList.add(fileEventData2);

        Map<String, String> mapData3 = new HashMap<>();
        mapData3.put("USER_NAME", "Vipul");

        FileEventData fileEventData3= FileEventData.builder()
                .mapData(mapData3)
                .build();
        fileEventDataList.add(fileEventData3);

        Map<String, String> mapData4 = new HashMap<>();
        mapData4.put("USER_NAME", "Akshay");

        FileEventData fileEventData4= FileEventData.builder()
                .mapData(mapData4)
                .build();
        fileEventDataList.add(fileEventData4);

        Map<String, String> mapData5 = new HashMap<>();
        mapData5.put("USER_NAME", "Akshay");

        FileEventData fileEventData5= FileEventData.builder()
                .mapData(mapData5)
                .build();
        fileEventDataList.add(fileEventData5);
        when :
        List<String> top2Users = filterFilterService.getTopNUsers(fileEventDataList,2);

        then :
        top2Users.get(0) == 'Akshay'
        top2Users.get(1) == 'sandeep'

    }

}
