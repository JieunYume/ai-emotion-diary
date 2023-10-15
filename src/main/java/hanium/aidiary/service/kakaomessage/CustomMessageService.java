package hanium.aidiary.service.kakaomessage;

import hanium.aidiary.dto.kakao.DefaultMessageDto;
import hanium.aidiary.service.kakaomessage.KakaoAuthService;
import hanium.aidiary.service.kakaomessage.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMessageService {
    private final MessageService messageService;

    public boolean sendMyMessage() {
        DefaultMessageDto myMsg = new DefaultMessageDto();
        myMsg.setBtnTitle("자세히보기");
        myMsg.setMobileUrl("");
        myMsg.setObjType("text");
        myMsg.setWebUrl("");
        myMsg.setText("메시지 테스트입니다.");

        String accessToken = KakaoAuthService.authToken;

        return messageService.sendMessage(accessToken, myMsg);
    }
}
