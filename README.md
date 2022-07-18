- https://github.com/roon-replica/KotlinMicroservices (blocking, 전통적인 개발 방법)에 이어서 
  
  이 레포지토리에서는 reactive한 방법을 다룸

# 이론
- 스프링 5을 이용해서 reactive microservice 만들기 쉽다고 함
- spring webflux는 tomcat 대신 Netty를 WAS로 사용

  스프링부트 2부터 non-blocking IO인 reactive 서비스를 위해 Netty를 선택

- webflux는 reactive stream pattern을 구현하기 위해 Reactor 프레임워크를 많이 쓴다고 함...
  
- [reactive-streams.org](https://www.reactive-streams.org/)

- 리액티브 애플리케이션의 layer
  - router: mvc에서 컨트롤러같은 역할
  - handler: 응답으로 변환하는 로직
  - service: 비즈니스 로직 캡슐화

### 정적 컨텐츠 제공하기
- blocking IO vs non-blocking IO
- https://techblog.bozho.net/why-non-blocking/

# 생각들
- non blocking, event driven 이런것들 다 자바스크립트에서 흔히 쓰이는 방식인데..<br/>
  리액티브 프로그래밍이라는게 서버도 자바스크립트처럼 비동기, 이벤트 모델 방식으로 쓰겠다는거 아닐까?


# 모르는 것들
- reactive stream pattern 모름
- reactor 프레임워크 모름
- event driven 잘 모름
- '리액티브하다'는 말의 뜻 잘 모름
- Mono, Flux는 실제로 만든 인스턴스가 아니라 앞으로 얻으려는 것에 대한 약속이다..? (구독 시 실행될 수 있다는 약속)
  
  확실히 이해 안됨. 어색함

# API 요청 예시
- 고객 조회
  ```javascript
   // url을 통해 요청
   /customer/{id}
  ```

- 고객들 조회
  ```javascript
   // url을 통해 요청
   /customers?nameFilter={nameFilter}
  ```

- 고객 생성 요청
  ```javascript
    var res = await fetch('/customer', options = {
      'method': 'POST',
      headers:{
        'Content-Type': "application/json;charset=utf-8"
      },
      body: JSON.stringify({ id: 10, name:'created name'})
    }).then(response => response.json())
  ```