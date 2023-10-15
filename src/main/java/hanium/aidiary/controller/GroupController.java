package hanium.aidiary.controller;

import hanium.aidiary.dto.group.GroupCreateRequest;
import hanium.aidiary.dto.group.GroupRequest;
import hanium.aidiary.dto.group.GroupJoinRequest;
import hanium.aidiary.service.AuthService;
import hanium.aidiary.service.GroupService;
import hanium.aidiary.service.kakaomessage.CustomMessageService;
import hanium.aidiary.service.kakaomessage.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 권한에 따라 메소드의 호출을 제한
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
    private final GroupService groupService;
    private final KakaoAuthService kakaoAuthService;
    private final CustomMessageService customMessageService;

    // 그룹 생성
    @PostMapping("/")
    public ResponseEntity create(@RequestBody GroupCreateRequest request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }
    // 그룹 가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody GroupJoinRequest request) {
        return ResponseEntity.ok(groupService.joinGroup(request));
    }

    // 내 그룹 멤버 조회
    @GetMapping("/")
    public ResponseEntity findMyGroupMembers(@RequestParam Long memberId) {
        return ResponseEntity.ok(groupService.findGroupMembers(memberId));
    }

    // 그룹 나가기
    @DeleteMapping("/")
    public ResponseEntity leaveGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.leaveGroup(request));
    }

    // 초대장 보내기
    @GetMapping("/message")
    public String serviceStart(String code) {
        if(kakaoAuthService.getKakaoAuthToken(code)) {
            customMessageService.sendMyMessage();
            return "메시지 전송 성공";
        }else {
            return "토큰발급 실패";
        }
    }





}
