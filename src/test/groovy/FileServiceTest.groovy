import com.sandeep.example.sandeep.files.dto.FileUploadStatus
import com.sandeep.example.sandeep.files.service.FileService
import spock.lang.Specification

class FileServiceTest extends Specification {

    FileService fileService = new FileService();
    def setup() {

    }



    def "extract the zip file content" () {
        given :
        String zipFilePath = "/opt/tmp/input/inputFiles.zip"
        when :
        FileUploadStatus fileUploadStatus =  fileService.processZipFile(zipFilePath);
        then :
        fileUploadStatus.getStatus().equalsIgnoreCase("success")
    }
    def "Read the extracted files and dump into a table"() {

    }

}
