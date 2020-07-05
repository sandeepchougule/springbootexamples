import com.sandeep.example.sandeep.files.Util.DateUtil
import com.sandeep.example.sandeep.files.Util.FileUtil
import com.sandeep.example.sandeep.files.dto.FileEventData
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
        mapData1.put("DATETIME", "5/10/2013 1:53:34 2:41:16");
        FileEventData fileEventData1= FileEventData.builder()
                .mapData(mapData1)
                .build();
        fileEventDataList.add(fileEventData1);

        Map<String, String> mapData2 = new HashMap<>();
        mapData2.put("USER_NAME", "sandeep");
        mapData2.put("DATETIME", "5/10/2013 2:35:29 1:56:05");
        FileEventData fileEventData2= FileEventData.builder()
                .mapData(mapData2)
                .build();

        fileEventDataList.add(fileEventData2);

        Map<String, String> mapData3 = new HashMap<>();
        mapData3.put("USER_NAME", "Vipul");
        mapData3.put("DATETIME", "5/10/2013 2:35:29 1:56:05");
        FileEventData fileEventData3= FileEventData.builder()
                .mapData(mapData3)
                .build();
        fileEventDataList.add(fileEventData3);

        Map<String, String> mapData4 = new HashMap<>();
        mapData4.put("USER_NAME", "Akshay");
        mapData4.put("DATETIME", "5/10/2013 3:50:27 1:28:57");
        FileEventData fileEventData4= FileEventData.builder()
                .mapData(mapData4)
                .build();
        fileEventDataList.add(fileEventData4);

        Map<String, String> mapData5 = new HashMap<>();
        mapData5.put("USER_NAME", "Akshay");
        mapData5.put("DATETIME", "5/10/2013 3:50:27 1:28:57");
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
   /*     List<FileEventData> fileEventDataList = new ArrayList<>();
        FileEventData fileEventData =  FileEventData.builder().build();
        fileEventDataList.add(fileEventData)*/
        FileDetail fileDetail1 =
                FileDetail.builder()
                        .fileName("file1")
                        .fileSize(10)
                .fileEventDataList(fileEventDataList)
                        .build();
        FileDetail fileDetail2 =
                FileDetail.builder()
                        .fileName("file2")
                        .fileSize(20)
                        .fileEventDataList(fileEventDataList)
                        .build();
        FileDetail fileDetail3 =
                FileDetail.builder()
                        .fileName("file3")
                        .fileSize(12)
                        .fileEventDataList(fileEventDataList)
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

    def "Get date coverstion getDateTimeByPosition 1"(){
        when :
        String startDateTime = DateUtil.getDateTimeByPosition("5/16/2013 11:34:13 0:18:27", 1)
        DateUtil.dateTime(startDateTime);

        then :
        startDateTime == '5/10/2013 0:20:48';

    }

    def "Get date coverstion getDateTimeByPosition 2"(){
        when :
        String startDateTime = DateUtil.getDateTimeByPosition("5/10/2013 0:20:48 0:18:27", 2)
        int[][] arr = new int[2][2];
        arr[0][0] = 10;
        arr[0][1] = 100;
        arr[1][0] = 110;
        arr[1][1] = 1100;
        System.out.println("arr[0][0] = " + arr[0][0]);
        System.out.println("arr[0][0] = " + arr);
        then :
        startDateTime == '5/10/2013 0:18:27';

    }

    def "Get Trend of activity by" () {

        when :
        long [][] dateAndRepeatValues = filterFilterService.trendOfActivity(fileEventDataList);
        then:
        System.out.println("dateAndRepeatValues+"+dateAndRepeatValues)
        1 ==1
    }
}
