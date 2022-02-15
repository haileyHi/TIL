## 조건
여러 조건을 이용한 검색을 진행할 때, criteriaBuilder로 predicate 조건을 추가해서 Specification을 만들어 넘기자.

## 목표
### `@ManyToOne`속성을 이용한 predicate arraylist 만들기


```kotlin
val predicate: ArrayList<Predicate> = ArrayList<Predicate>()
```

### 1) attribute = value ( 같은지 비교하기)
- equal() method in criteriaBuilder

```kotlin
predicate.add(builder.equal(`expression_ (1)` , `expression or object_ (2)`))
```
로 검색 조건을 _주렁주렁_ 추가해줄 것이다.

`@ManyToOne`을 사용하는 속성은 특정 entity 그대로 요소가 추가되어있으므로 (1)에는 
root.get(key.getValue)로 검색하려는 조건은 enum class에 속성 엔티티명을 그대로 사용하고
.get("해당 엔티티에서 참조하고 있는 속성")으로 get을 두번 사용해서 접근하면 된다.
```kotlin
predicate.add(
    builder.equal(
        root.get<String>(key.getValue()).get<Int>("id"),
        searchKeyword.get(key).toString()
    )
)
```

### 2) attribute >= value 
- greaterThanOrEqualTo(`attribute to Search`, Integer value) method in criteriaBuilder

attribute <= value
- lessThanOrEqualTo(`attribute to Search`, Integer value)

```kotlin
predicte.add(builder.greaterThanOrEqualTo(root.get(key.value), 비교하려는 수)
```

### 3) attribute like "%키워드%"
- like() method in criteriaBuilder

usage)
```kotlin
predicate.add(builder.like(root.get(key.getValue()), "%" + searchKeyword.get(key) + "%"))
```

### Guide of using builder.or()
```kotlin
builder.or(
    /*first condition*/ builder.like( ),
    /*second condition*/ builder.like( )
)
```
