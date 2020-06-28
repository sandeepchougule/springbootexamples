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


    @Ignore
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
        String row = "CheckIn:\tOBJECT_NAME=web log analysis paper.pdf\tDATETIME=5/22/2013\t0:25:10\t0:16:40\tOBJECTNAME=web log analysis paper.pdf\tAPPLICATIONNAME=SECX-SHARE\tOBJECT_TYPE=secx_document\tHOST_NAME=secxmecsprd06\tMESSAGE=View/Export\tEVENT_DESCRIPTION=View/Export\tDATETIME_5=06/01/2012\t0:16:40\tUSER_NAME=2489\tCOMMANDNAME=secx_document\tSOURCEUSERNAME=USERNAME";
        when :
        FileEventData fileEventData = fileService.readRow(row)
        then :
        Optional<Map.Entry<String, String>> firstEntry =     fileEventData.getMapData().entrySet().stream().findFirst();
        firstEntry.get().key.toString().contains('OBJECT_TYPE')
        firstEntry.get().value.toString().contains('secx_document');
    }



}
