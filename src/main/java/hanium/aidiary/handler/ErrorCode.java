package hanium.aidiary.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    PASSWORD_NOT_MATCH(400, "잘못된 비밀번호입니다."),
    CROP_ALL_GROWN(400, "농작물이 이미 다 자랐습니다. 수확이 필요합니다."),
    WRITER_NOT_MATCH(400, "일기 작성자가 일치하지 않습니다."),
    GROUP_NOT_EXIST(400, "회원에게 그룹이 존재하지 않습니다."),
    IMAGE_NOT_EXIST(400, "요청에 이미지 파일이 존재하지 않습니다"),

    //404 NOT_FOUND 잘못된 리소스 접근
    EMAIL_NOT_FOUND(404, "존재하지 않는 이메일입니다."),
    USER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    GROUP_NOT_FOUND(404, "존재하지 않는 그룹입니다."),
    DIARY_NOT_FOUND(404, "존재하지 않는 일기입니다."),
    LIKE_NOT_FOUND(404, "존재하지 않는 좋아요입니다."),
    CROP_NOT_FOUND(404, "존재하지 않는 농작물입니다."),
    FILE_NOT_FOUND(404, "존재하지 않는 파일입니다."),


    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_EMAIL(409, "이미 저장된 이메일입니다."),
    ALREADY_SAVED_LIKE(409, "이미 좋아요가 저장돼있습니다."),

    //410 계산 오류
    DIARY_CALENDAR_CAL_ERROR(410, "캘린더 서버 내부 계산 오류입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!"),
    EMAIL_ERROR(500, "이메일 에러입니다. 서버 팀에 연락주세요!"),
    USER_FILE_ERROR(500, "파일 업로드에 실패했습니다. 서버 팀에 연락주세요!");

    private final int status;
    private final String message;
}
