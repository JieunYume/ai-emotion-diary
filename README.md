# AI 감정 다이어리(AI Emotion Diary)
<img width="1616" height="778" alt="image" src="https://github.com/user-attachments/assets/48f5738b-a50f-4912-8b89-becc2838390f" />
- 📝 감정을 솔직하게 적으면,
- 🤖 AI가 오늘의 감정에 어울리는 명언·공감 메시지·음악을 추천하는 다이어리 앱입니다.

## 🏆 프로젝트 성과
- 🥉 **2023년 한이음 ICT멘토링 공모전 입선**
- 📄 **한국정보처리학회 ACK 2023 논문 게재**
  - [논문 바로가기 🔗](https://kiss.kstudy.com/Detail/Ar?key=4059511)
- **KBS MNC팀 사내 매거진(2024년 3월호) 기고문 게재**
<div align="center"> <img src="https://github.com/user-attachments/assets/feb16b1d-aa1e-44e1-aaf3-09037231a013" width="300"/> <img src="https://github.com/user-attachments/assets/a9b9449b-3871-4939-862a-04678f406dea" width="300"/> <img src="https://github.com/user-attachments/assets/64b87514-be55-4d06-843a-9d921059f1e7" width="300"/> </div>

## 포스터(부산대학교 생명자원과학대학 캡스톤디자인 경진대회 참가)
<div align="center"> <img src="https://github.com/user-attachments/assets/96055e45-4221-4ef2-be27-dbae33f2a429" width="500"/> </div>

## 🎬 시연 영상
<a href="https://www.youtube.com/watch?v=qILtQAYQ0WI" target="_blank">
  <img src="http://img.youtube.com/vi/qILtQAYQ0WI/0.jpg" width="800"/>
</a>

## 🖥️ 최종 구현 화면

https://github.com/user-attachments/assets/ef5c98e5-4634-4006-810f-9da27166c145

<img src="https://github.com/user-attachments/assets/3767a3e2-7484-4bdd-928a-54747e9c4052" width="300"/>
<div align="center">
  <img src="https://github.com/user-attachments/assets/bfa8cfad-b2d3-49f7-85bf-69788ac45b22" width="200"/>
  <img src="https://github.com/user-attachments/assets/38ab3d3c-a799-41ca-bd47-98bd1ce8d737" width="666"/>
</div>






## 💡 대표 기능
<img width="1716" height="281" alt="image" src="https://github.com/user-attachments/assets/9bf6f6bf-7a9b-4b15-a681-e799a1f0cdd4" />
- 기록: 사용자가 양식대로 ‘감정 일기’를 작성한다.
- 공유: 나의 일상을 친구들과 ‘공유’한다
- 진단: 일기를 바탕으로 ‘AI가 감정을 분석’해 공감의 말을 전한다.
- 보상: 일기를 기록하면 ‘농작물’이 성장하고, 수확한 농작물로 상대방의 일기에 ‘좋아요’를 누르는 등 참여할 기회가 많아진다.
- 분석: 개인별, 그룹별 분석된 감정의 변화 추이 ‘시각화 그래프’를 제시한다.
- 공감: 그룹원들에게 ‘댓글’을 남기거나 ‘좋아요’를 누를 수 있다.
- 음악 추천: 분석한 감정을 바탕으로 적합한 ‘노래’를 추천한다.
- 명언 추천: 분석한 감정을 바탕으로 적합한 ‘명언’을 추천한다.

## ⚙️ 핵심 기술
### 1️⃣ 시스템 흐름도
<img width="1570" height="842" alt="image" src="https://github.com/user-attachments/assets/9e81102d-bf7f-4413-90f6-0a4f23f62192" />
- 사용자가 **Android**에서 일기를 작성하여 API를 요청한다.
- **Spring Boot 서버**가 요청에 응답하고, **Flask 서버**로 분석 요청을 전달한다.
- **Flask 서버**는 AI 모델을 통해 감정을 분석하고, 분석 결과를 바탕으로 음악/명언/공감의 말 데이터를 응답한다.

### 2️⃣ AI 및 추천시스템
- 🧠 감정 분석: 사용자의 일기 내용을 NLP(Natural Language Processing)와 LSTM(Long Short-Term Memory) 기반의 딥러닝 모델을 통해 감정 분류를 수행합니다.
- 💬 명언 추천: 감정 분석 결과와 명언 데이터셋 간의 코사인 유사도를 계산하여, 가장 적절한 명언을 추천합니다.
- 🎵 음악 추천: 분석된 감정을 바탕으로 FLO API를 활용하여 사용자에게 어울리는 음악을 제공합니다.
- 🤝 공감의 말 생성: 사용자의 감정에 공감하는 메시지는 ChatGPT(GPT-3.5 기반) 생성형 언어 모델을 통해 생성됩니다.


<img width="1401" height="795" alt="image" src="https://github.com/user-attachments/assets/1777d472-aa1c-4221-b7b4-fefd49013472" />

## 📚 프로젝트 완료 후기
![202403월호-이미지-5](https://github.com/user-attachments/assets/68709b98-9589-47f5-8206-991acc62cf0f)


