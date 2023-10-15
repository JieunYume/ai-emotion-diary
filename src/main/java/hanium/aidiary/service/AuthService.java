package hanium.aidiary.service;

import com.amazonaws.services.s3.AmazonS3;
import hanium.aidiary.domain.Crop;
import hanium.aidiary.domain.File;
import hanium.aidiary.domain.Member;
import hanium.aidiary.domain.MemberType;
import hanium.aidiary.dto.auth.JoinRequest;
import hanium.aidiary.dto.auth.JoinResponse;
import hanium.aidiary.dto.auth.LoginRequest;
import hanium.aidiary.dto.auth.LoginResponse;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.FileRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

import static hanium.aidiary.handler.ErrorCode.*;

@Service // jpa가 성능을 최적화해줌, '이건 읽기 전용이야' 읽기 전용이 많으면 여기에 넣어주고, 읽기 전용이 아닌 메소드에 @Transactional 달아줌
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileRepository fileRepository; // -> fileService에 넣기
    private final CropService cropService;
    private final AmazonS3 amazonS3;

    public void isDuplicateEmail(String email) {
        Member findMember = memberRepository.findByEmail(email).orElse(null);
        if (findMember != null) {
            throw new CustomException(ALREADY_SAVED_EMAIL);
        }
    }

    /**
     * 회원가입
     */
    @Transactional
    public JoinResponse join(JoinRequest joinRequest, Long fileId) {
        isDuplicateEmail(joinRequest.getEmail());
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> {
                    throw new CustomException(FILE_NOT_FOUND);
                });

        Crop saveCrop = cropService.createCrop();

        Member saveMember = memberRepository.save(Member.builder()
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .type(MemberType.USER)
                .file(file)
                .crop(saveCrop)
                .build());

        return JoinResponse.builder()
                .email(saveMember.getEmail())
                .build();
    }

    /**
     * 로그인
     */
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginDto) throws IllegalArgumentException {
        System.out.println(loginDto.getEmail());
        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> {
                    throw new CustomException(EMAIL_NOT_FOUND);
                });
        if (passwordEncoder.matches(loginDto.getPassword(), member.getPassword()) == false) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }
        URL url = amazonS3.getUrl("aidiary-bucket", member.getFile().getOrigFilename());
        String urltext = ""+url;
        return LoginResponse.builder()
                .memberId(member.getId())
                .nickName(member.getNickName())
                .fileUrl(urltext)
                .build();
    }
}
