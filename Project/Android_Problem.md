## 1108
1. retrofit2, okhttp3 관련 에러 - 
CLEARTEXT communication to ~ not permitted by network security policy

원인 : [안드로이드 9.0 (API 28 이상)부터 일반 텍스트 트래픽 사용 요청 관련 기본값이 false임](https://developer.android.com/guide/topics/manifest/application-element#usesCleartextTraffic)

해결 방법 :

manifest 파일의 application에 android:usesCleartextTraffic=true 설정을 주면 임시로 해결할 수 있다.

그러나 *일반 텍스트 트래픽을 피하는 주요 이유는 기밀성, 진실성이 보장되지 않고 변조 방지가 불가능하기 때문입니다.* 라고 적혀있는 걸로 보아 true로 설정하는 것보다

res/network-security-config.xml에 

```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
      <domain includeSubdomains="true">요청하려는 API주소</domain>
    </domain-config>
</network-security-config>
```

방식을 사용하는 것이 더 안전한 해결 방법인 듯하다.

2. okhttp3 - HttpLoggingInterceptor 관련 에러

java.lang.NoSuchMethodError: No virtual method log(ILjava/lang/String;Ljava/lang/Throwable;)V in class Lokhttp3/internal/platform/Platform; 
~ okhttp3.logging.HttpLoggingInterceptor 라고 적혀있고 통신이 안 되는 경우

원인 : okhttp3 라이브러리 간의 버전 불일치. 

해결 방법 : gradle 파일의 okhttp3관련 라이브러리의 버전을 잘 확인 하고 통일시켜주자.

[okhttp](https://square.github.io/okhttp/) 사이트에서 release 버전으로 모든라이브러리 버전을 통일시켜주니 사라졌다.

안드로이드 스튜디오가 4.9.1로 버전을 올릴 수 있다고 말해줬지만 4.9.1로 세개 다 했다가 안 됐으므로 현재 사이트에 나와있는 4.9.0으로 통일 시켜주니 제대로 작동됐다.

```
// okhttp
    implementation "com.squareup.okhttp3:okhttp:4.9.0"
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0' // interceptor 생성
    implementation "com.squareup.okhttp3:okhttp-urlconnection:4.9.0" // 쿠키 유지
```

3. API 주소를 git에 올리지 않고 사용하는 방법.

1. .Project level에 keystore.properties 파일을 추가

```
URL = "{{서버url}}"
```

2. .gitignore에 keystore.properties 추가

3. app 단위 gradle에 

```gradle
android {
    def keystorePropertiesFile = rootProject.file("keystore.properties")
    def keystoreProperties = new Properties()
    keystoreProperties.load(new FileInputStream(keystorePropertiesFile))
}
```

추가하고 defaultConfig안에 buildConfigField "String", "BASE_URL", keystoreProperties["URL"]

를 사용하면 server 연결할 때 BuildConfig.BASE_URL로 주소를 대체할 수 있다.

