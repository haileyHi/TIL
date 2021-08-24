# 1182번 부분수열의 합
[문제 보러가기](https://www.acmicpc.net/problem/1182)

## 🅰 설계
조건 확인
- N개의 주어진 수에서 몇 개의 부분수열의 합이 S를 만족하는 지 구하기 

부분수열을 구하기 위해 비트마스크를 사용하였다.
```java
int cnt = 0;
for (int i = 1 ; i< (1 << N) ; i++){
// 부분수열 돌리기
int sum = 0;
    for (int j = 0 ; j < N; j++){
    // 경우의 수 돌리기
        if ((i & (1 << j)) != 0){
            // 부분수열에 해당하는 원소와 체크하려는 수가 같은 위치에 있다면 &연산 결과가 1일테니 sum에 더하기
            sum += array[j]; // j번째에 해당하는 수가 포함된 부분수열 합 구하기
        }
    }
}
```

## ✅ 후기

부분 수열 구하기는 한 번 익혀두면 여기저기에 편하게 사용할 수 있어서 좋다.😇
쉬어가는 문제를 풀었으니 오늘은 시뮬레이션도 한 문제 풀어야겠다.