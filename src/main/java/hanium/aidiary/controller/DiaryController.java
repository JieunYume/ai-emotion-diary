package hanium.aidiary.controller;

import hanium.aidiary.dto.calendar.CalendarDetailDto;
import hanium.aidiary.dto.diary.DiaryCreateRequest;
import hanium.aidiary.dto.diary.DiaryCreateResponse;
import hanium.aidiary.dto.diary.DiaryDeleteRequest;
import hanium.aidiary.service.CropService;
import hanium.aidiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor // 권한에 따라 메소드의 호출을 제한
@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {
    private final DiaryService diaryService;
    private final CropService cropService;

    // 일기 작성
    @PostMapping("/")
    public ResponseEntity<DiaryCreateResponse> create(@RequestBody DiaryCreateRequest diaryRequest) {
        //일기 생성 후, 작성된 일기 불러오기
        return ResponseEntity.ok(diaryService.createDiary(diaryRequest));

    }

    // 일기 조회 1. 결과창
    @GetMapping("/{id}")
    public ResponseEntity<Object> findDiary(@PathVariable(value = "id") Long diaryId) {
        return ResponseEntity.ok(diaryService.findDiary(diaryId));
    }

    // 일기 조회 2. 캘린더
    @GetMapping("/calendar/{memberId}")
    public ResponseEntity<Object> findDiaryListInCalendar(@PathVariable Long memberId, @RequestParam int year, @RequestParam int month) {
        System.out.println("DiaryController.findDiaryListInCalendar");
        return ResponseEntity.ok(diaryService.findMyCalendarList(memberId, year, month));
    }


    // 일기 조회 3. 캘린더 상세창(개인)
    @GetMapping("/calendar/detail/{memberId}")
    public ResponseEntity<CalendarDetailDto> findCanlendarDetail(@PathVariable Long memberId, @RequestParam int year,
                                                                 @RequestParam int month, @RequestParam int day) {
        return ResponseEntity.ok(diaryService.findMyCanlendarDetail(memberId, year, month, day));
    }

    // 일기 조회 3. 캘린더 상세창(그룹)
    @GetMapping("/calendar/group/{memberId}")
    public ResponseEntity<Object> findGroupCanlendarDetail(@PathVariable Long memberId, @RequestParam int year,
                                                           @RequestParam int month, @RequestParam int day) {
        return ResponseEntity.ok(diaryService.findGroupCanlendarDetail(memberId, year, month, day));
    }

/*
    // groupId로 그룹 캘린더 받아오는 api
    @GetMapping("/calendar2/group/{groupId}")
    public ResponseEntity<Object> findGroupCanlendarDetail2(@PathVariable Long groupId, @RequestParam int year,
                                                           @RequestParam int month, @RequestParam int day) {
        return ResponseEntity.ok(diaryService.findGroupCanlendarDetail2(groupId, year, month, day));
    }

 */

    // 일기 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Object> modifyDiary(@PathVariable(value = "id") long diaryId, @RequestBody DiaryCreateRequest diaryCreateRequest) {
        return ResponseEntity.ok(diaryService.updateDiary(diaryId, diaryCreateRequest));
    }

    // 일기 삭제
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteDiary(@RequestBody DiaryDeleteRequest diaryDeleteRequest) {

        int deleteCount = diaryService.deleteDiary(diaryDeleteRequest);
        if (deleteCount == 0) {
            return ResponseEntity.badRequest().body("일기 삭제를 실패했습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("일기 삭제 완료");//ok(null);
    }

// Spring boot - Flask 연동
    @GetMapping("/test")
    public String Test() {
        ModelAndView mav = new ModelAndView();

        String url = "http://43.202.124.240:5000/wise-saying";
        post(url, "");
        return "ok";
    }

    public void get(String requestURL) {
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
//            getRequest.addHeader("x-api-key", RestTestCommon.API_KEY); //KEY 입력
            HttpResponse response = client.execute(getRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    public void post(String requestURL, String jsonMessage) {
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpPost postRequest = new HttpPost(requestURL); //POST 메소드 URL 새성
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");
//            postRequest.addHeader("x-api-key", RestTestCommon.API_KEY); //KEY 입력
            //postRequest.addHeader("Authorization", token); // token 이용시
            //json 메시지 입력
            System.out.println("content: "+new StringEntity(jsonMessage, ContentType.APPLICATION_JSON));
            postRequest.setEntity(new StringEntity(jsonMessage, ContentType.APPLICATION_JSON));
            HttpResponse response = client.execute(postRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
