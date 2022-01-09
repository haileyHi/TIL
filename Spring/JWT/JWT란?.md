JWT 내용 정리 -> (https://jwt.io/introduction)

### JWT signature

JWT가 주고 받는 대상에게 안전성을 제공하기 위해서 암호화된다고도 할 수 있지만 동시에 *서명된* 토큰이라는 점에 주목해보자. 

서명된 토큰은 그 내부에 있는 claims의 무결성을 증명할 수 있으면서 동시에, 암호화된 토큰의 내용은 다른 제3자에게 숨길 수 있기 때문이다. 

만약 토큰이 public-private 키 쌍으로 서명되었다면 서명을 한, private 키를 가진 측에서만 해당 서명을 증명할 수 있다.

## When should you use JSON Web Tokens?


### Authorization(인가)

다음은 JWT를 사용하는 보편적인 상황이다. 
사용자가 한 번 로그인 한 이후, 사용자가 경로 이동, 서비스, 자신의 토큰으로 요청받은 자원에 대한 접근을 허락받기 위해 각 연속적인 요청들은 JWT를 포함하고 있을 것이다. 
SSO(Single Sign On)은 적은 오버헤드와 여러 도메인에서 범용적으로 사용할 수 있는 가능성 때문에  JWT를 넓게 사용하기 위한 기능으로 사용되고 있다.

### Information Exchange(정보 교환)

JSON Web Tokens는 안전하게 주체간 정보를 전달하기에 좋은 방식이다. 
JWT에 서명(public/ private 키 쌍을 이용하는 등)을 하므로 보내는 사람이 누구인지 스스로 밝혀 송신자의 정체를 알 수 있다. 
또한, 서명은 헤더와, 페이로드를 이용해서 계산되므로, 우리는 내용의 무결성을 입증할 수 있다.

## What is the JSON Web Token structure?

토큰의 세 부분은 `.`으로 분리되어있다.

🗝 => `Header.Payload.Signature`

#### Header

Header에는 토큰의 타입과 서명에 사용된 알고리즘에 대한 정보가 담겨 있다. 

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```



#### Payload

토큰의 두번째 파트는 payload로, claims를 포함하고 있는 부분이다. Claims는 객체(주로 사용자에 대한 정보)와 부가적인 데이터에 대한 선언문이다. Claims에는 세 종류가 있다. 

- registered
- public
- private

##### 1. 등록된 claim

미리 선언된 claim세트로 직접 만든 것은 아니지만, 사용하기 쉽고 운영체제 독립적인 claims 세트를 제공하기 위해 권장된다. 이 registered claims의 종류로는

- iss(issuer), exp(expiration time) 만료 시간, sub(subject) 주체, aud(audience) 관중 + ⍺



```text
✅ JWT가 축약됐다는 점에서 의미가 있기 때문에 claim의 이름은 3글자미만이어야 한다.
```



##### 2. 공동 claim

- 충돌을 피하기 위해 00에 선언되거나 충돌 방지 이름 영역을 소유한 URI로 선언되어 있어야 한다.



##### 3. Private claims

이 클레임은 커스텀으로, 서로 private 으로 주고 받기로 동의한 주체들 사이에서 정보를 공유하기 위해 생성되었다.  ( 각 주체는 registered/ public claims를 사용하지 않고 둘 다 private claims를 사용해야 한다.)

```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```
