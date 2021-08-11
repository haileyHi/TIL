# 9019번 괄호
[문제 보러가기](https://www.acmicpc.net/problem/9019)

## 🅰 설계

주어진 수 `from`부터 `to`까지 방문할 수 있는 모든 상황에서(D,S,L,R) 조건을 확인하고 queue에 하나씩 담은 다음 연산을 수행한다.

현재 숫자에서 DSLR 연산을 적용한 수가 방문하지 않은 수라면 현재 수 만들기까지의 문자 + "해당연산자 char" 이므로

queue에 연산을 적용한 수, 그 수를 만들기까지의 연산자들을 넣어주었다.

`Queue<Object[]> queue`를 사용하고 별도의 클래스를 사용하지 않음.

방문 여부는 `boolean visited`로 해결했다.


## ✅ 후기

- StringBuilder를 사용하려고 했지만 StringBuilder에서 .append는 계속 누적되어 사용하지 않았다.
