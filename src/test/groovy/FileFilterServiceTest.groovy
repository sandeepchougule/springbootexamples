import com.sandeep.example.sandeep.files.dto.FileEventData
import com.sandeep.example.sandeep.files.dto.FileUploadStatus
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail
import com.sandeep.example.sandeep.files.service.FileService
import com.sandeep.example.sandeep.files.service.FilterFilterService
import spock.lang.Specification

class FileFilterServiceTest extends Specification {

    FilterFilterService filterFilterService = new FilterFilterService();
    List<FileEventData> fileEventDataList = new ArrayList<>();
    FileService fileService = new FileService();
    def setup() {
        filterFilterService = new FilterFilterService(fileService: fileService);
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
    }



    def "Top N users from the list" () {

        when :
        Map<String, Long> top2Users = filterFilterService.getTopNUsers(fileEventDataList,2);

        then :
        top2Users.size() == 2 //Checking map has max 2 elements
        top2Users.entrySet().stream().findFirst().get().key == 'Akshay' //Checking first element is always Akshay as per data set provided

    }

    def "Top N users from the zip file content" () {

        when :
        Map<String, Long> top2Users = filterFilterService.readTempFilesAndReturnTopNUsers(2);

        then :
        //This is added based on files provided
        top2Users.entrySet().stream().findFirst().get().key == '1142'

    }

    def "Top N files from the zip file content" () {
        given:
        FileDetail fileDetail1 =
                FileDetail.builder()
                        .fileName("file1")
                        .fileSize(10)
                        .build();
        FileDetail fileDetail2 =
                FileDetail.builder()
                        .fileName("file2")
                        .fileSize(20)
                        .build();
        FileDetail fileDetail3 =
                FileDetail.builder()
                        .fileName("file3")
                        .fileSize(12)
                        .build();
        List<FileDetail> fileDetails = new ArrayList<>();
        fileDetails.add(fileDetail1);
        fileDetails.add(fileDetail2);
        fileDetails.add(fileDetail3);

        when :
        Map<String, Long> top2Files = filterFilterService.getTopNFiles(fileDetails,2);

        then :
        //This is added based on files provided
        top2Files.size() == 2
        top2Files.entrySet().stream().findFirst().get().key == 'file2'

    }
}
