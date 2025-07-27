# 🛠️ BackEnd - SpringBoot
## 📐 ERD 설계
<img src="https://github.com/user-attachments/assets/d860c11a-3793-4fbb-a282-0912b65864d6" width=100%/>

## 🧩 주요 관계 설명
- 이 프로젝트의 데이터베이스는 회원, 일기, 감정, 댓글, 좋아요, 일기 분석 결과(감정 결과, 음악, 명언)으로 구성되어 있습니다.
- 1️⃣ 회원 (members)
  - 하나의 회원은 여러 개의 **일기(diaries)**를 작성할 수 있습니다.
  - 하나의 회원은 여러 개의 **댓글(comments)**을 작성할 수 있으며,
  - 여러 개의 **좋아요(likes)**를 남길 수 있습니다.
- 2️⃣ 일기 (diaries)
  - 하나의 일기는 하나의 **주요 감정(mood_emojis)**을 가집니다.
  - 하나의 일기는 여러 개의 댓글과 좋아요를 받을 수 있습니다.
  - 하나의 일기는 감정 결과, 음악, 명언을 하나씩 가질 수 있습니다.

- 3️⃣ 일기 분석 결과(mood_analysis_result, music_playlists, quotes)
  - 감정 결과(mood_analysis_result)는 6가지(기쁨, 당황, 분노, 불안, 상처, 슬픔)가 속성으로 들어갑니다.
 
