import com.sandeep.example.sandeep.files.dto.FileUploadStatus
import com.sandeep.example.sandeep.files.service.FileService
import spock.lang.Specification

class HelloWorld extends Specification {

    FileService fileService = new FileService();
    def setup() {

    }

    def "Test world" () {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "Upload a .zip file and validate it has .zip extension" () {

        when :
        FileUploadStatus fileUploadStatus =  fileService.processZipFile();
        then :
        fileUploadStatus.getStatus().equalsIgnoreCase("success")
    }

}
