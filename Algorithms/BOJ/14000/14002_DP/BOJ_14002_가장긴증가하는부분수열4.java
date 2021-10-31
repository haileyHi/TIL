import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringBuilder[] strArr = new StringBuilder[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i< N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            strArr[i] = new StringBuilder();
        }
        int[] dp = new int[N+1];

        int max = 1, maxIdx = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1; // arr[i]를 가장 큰 수로 가지고 있는 부분 증가 수열의 길이
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[i] < dp[j] + 1) { // dp[i] i == 4 , j = 0 30 j = 1 dp[4] = 1, dp[1] = 10 ( + 1 == 2) 10
                        dp[i] = dp[j] + 1;
                        strArr[i].append(arr[j]).append(" ");
                    } else if (dp[i] == dp[j] + 1){ // 이 경우 j가 i보다 더 작은 숫자일 수 있으므로 setArr[j]로 처음부터 다시 붙이기 위해서 
                        strArr[i].setLength(0);
                        strArr[i].append(strArr[j].substring(0, strArr[j].length() - 1)).append(" ");
                    }

                    if (max < dp[i]) {
                        max = dp[i];
                        maxIdx = i;
                    }
                }
            }
            strArr[i].append(arr[i]).append(" ");
        }

        System.out.println(max);
        System.out.println(strArr[maxIdx]);
    }
}
