## JPA(Java Persistence API)

- Hibernate(Entity, Repository)라는 ORM 프레임워크를 이용해 구현된 자바의 표준 ORM 인터페이스 모음이 JPA이다.

```markdown
목차
1. ORM
2. JPA 동작 과정
3. JPA로 DB 모델을 설계하는 방법
4. JPA를 이용한 CRUD
5. JPA를 사용할 때의 장점
```

### 1. ORM(Object-Relational Mapping)

- 객체- 관계 매핑
- ORM은 데이터베이스 테이블(**관계**)을 객체로 매핑해주어 쿼리를 자동으로 생성해준다.
- 필드를 매핑하는 Mapper와 달리 관계를 객체에 반영하고자 한다는 차이점이 있다.



### 2. JPA 동작 과정

JPA는 JDBC와 애플리케이션 사이에서 동작한다.

개발자가 JPA를 쓰면, 내부적으로 JDBC API가 SQL 쿼리를 호출해 DB와 통신하며 요청한 데이터를 주고 받을 수 있다.

(JDBC는 Java가 제공하는 DB 접근을 가능하게 하는 API이다.)



### 3. JPA로 DB 모델을 설계하는 방법

**@Entity** , **JpaRepository**

- 다중성
  - @OneToOne
  - @OneToMany
  - @ManyToOne
  - @ManyToMany
  - 1:1 != n:1
    - 1:1 게시물을 조회하고 댓글 가져오기
    - 댓글 가져오고 어떤 게시물인지 확인
- 방향성
  - @JoinColumn
  - ObjectA -> ObjectB
  - ObjectB -> ObjectA
  - ObjectA <-> ObjectB
  - 양방향은 무한 참조가 발생할 수 있으니 주의할 것.
- 연관관계의 주인
  - @OneToMany(mappedBy = “boardUid”)
  - 양방향일 경우 한 테이블 기준으로 삭제를 진행할 때 모든 연관 데이터를 삭제해야 할까?
    - FK 키 관리는 주인이 하고
    - 1:N에서 N이 주인임.(@ManyToOne은 항상 주인이 된다)



**+) 설정할 것**

[Application.properties]

```mysql
spring.datasource.url="db URL"
spring.jpa.generate.ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show.sql=true
spring.jpa.properties.hibernate.format_sql=true logging.level.org.hibernate.type.descriptor.sql.BasicBinde=true 
```



**(꿀팁)** ID로 사용하는 값의 범위를 양수 범위로만 지정하면 데이터 용량을 줄이면서 더 많은 ID값을 쓸 수 있다.

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(columnDefinition = "INT UNSIGNED")
private int id;
```



### 4. JPA를 이용한 CRUD (Repository)

- Create : save

- READ : findAll(), find*ById(), find~ByIdOrderBy~Desc() ... 
- Delete : delete
- Update : find로 객체 찾기 -> 값 변경 (-> save) 



JPA Specification 등을 이용해 여러 항목들을 필터 검색할 수 있고 

파라미터로 원하는 기준의 Sort를 적용한 Pageable을 넘겨서 결과를 쉽게 페이징 처리할 수 있다.

(JpaRepository는 PagingAndSortingRepository를 extends받은 인터페이스)



몇몇 어노테이션을 사용해서 코드를 훨씬 간결하게 만들 수 있다.


@Builder, @Data(A shortcut for `@ToString`, `@EqualsAndHashCode`, `@Getter` on all fields, `@Setter` on all non-final fields, and `@RequiredArgsConstructor`), @NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor ...


```java
@Builder public Board(String userName, LocalDateTime createDate, String ip, String title, String contents, int how){
    this.userName = userName;
    this.createDate = createDate;
    this.ip = ip;
    this.title = title;
    this.contents = contents;
    this.how = how;
}
```

@Builder를 이용하면 
```java
board.save(Board.builder()
    .userName("홍길동")
    .title("아 호부호형 하고 싶다.")
    .contents("아버지를 아버지라 부르지 못하고 형을 형이라 부르지 못하다니 엉엉")
    .how(1)
    .build());
```
이런식으로 객체를 저장할 수 있다.

@Builder.default를 이용해 채우지 않은 속성의 기본값을 지정할 수 있으며, 지정하지 않은 경우 타입에 따라 0/ null/ false가 들어가게 된다.



### 5. JPA를 사용할 때의 장점

1. 객체 중심 개발

   - 기존 Mapper 사용 시 반복되는 SQL 코드 작성과 오브젝트를 SQL로 변환하고 RDB에 저장하는 과정은 객체지향과는 거리가 있다.

2. 생산성 향상

   - CRUD를 save, find~, object.get().set속성(""), delete 등으로 간단하게 처리할 수 있다.
     - insert문, select문, update문 등을 하나하나 작성하는 것보다 훨씬 쉽고 빠르다.

   - 위의 1번과 비슷한 맥락에서 repository에서 함수만 작성해도 바로 쿼리를 생성해주기 때문에 SQL 쿼리를 작성하는 것보다 더 빠르게 개발할 수 있기에 개발자는 다른 부분에 집중할 수 있어 생산적이다.

3. 유지보수

   - 새로운 필드가 추가되면 domain/entity에 필드를 추가하기만 하면 repository에서 바로 사용할 수 있다.

   - mapper에서는 추가된 필드와 관련된 모든 xml의 SQL을 수정해야 한다.

4. 성능 최적화

   - 캐싱
     - 같은 트랜잭션 내에서의 조회 성능이 향상되는 캐싱.
   - 버퍼링
     - 트랜잭션을 모아서 반영하는 write 지연
     - commit() 할 때 한번에 insert/ <u>update/ delete</u>를 반영하면서 효율성을 높인다.(<u>락 시간 최소화</u>)

### SpringBoot JPA Docs

  [Spring Boot JPA docs](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

+) 가장 좋은 참고문헌은 역시 언제나 공식문서!
