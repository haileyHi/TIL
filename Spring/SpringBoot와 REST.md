## Spring Framework

자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크. 동적인 웹 사이트를 개발하기 위한 여러 가지 서비스를 제공한다. 

스프링의 장점은

```
1. 경량 컨테이너 -> 빠르게 빌드와 배포 가능
2. IoC(Inversion of Control : 제어의 역행) -> 낮은 결합
3. DI (Dependency Injection : 의존성 주입) -> 높은 응집
4. AOP (Aspect-Oriented Programming : 관점 지향 프로그래밍)
```
이 있다. 

스프링 프레임워크가 기능이 많은 만큼 환경설정이 복잡해 설정의 많은 부분을 자동화하여 사용자가 사용하기 편하게 만든 것이 스프링 부트이다. 

### Spring Boot
스프링 부트에서는 Starter Dependency 설정을 이용해 API를 정의할 수 있고, Tomcat을 내장하고 있어 별도의 톰캣 설치가 필요하지 않다.

java 기반의 configuration 파일(xml 로 설정하고 싶으면 읽어들이는 코드도 별도로 필요함)

application.properties에 세팅을 함.(maven 환경에서 / gradle 환경이면 applcation.yml) ← key value로 형식을 관리하는 파일 

자동으로 생기는 configuration 소스에는 **@SpringBootApplication**이 붙어있음.

이것은
**@EnableAutoConfiguration**, **@Configuration**, **@ComponentScan**
세가지를 합쳐 놓은 의미이다.

@RequestBody는 요청메소드의 몸체에 있는 걸 받아오겠다는 의미(Get방식에서는 쓸 일이 없다!)

객체를 직렬화된 string으로 쭉 보내고, 받아서 객체로 복원하고 싶을 때 RequestBody를 사용한다.

↔ ResponseBody는 반대 개념

RequestParam은 /index/{id} 이런 식

메소드에 사용되는 @ModelAttribute는, @RequestMapping 어노테이션이 적용된 메소드보다 먼저 호출된다.

- 그리고 @ModelAttribute 메소드 실행 결과로 리턴된 객체는, 자동으로 Model에 저장된다.
- 따라서 @ModelAttribute 메소드의 실행 결과로 리턴된 객체를 View 페이지에서 사용 할 수 있다.


### REST

Rest(**Re**presentational **S**tate **T**ransfer) 분산 네트워크 프로그래밍의 아키텍쳐 스타일

1. **리소스 식별**
서로 구분할 수 있는 개념으로 URI와 같은 고유 식별자를 통해 리소스를 표현할 수 있다.
2. **표현을 통한 리소스 처리**
동일한 내용의 데이터를 ****JSON**,** XML, HTML 페이지처럼 다양한 콘텐츠로 표현할 수 있다.
3. **자기 묘사 메시지**
헤더에 메타 데이터 정보를 추가해서 데이터에 대한 설명을 담을 수 있다.
4. **애플리케이션 상태에 대한 하이퍼미디어**
데이터와 링크 정보까지 포함하여 전달할 때 웹에 친숙한 API가 됨.

→ Rest 조건에 적합한 경우 RESTful 하다고 하고 이런 API를 REST API라 부른다.

→ 각각에 특화된 

GET방식 → resource 조회(@GetMapping)

POST ⇒ resource 등록 (@PostMapping) 

PUT ⇒ resource 수정(@PutMapping)

DELETE ⇒ resource 삭제(@DeleteMapping)

(@RequestMapping(value ="list", method = RequestMethod.POST) 이런 식으로 작성해도 됨.

어노테이션 기본적으로 컨트롤러가 요청의 url에 대응되는 매핑을 해준다.

`**@Controller**` `**@RestController` (← @Controller에 @ResponseBody** 만 추가 된 의미임)

→ restController가 반환하는 모든 값은 response body 로 처리한다는 뜻이 됨.

### **@PathVariable과 @RequestParam의 차이점.**

- PathVariable은 그 자체가 URL의 일부이면서 그 자체로 리소스를 식별할 수 있는 식별자
- RequestParam은 URL이 아니라 전달과정에서 건네는 매개변수 같은 역할.

ex) list/${xxx}

### CrossOrigin

클라이언트 → 프론트 → 우리 백엔드의 컨트롤러 ↔ 네트워킹 API로 타 도메인의 data 가져올 수 있다.

                                    ← 클라이언트 한테 보여주기( like **proxy** 기법)

(프론트에서 타 도메인으로 바로 비동기 데이터 요청 불가능)
