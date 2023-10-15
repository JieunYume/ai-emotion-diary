package hanium.aidiary.controller;

import hanium.aidiary.domain.File;
import hanium.aidiary.dto.auth.ConfirmEmailResponse;
import hanium.aidiary.dto.auth.JoinRequest;
import hanium.aidiary.dto.auth.LoginRequest;
import hanium.aidiary.dto.auth.LoginResponse;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.service.AuthService;
import hanium.aidiary.service.EmailService;
import hanium.aidiary.service.FileStorageService;
import hanium.aidiary.service.FileUploadService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

import static hanium.aidiary.handler.ErrorCode.IMAGE_NOT_EXIST;

@RestController // 데이터만 리턴해줄 것이다
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;
    private final FileUploadService fileUploadService;
/*
    @PostMapping(value ="/join", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> join(@RequestPart JoinRequest joinRequest,
                            @RequestPart MultipartFile imgFile) {
        if(imgFile.isEmpty()){
            throw new CustomException(IMAGE_NOT_EXIST);
        }

        String filename = fileStorageService.storeFile(imgFile);
        String origFilename = StringUtils.cleanPath(imgFile.getOriginalFilename());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadfile/")
                .path(filename)
                .toUriString();
        System.out.println("fileDownloadUri" + fileDownloadUri);
        FileDto fileDto = FileDto.builder()
                .filename(filename)
                .origFilename(origFilename)
                .filePath(fileDownloadUri)
                .build();
        return ResponseEntity.ok(authService.join(joinRequest, fileDto));
    }

 */

    @PostMapping(value ="/join", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> join(@RequestPart JoinRequest joinRequest,
                                       @RequestPart MultipartFile imgFile) {
        if(imgFile.isEmpty()){
            throw new CustomException(IMAGE_NOT_EXIST);
        }

        File savedFile = fileUploadService.uploadFile(imgFile);
        return ResponseEntity.ok(authService.join(joinRequest, savedFile.getId()));
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginDto) throws Throwable{
        System.out.println("AuthController.login");
        return ResponseEntity.ok(authService.login(loginDto));
    }


    // 이메일 인증 (인증코드를 반환)
    @PostMapping("/confirm-email")
    public ResponseEntity<ConfirmEmailResponse> mailConfirm(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        // 이메일 중복 확인
        authService.isDuplicateEmail(email);

        String authCode = emailService.sendEmail(email);
        log.info("인증코드 : " + authCode);
        return ResponseEntity.ok(ConfirmEmailResponse.builder()
                .authCode(authCode)
                .build());
    }
}
