package hanium.aidiary.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import hanium.aidiary.domain.File;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import static hanium.aidiary.handler.ErrorCode.USER_FILE_ERROR;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final FileRepository fileRepository;

    public File uploadFile(MultipartFile media) {

        String mediaName = createFileName(media.getOriginalFilename()); // 각 파일의 이름을 저장

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(media.getSize());
        objectMetadata.setContentType(media.getContentType());

        System.out.println("for each 진입 : " + mediaName);

        try (InputStream inputStream = media.getInputStream()) {
            // S3에 업로드 및 저장
            amazonS3.putObject(new PutObjectRequest(bucket, media.getOriginalFilename(), inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new CustomException(USER_FILE_ERROR);
        }

        // 접근가능한 URL 가져오기
        String mediaUrl = amazonS3.getUrl(bucket, mediaName).toString();

        // 동시에 해당 미디어 파일들을 미디어 DB에 이름과 Url 정보 저장.
        // 게시글마다 어떤 미디어 파일들을 포함하고 있는지 파악하기 위함 또는 활용하기 위함.
        File file = File.builder()
                .origFilename(media.getOriginalFilename())
                .filename(mediaName)
                .fileType(media.getContentType())
                .fileUrl(mediaUrl)
                .build();

        File savedFile = fileRepository.save(file);

        return savedFile;
    }

    // S3에 저장되어있는 미디어 파일 삭제
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }


    // 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌린다. (현재는 굳이 난수화할 필요가 없어보여 사용하지 않음)
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }


    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }


    public String getFileUrl(String origFilename) {
        URL url = amazonS3.getUrl("aidiary-bucket", origFilename);
        String urltext = ""+url;
        return urltext;
    }
}