### 코틀린 기초

1. 변수와 상수, 기본자료형, 메소드

```kotlin
fun main() {
  // 기본 입 출력
    println("Hello world")

    // 변수 var
    var x = 10 // var x 는 곧 int 형 변수
    // 코틀린은 타입을 명시하지 않아도 할당된 값으로 타입을 추론하는 기능이 있어서 타입 명시를 안 해도 쓸 수 있다.
    var sayHi = "Hello"
    var isMarried = true
    // 이런 식으로 다양한 변수들을 할당해서 사용할 수 있음.

    var a: Int? = null // 선언과 동시에 초기화를 해주어야 한다.
    println(a) // null
    a = 10
    println(a) // 10
  
    // 널을 허용하지 않는 속성으로 생성자를 나중에 초기화하는 경우 lateinit var를 사용할 수 있다. 사용 전에 반드시 초기화해야 예외가 발생하지 않는다.
    
    // 상수 val
    val b = 10
		//b = 20(x) <- 상수는 변경이 안 됨.

    // 출력에서 특정 변수를 가져다 쓰고 싶다면 $를 앞에 붙이면 되고
  	// 수식을 사용하고 싶다면 ${}의 대괄호 안에 적어주면 된다.
		println("$sayHi world => ${b * 20}") // Hello word => 200

    // println(myMethod(4,5))
}

// 함수는 이렇게 만들 수 있다.
fun myMethod(a: Int, b: Int):Int = a + b

fun voidMethod(a: Int, b: Int)/*:Unit*/ { 
    print(a + b)
}

```

코틀린은 가능한 모든 널 값을 제거하기 때문에 특정 변수에 널이 아닌 값만을 할당하고 싶다면

물음표 없이 변수를 정의하면 된다.

`var name: String`

-> name에 널을 할당하면 컴파일 되지 않는다.



### 널 허용 타입

안전 호출 연산자(?.)나 엘비스 연산자(?:)와 함께 사용가능

특정 변수 Charact의 속성이 String? 타입인 경우를 생각해보자.

``` kotlin
class Charac(	val name: String,
             	val race: String,
            	val weapon: String?)

val frodo = Charac("Frodo", "Hobbit", "The One Ring")
val sam = Charac("Sam", "Hobbit", null)
val gandalf = Charac("Gandalf", "Wizard", "Glamdring ")
val gimli = Charac("Gimli", "Dwarf", "ax")
```

null이 아닌 값을 가지고 있는지 smart cast를 하기 위해서 검사를 한다면.

```kotlin
if (frodo.weapon != null){
  val weaponPower = frodo.weapon.length
}
```

이런 if문을 이용해 weapon이 null이 아닌 경우에만 값을 읽기 때문에 사실은 String?타입인 weapon의 값을 String으로 보장해서 가져올 수 있다.

만약 프로도가 var 타입으로 선언되었다면 if 문 내부는 이렇게 바꾸어 주어야 한다.

```
if (frodo.weapon != null){
  val weaponPower = frodo.weapon!!.length
}
```

