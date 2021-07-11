import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        int tmp = 1;
        int prev = 0;
        dp = new int[N + 1];
        setArr(N);
        for (int i = 0; i < cnt; i++) {
            int a = Integer.parseInt(br.readLine());
            //연속으로 이어진 좌석에 올 수 있는 가짓수의 곱.
            tmp *= dp[a - prev - 1];
            prev = a;
        }
        //마지막으로 입력된 고정좌석이 맨 끝 번호가 아니라면 마지막 좌석들에 올 수 있는 가짓수 곱해주기.
        if(prev != N) {
            tmp *= dp[N - prev];
        }
        System.out.println(tmp);
    }

    private static void setArr(int N) {
        dp[0] = dp[1] = 1;
        if (N < 2) return;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }
}
