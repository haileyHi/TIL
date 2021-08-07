# 14569번 시간표 짜기
[문제 보러가기](https://www.acmicpc.net/problem/14569)

## 🅰 설계

- 총 과목의 수 n개( 3 <= N <= 1000)
- 신청 가능한 시간과 시간표 보고 수강 가능한 후보 개수 세기 

비트 연산의
0 | 1 = 1
0 & 1 = 0
1 & 1 = 1
의 성질을 이용해서 몽땅 for문을 돌면서 풀지 않고 쉽게 접근할 수 있다.

1. 시간표 입력되는 대로 `시간표 정보 |= (1 << 시간)`로 표시 후 lectures 배열에 담는다.
2. 학생의 공강 역시 `ableTime |= (1 << 공강시간)`으로 모두 표시하기
3. 과목 개수만큼 돌면서 각 학생의 `공강시간 & 시간표 == 시간표`인지 확인하고 후보 cnt 증가시키기

로 접근해서 풀이로 옮겼다.

비트 마스킹으로 시간표 입력은 아래처럼
```java
for (int j = 0; j < cnt; j++) {
    long tmp = Integer.parseInt(st.nextToken()) - 1;
    time |= (1 << tmp);
}
```

이렇게 코드를 짜면 모든 시간표를 담을 걸 고려해서 long으로 지정해놓고 32비트만 사용하는 방법이 된다.😅 

무서운 습관에 의해 손에 익숙한대로 작성하게 되면 tmp만 long이고 연산에 사용되는 부분이 결국 `(1 << tmp)` int 타입이기 때문!

이에 유의하며 1L로 바꾸어주자.


### 전체코드

```java
public class BOJ_Timetable_14569 {
    private static int N;
    private static int STU_CNT;
    private static long[] lectures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        lectures = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            long time = 0;
            for (int j = 0; j < cnt; j++) {
                long tmp = Long.parseLong(st.nextToken()) - 1;
                time |= (1L << tmp);
            }
            lectures[i] = time;
        }
        STU_CNT = Integer.parseInt(br.readLine());

        for (int i = 0; i < STU_CNT; i++) {
            int res = 0;
            st = new StringTokenizer(br.readLine());
            int ableTime = Integer.parseInt(st.nextToken());
            long able = 0;
            for (int j = 0; j < ableTime; j++) {
                long empty = Long.parseLong(st.nextToken()) - 1;
                able |= ( 1L << empty);
            }
            // 저장된 과목별로 학생 able 과 연산해서 학생 공강 & 수업 시간표 !
            for (int check = 0; check < N; check++) {
                if ((lectures[check] & able) == lectures[check]) {
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}
    

```

## ✅ 후기

위에 작성했듯 long 타입을 int로 받지 않도록 (1L << (시간))으로 연산해야함에 유의!

1 << 시간 은 32비트에서 범위 초과로 오류가 발생하게 된다.

재미있는 비트 마스킹 연습용 문제였다.
