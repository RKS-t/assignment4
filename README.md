# ScheduleProject

### 프로젝트 소개

* 일정을 작성하는 기능을 구현하는 스프링 백엔드 프로젝트

* 회원가입, 로그인 기능을 통해 세션과 필터 기능 연습 목표

* 스프링 JPA에 대한 기본적인 사용법과 이해 목표


---
### Stacks

Environoment

 <img src="https://img.shields.io/badge/Intellij-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">

Development

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=&logoColor=white"> 

Frmaework

<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

DB

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 

Communication

<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-333333?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> 



---

### 주요 기능

* 회원가입, 로그인, 로그아웃 기능

* 회원, 일정 댓글 CRUD 기능
      

---
### 이 코드의 특징

* 필터를 통한 상황별 uri 통제

* 예외 핸들러를 통한 각종 예외 상황 통제

* 패스워드 인코더를 이용하여 비밀번호를 암호화하여 DB에 저장

* 세션을 이용한 안전한 로그인 기능 제공

* 멤버 인가 함수를 통한 작성자 구별

---
### API 명세서 

| 기능             | Method   | URL                              | request                                                                                                                                                             | response                                                                                                                                                                                                                                           | 상태코드     |
|:-----------------|:---------|:---------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------|
| 회원가입         | POST     | api/members/signup               | 요청 body <br>{<br>"email": “이메일”,<br>"password": "비밀번호",<br>"passwordCheck": "비밀번호 확인",<br>"username": "이름"<br>}                                    | 단건 응답 정보<br>{<br>"id": “멤버식별번호”,<br>"email": "이메일",<br>"username": "이름",<br>"createdAt": "생성날짜",<br>"modifiedAt": "수정날짜"<br>}                                                                                             | 201: CREATED |
| 멤버 조회        | GET      | api/members/{id}                 | 요청 pathvaluable<br>{id} in    /members/{id}                                                                                                                       | 단건 응답 정보<br>{<br>"id": “멤버식별번호”,<br>"email": "이메일",<br>"username": "이름",<br>"createdAt": "생성날짜",<br>"modifiedAt": "수정날짜"<br>}                                                                                             | 200: OK      |
| 로그인           | POST     | api/members/login                | 요청 body <br>{<br>"email" : "이메일",<br>"password" : "비밀번호"<br>}                                                                                              | 응답 메세지<br>{<br>"message": "로그인에 성공하였습니다."<br>}                                                                                                                                                                                     | 200: OK      |
| 로그아웃         | POST     | api/members/logout               | -                                                                                                                                                                   | 응답 메세지<br>{<br>"message": "로그아웃하였습니다."<br>}                                                                                                                                                                                          | 200: OK      |
| 멤버 정보 수정   | PATCH    | api/members/{id}                 | 요청 pathvaluable<br>{id} in   /members/{id}                                                                                                                        | 응답 메세지<br>{<br>"message": "회원정보가 수정되었습니다."<br>}                                                                                                                                                                                   | 200: OK      |
| 멤버 삭제        | DELETE   | api/members/{id}                 | 요청 pathvaluable<br>{id} in   /members/{id}                                                                                                                        | 응답 메세지<br>{<br>"message": "회원정보가 삭제되었습니다. 자동으로 로그아웃됩니다."<br>}                                                                                                                                                          | 200: OK      |
| 일정 저장        | POST     | api/plans                        | 요청 body<br>{<br>”name” : “이름”,<br>”date” : “일정 날짜”<br>”password” : “비밀번호”,<br>”contents” :  “일정 내용”<br>}<br>요청 pathvaluable<br>{id} in /post/{id} | 단건 응답 정보<br>{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"contents": 일정 내용",<br>"createdAt": "작성일",<br>"modifiedAt": "수정일",<br>"email": "작성자 이메일",<br>"username": "작성자 이름"<br>} | 201: CREATED |
| 모든 일정 조회   | GET      | api/plans                        | -                                                                                                                                                                   | 다건 응답 정보<br>[{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"createdAt": "작성일",<br>"username": "작성자 이름"<br>}]                                                                                  | 200: OK      |
| 이름별 일정 조회 | GET      | api/plans/name/{name}            | 요청 pathvaluable<br>{name} in  /plans/name/{name}                                                                                                                  | 다건 응답 정보<br>[{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"createdAt": "작성일",<br>"username": "작성자 이름"<br>}]                                                                                  | 200: OK      |
| 날짜별 일정 조회 | GET      | api/plans/date/{date}            | 요청 pathvaluable<br>{date} in  /plans/date/{date}                                                                                                                  | 다건 응답 정보<br>[{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"createdAt": "작성일",<br>"username": "작성자 이름"<br>}]                                                                                  | 200: OK      |
| 일정 조회        | GET      | api/plans/{id}                   | 요청 pathvaluable<br>{id} in   /plans/{id}                                                                                                                          | 단건 응답 정보<br>{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"contents": 일정 내용",<br>"createdAt": "작성일",<br>"modifiedAt": "수정일",<br>"email": "작성자 이메일",<br>"username": "작성자 이름"<br>} | 200: OK      |
| 일정 수정        | PATCH    | api/plans/{id}                   | 요청 pathvaluable<br>{id} in   /plans/{id}                                                                                                                          | 단건 응답 정보<br>{<br>"id": “일정식별번호”,<br>"title": "일정 제목",<br>"targetDate": "일정 날짜",<br>"contents": 일정 내용",<br>"createdAt": "작성일",<br>"modifiedAt": "수정일",<br>"email": "작성자 이메일",<br>"username": "작성자 이름"<br>} | 200: OK      |
| 일정 삭제        | DELETE   | api/plans/{id}                   | 요청 pathvaluable<br>{id} in   /plans/{id}                                                                                                                          | {<br>"message": "계획이 삭제되었습니다."<br>}                                                                                                                                                                                                      | 200: OK      |
| 댓글 저장        | POST     | api/plans/{planId}/comments      | 요청 pathvaluable<br>{planId} in   /plans/{planId}/comments<br>요청 body<br>{    <br>"contents": "댓글 내용"<br>}                                                   | 단건 응답 정보<br>{<br>"id": “댓글 식별 번호”,<br>"contents": "댓글 내용",<br>"createdAt": “작성일",<br>"modifiedAt": "수정일",<br>"username": "작성자 이름",<br>"email": "작성자이메일"<br>}                                                      | 201:CREATED  |
| 일정별 댓글 조회 | GET      | api/plans/{planId}/comments      | 요청 pathvaluable<br>{planId} in   /plans/{planId}/comments                                                                                                         | 다건 응답 정보<br>[{<br>"id": “댓글 식별 번호”,<br>"contents": "댓글 내용",<br>"createdAt": “작성일",<br>"modifiedAt": "수정일",<br>"username": "작성자 이름",<br>"email": "작성자이메일"<br>}]                                                    | 200: OK      |
| 댓글 수정        | PUT      | api/plans/{planId}/comments/{id} | 요청 pathvaluable<br>{planId}, {id} in   <br>/plans/{planId}/comments/{id}<br>요청 body<br>{    <br>"contents": "댓글 내용"<br>}                                    | 응답 메세지<br>{<br>"message": "댓글이 수정되었습니다."<br>}                                                                                                                                                                                       | 200: OK      |
| 댓글 삭제        | DELETE   | api/plans/{planId}/comments/{id} | 요청 pathvaluable<br>{planId}, {id} in   <br>/plans/{planId}/comments/{id}                                                                                          | 응답 메세지<br>{<br>"message": "댓글이 삭제되었습니다."<br>}                                                                                                                                                                                       | 200: OK      |                                                                                                                                                                         | 200: OK      |

  
  [api 명세서 링크 바로가기](https://www.notion.so/1ca1384f860280478341cbba3697830e?v=1ca1384f860281e98a51000c757f4f22)

---
### ERD

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdJCa6D%2FbtsM8Z0am7O%2Fflpo6jCylQrlIEYkTGe4N0%2Fimg.png)





### 디렉토리
<pre> 
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┗ 📂scheduleproject
 ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Const.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberValidator.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordEncoder.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlanController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DeleteMemberRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignUpRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateMemberRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂plan
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlanRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlanResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SinglePlanResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Comment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Plan.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginAuthException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MismatchException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MisMatchMemberException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NullResponseException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordCheckFailException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SamePasswordException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LoginFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MyExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlanRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlanService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PlanServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleProjectApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┃ ┗ 📂scheduleproject
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SchedulesProjectApplicationTests.java
  </pre>
 ----

 ### 개발일지기록 
 [티스토리블로그](https://rudtjs2.tistory.com/category/%EA%B3%BC%EC%A0%9C4)


