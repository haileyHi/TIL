# 1074번 Z
[문제 보러가기](https://www.acmicpc.net/problem/1074)

## 🅰 설계
z 순서로 방문하는 경우에 2차원 배열 (r행, c열)에 도달하는 순서 구하기.

같은 패턴이 반복되고 있으니 재귀로 풀어주도록 하자.📐

최소 단위는 2 * 2 일 때이고
```
0 1
2 3
```
순서로 방문하므로 재귀 함수의 기저 조건을 (n == 2)일 때 `return r * 2 + c`로 먼저 설정해주었다.

그 다음은 전체 n * n 배열의 어느 부분에 위치했는지에 따라서 값을 다르게 더해주도록 한다.

n은 2의 배수이므로 `int half = n/2;`를 이용해서

위에서 표시했던 방문 순서에 따라서 
- 0번 구역에 있는 경우 (r < half, c < half)
- 1번 구역에 있는 경우 (r < half, half <= c < n)
- 2번 구역에 있는 경우 (half <= r < n, c < half)
- 3번 구역에 있는 경우 (half <= r < n, half <= c < n)

로 나눌 수 있다.

1번 구역에 있는 경우라면 r행 c열에 방문하는 순서는 `0번 구역의 칸 개수 + 1번 구역 내에서 (r, c - half)의 순서`가 된다.

2번 구역도 마찬가지로 `0번, 1번 구역의 칸 개수 + 2번 구역 내에서 (r - half, c)의 순서`가 될 것이다.

작은 구역 내에서 n은 half로 지정해서 다시 계산해주면 된다!

따라서 재귀 함수는 다음과 같다.

```java
private static int search(int n, int r, int c) {
    if (n == 2) {
        return r * 2 + c;
    }
    int half = n/2;
    if (r < half) {
        if (c < half) {
            return search(half, r, c);
        } else {
            return half * half + search(half, r, c - half);
        }
    } else {
        if (c < half) {
            return n * n / 2 + search(half, r - half, c);
        } else {
            return half * half * 3 + search(half, r - half, c - half);
        }
    }
}
```

처음에 입력받은 N을 2^N으로만 변환해서 함수에 넣어주면 된다.

## ✅ 후기

요 며칠 컨디션이 안 좋아서 정신이 없었는데 오랜만에 재귀를 풀어보니 재미있었다.
