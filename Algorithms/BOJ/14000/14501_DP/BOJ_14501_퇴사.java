import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] price = new int[N+1];
        int[] task = new int[N+1];
        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int t = sc.nextInt();
            int p = sc.nextInt();
            task[i] = t;
            if(task[i] + i - 1> N){
                continue;
            }
            price[i] = p;
        }
        int max = -1;
        for (int i = 1; i<= N; i++){
            for (int j = 1; j < i ; j++){
              // j 일에 업무를 해서 i에 끝나는 경우
                if (i >= j + task[j]) {
                    // j날 상담 하는 경우
                    dp[i] = Math.max(dp[j] + price[i], dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        System.out.println(max);
    }
}
