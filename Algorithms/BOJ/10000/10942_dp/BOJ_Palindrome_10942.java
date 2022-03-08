import java.io.*;
import java.util.*;

public class Main
{
    private static int N, M;
    private static int[] number;
    private static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		dp = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i< N; i++){
		    number[i] = Integer.parseInt(st.nextToken());
		}
		
        for (int d = 0 ; d< N; d++){ // 차이
		    for (int i = 0; i < N - d; i++){
		        int j = i + d;
		        // 한 자리 수면 팰린드롬.
		        if (d == 0) {
		            dp[i][j] = 1;
		            continue;
		        }
		        // 두 숫자가 같은 경우
		        if (number[i] == number[j]){
		            // 팰린드롬 확인하기
    		        if (d == 1 || dp[i+1][j-1] == 1){
    		            dp[i][j] = 1;
    		        }
		        }
		    }
		}
		
		M = Integer.parseInt(br.readLine());

		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i< M; i++){
		    st = new StringTokenizer(br.readLine());
		    int s = Integer.parseInt(st.nextToken()) - 1;
		    int e = Integer.parseInt(st.nextToken()) - 1;
		    if (dp[s][e]==1) {
		        sb.append("1\n");
		    } else {
		        sb.append("0\n");
		    }
		}
		
		System.out.println(sb);
	}
	
}
