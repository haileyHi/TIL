import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] val = new int[n][n]; // 값 저장용
        int[][] dp = new int[n][n];
        for (int i = 0; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j<= i; j++){
                val[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = val[0][0];
        int max = dp[0][0];// 1개라면 이 값이 최대 경로 값
        for (int i = 1; i< n; i++){
            for (int j = 0; j<= i; j++){
                if (j == 0) {
                    dp[i][j] = val[i][j] + dp[i-1][j];
                }
                else if (i == j){
                    dp[i][j] = val[i][j] + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = val[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
                if (i == n -1){
                    max = Math.max(dp[i][j] , max);
                }
            }
        }
        System.out.println(max);

    }
}
