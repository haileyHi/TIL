## TCP vs UDP

TCP는 연결형 프로토콜, UDP는 비연결형 프로토콜.

TCP는 전송 계층에서 사용하느 프로토콜로 하위 계층(네트워크 계층)의 프로토콜인 IP 프로토콜과 함께 TCP/IP라고 표현한다.

TCP 연결 시에는 3 Way 핸드쉐이크 방식을, 해제 시에는 4 Way 핸드쉐이크를 이용한다.

흐름 제어, 혼잡 제어 서비스를 통해 데이터를 확인하며 신뢰성 있는 데이터 송수신을 지원한다.

UDP는 비연결형 프로토콜이기 때문에 연결과 해제를 위한 별도의 과정을 거치지 않고, TCP와 같은 흐름 제어나 혼잡 제어를 통한 데이터 신뢰성을 체크하지 않기 때문에 신뢰성은 떨어지지만, TCP보다 전송에 사용하는 헤더의 크기도 작고, 빠른 데이터 전송을 할 수 있다. 



따라서 UDP는 멀티미디어 스트리밍, 인터넷 전화, 게임 등 지연시간이 발생하지 않는 것이 더 중요한 서비스에 사용되고, TCP는 HTTP, HTTPS, 이메일, 파일 전송 등 신뢰도와 순서대로 데이터가 도착하는 것이 중요한 서비스에 사용된다. (채팅도 순서가 중요하기 때문에 TCP)
