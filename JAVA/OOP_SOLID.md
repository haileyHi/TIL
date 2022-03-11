# SOLID

객체 지향 설계 5원칙

SRP(Single Responsibility Principle)단일 책임 원칙

OCP(Open Closed Priciple)개방 폐쇄 원칙

LSP(Liskov Substitution Priciple)리스코프 치환 원칙

ISP(Interface Segregation Principle)인터페이스 분리 원칙

DIP(Dependency Inversion Principle)의존 역전 원칙

## SRP
클래스와 메소드는 하나의 역할만 수행하도록 한다

## OCP
자신의 확장에는 열려있되 다른 클래스의 변화에는 폐쇄적이어야 한다

## LSP
> FUNCTIONS THAT USE POINTERS OR REFERENCES TO BASE CLASSES MUST BE ABLE TO USE OBJECTS OF DERIVED CLASSES WITHOUT KNOWING IT.

상위 클래스를 향한 포인터나 참조를 사용하는 메소드에 (이를 상속하는) 하위 클래스 객체를 사용하더라도 작동해야 한다.

하위 타입은 언제나 상위 타입으로 변환해도 동작에 문제가 없어야 한다
(서브 타입은 언제나 자신의 상위 타입으로 변환할 수 있어야 한다 -> 하위 타입이 상위 타입의 규칙을 지켜야 한다는 의미)

문제는 보통 override 하면서 하위 타입이 사용하는 메소드가 달라져서 발생한다. -> 상속을 받으면서 override를 안 할 수는 없으니

상속 할 때 하위 타입이 override하는 메소드에 새로운 기능을 추가하는 경우, **상위 타입의 기본 메소드의 의미를 해치지 않게 지키면서** 신중하게 추가해야 한다.

## ISP
인터페이스는 기능에 따라 작은 단위로 분리하고, 사용하지 않는 인터페이스는 구현하지 않는다
필요한 인터페이스만 구현해야 한다

## DIP
상위 타입은 하위 타입에 의존하면 안 된다. 의존 관계가 있는 클래스는 외부에서 주입하여야 한다.
