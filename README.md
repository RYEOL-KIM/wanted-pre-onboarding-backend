# 원티드 프리온보딩 백엔드 인턴십 - 선발 과제

## 목차

- [지원자 소개]
- [어플리케이션 실행 방법]
- [배포된 AWS 엔드포인트 주소로 호출하는 방법]
- [데이터베이스 테이블 구조]
- [구현한 API의 동작을 촬영한 데모 영상 링크]
- [구현 방법 및 이유에 대한 간략한 설명]
- [API 명세(request/response 포함)]


<br>
<br>

## 👨‍💻지원자 소개

### 이름 : 김득렬

### 이메일 : rlaemrfuf043@gmail.com



<br><br>

## ✨어플리케이션 실행 방법

이 프로젝트는 Java/Spring 프로젝트로 구성되어 있습니다. 
다음은 프로젝트를 AWS EC2 인스턴스에 클론하고 실행하는 방법입니다.

#### 1. 프로젝트를 클론합니다.
~~~
git clone https://github.com/your-username/your-project.git
~~~

#### 2. 프로젝트 디렉토리로 이동합니다.
~~~
cd your-project
~~~

#### 3. 프로젝트를 빌드합니다.
~~~
./gradlew build
~~~

#### 4. 빌드된 어플리케이션을 실행합니다.
~~~
java -jar build/libs/<어플리케이션 파일 이름>.jar
~~~

#### 5. 엔드포인트 주소가 활성화되었습니다. 웹 브라우저나 API 클라이언트를 열어 다음 엔드포인트에 접속합니다.
~~~
http://<EC2 인스턴스 IP 주소>:<포트 번호>/<엔드포인트 경로>
~~~
<br><br>

## 💡배포된 AWS 엔드포인트 주소로 호출하는 방법

### ⭐ AWS 배포 IP 주소
~~~
http://52.78.202.239:8080/<엔트포인트 경로>
~~~

### ⭐ 엔드포인트 호출 방법
> 각 방법의 request, response는 아래의 API 명세서를 참고바랍니다

- 회원가입
    - POST `/api/v1/users/join`
- 로그인
    - POST `/auth/login`
- 게시글 생성
    - POST `/api/v1/posts`
- 게시글 전체 목록 조회
    - GET `/api/v1/posts`
- 게시글 상세 조회
    - GET `/api/v1/posts/{post-Id}`
- 게시글 수정
    - PATCH `/api/v1/posts/{post-Id}`
- 게시글 삭제
    - DELETE `/api/v1/posts/{post-Id}`



<br><br><br><br>

## 🖼️데이터베이스 테이블 구조

<br>

### 구조 다이어그램
![다이어그램](https://github.com/RYEOL-KIM/wanted-pre-onboarding-backend/assets/124750905/b29df68e-cae2-4ff7-8e94-010544578ded)



<br><br><br><br>

## 🖥️구현한 API의 동작을 촬영한 데모 영상 링크

<br>

### https://youtu.be/z3vx7V6vsR4



<br><br><br><br>



## 💡구현 방법 및 이유에 대한 간략한 설명

1. 사용자 요청은 Filter(Security)를 거쳐 사용자 권한을 확인한 후 해당 Controller로 전달됩니다.
2. 권한이 확인된 사용자의 요청 DTO는 service에서 처리되며, 데이터 예외 처리 후 Repository를 통해 저장됩니다.
3. 저장된 데이터는 Database에서 추출되어 사용자에게 반환되며, 정상적인 경우 결과 값이 표시됩니다.
---



<br><br><br><br>

## 🧱API 명세(request/response 포함)


### POSTMAN API 명세서 링크

- https://documenter.getpostman.com/view/26572081/2s9Xy6rALi


### 과제 1. 사용자 회원가입 엔드포인트

### Request
- POST `/api/v1/signup`
  ```json
    {
      "email": "user1@gmail.com",
      "password": "password"
    }
  ```
### Response
- 200 OK

  ```

  회원가입이 성공적으로 이루어졌습니다.
  
  ```

### 과제 2. 사용자 로그인 엔드포인트

### Request
- POST `/auth/login`
  ```json
  {
      "email": "user1@gmail.com",
      "password": "password"
  }
  ```
### Response
- 200 OK
- Headers
    ```
    Authorization Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidXNlcjFAZ21haWwuY29tIiwic3ViIjoidXNlcjFAZ21haWwuY29tIiwiaWF0IjoxNjkyMTY0OTEwLCJleHAiOjE2OTIxNjY3MTB9.eXZEioDNGmaFrCmJB7w-Lhrt59KHpysY7bWZ_tR_zrJCDHe4lqTdJLZIYIGUvJHj
  ```


### 과제 3. 새로운 게시글을 생성하는 엔드포인트

### Request
- POST `/api/v1/posts`

### Response
- 200 OK
  ```json
  
    {
      "postId": 1,
      "userId": 1,
      "email": "user1@gmail.com",
      "title": "게시글의 제목 입니다",
      "content": "게시글의 내용 입니다",
      "message": "게시글 등록에 성공하였습니다"
    }

  ```
  

### 과제 4. 게시글 목록을 조회하는 엔드포인트

### Request
- GET `/api/v1/posts`

### Response
- 200 OK

    ```json
    {
      "data": [
        {
          "postId": 1,
          "userId": 1,
          "email": "user1@gmail.com",
          "title": "게시글의 제목 입니다"
        },
        {
          "postId": 2,
          "userId": 1,
          "email": "user1@gmail.com",
          "title": "게시글의 제목 입니다"
        },
        {
          "postId": 3,
          "userId": 1,
          "email": "user1@gmail.com",
          "title": "게시글의 제목 입니다"
        },
        {
          "postId": 4,
          "userId": 1,
          "email": "user1@gmail.com",
          "title": "게시글의 제목 입니다"
        },
        {
          "postId": 5,
          "userId": 1,
          "email": "user1@gmail.com",
          "title": "게시글의 제목 입니다"
        }
      ],
      "pageInfo": {
        "page": 1,
        "size": 8,
        "totalElements": 5,
        "totalPages": 1
      }
    }
  ```
    

### 과제 5. 특정 게시글을 조회하는 엔드포인트
### Request
- GET `/api/v1/posts/{postId}`

### Response
- 200 OK
  ```json
    {
      "postId": 1,
      "userId": 1,
      "email": "user1@gmail.com",
      "title": "게시글의 제목 입니다",
      "content": "게시글의 내용 입니다"
    }
  ```
  

### 과제 6. 특정 게시글을 수정하는 엔드포인트

### Request
- PATCH `/api/v1/posts/{postId}`

  ```json
    {
      "title": "수정된 제목 입니다",
      "content" : "수정된 내용 입니다"
    }
  ```

### Response
- 200 OK
  ```json
    
    {
      "postId": 1,
      "email": "user1@gmail.com",
      "title": "수정된 제목 입니다",
      "content": "수정된 내용 입니다",
      "message": "게시글 수정에 성공하였습니다"
    }
  ```
  

### 과제 7. 특정 게시글을 삭제하는 엔드포인트
### Request
- DELETE `/api/v1/posts/{postId}`


### Response
- 200 OK

  ```
    게시물 삭제에 성공하였습니다
  ```
