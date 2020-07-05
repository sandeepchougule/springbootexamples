import com.sandeep.example.sandeep.files.dto.FileEventData
import com.sandeep.example.sandeep.files.dto.FileUploadStatus
import com.sandeep.example.sandeep.files.entity.FileDetail.FileDetail
import com.sandeep.example.sandeep.files.service.FileService
import spock.lang.Ignore
import spock.lang.Specification

class FileServiceTest extends Specification {

    FileService fileService = new FileService();
    def setup() {

    }



    def "extract the zip file content" () {
        given :
        String zipFilePath = "/opt/tmp/input/inputFiles.zip"
        when :
        FileUploadStatus fileUploadStatus =  fileService.extractZip(zipFilePath);
        then :
        fileUploadStatus.getStatus().equalsIgnoreCase("success")
    }
    def "Read the extracted files and dump into a table"() {
        List<FileDetail> fileDetailList
        when :
         fileDetailList = fileService.readExtractedFiles()
        then :
        fileDetailList.size() > 0
        fileDetailList.get(0).getFileName() == "SharePointEvents_5_10_2013.csv";
    }

    def "Parse single row" () {
        given:
        String row = "CheckIn:\tOBJECT_NAME=web log analysis paper.pdf\t" +
                "DATETIME=5/22/2013\t0:25:10\t0:16:40\t" +
                "OBJECTNAME=web log analysis paper.pdf\t" +
                "APPLICATIONNAME=SECX-SHARE\t" +
                "OBJECT_TYPE=secx_document\t" +
                "HOST_NAME=secxmecsprd06\t" +
                "MESSAGE=View/Export\t" +
                "EVENT_DESCRIPTION=View/Export\t" +
                "DATETIME_5=06/01/2012\t0:16:40\t" +
                "USER_NAME=2489\t" +
                "COMMANDNAME=secx_document\t" +
                "SOURCEUSERNAME=USERNAME";
        when :
        FileEventData fileEventData = fileService.readRow(row)
        then :
        Optional<Map.Entry<String, String>> firstEntry =     fileEventData.getMapData().entrySet().stream().findFirst();
        System.out.println("firstEntry"+firstEntry)
        firstEntry.get().key.toString().contains('APPLICATIONNAME')
        firstEntry.get().value.toString().contains('SECX-SHARE');
    }

    def "read entire file and create response object" () {
        List<FileDetail> fileDetailList
        when :
        fileDetailList = fileService.readExtractedFiles()
        then :
        fileDetailList.size() > 0
        fileDetailList.get(0).getFileName() == "SharePointEvents_5_10_2013.csv";
        fileDetailList.get(0).getFileEventDataList().size() > 0
    }

}
