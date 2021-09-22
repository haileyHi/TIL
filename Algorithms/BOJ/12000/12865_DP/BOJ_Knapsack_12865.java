import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Knapsack_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        int[][] bag = new int[N+1][K+1];
        int[] weight = new int[N+1];
        int[] value = new int[N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] =  Integer.parseInt(st.nextToken());
            value[i] =  Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if(weight[i] > j){ // 무게가 배낭보다 무거우면
                    bag[i][j] = bag[i-1][j]; //이전에 담았던 무게가 제일 무거운 상태이다.
                }else{
                    bag[i][j] = Math.max(value[i] + bag[i - 1][j - weight[i]], bag[i - 1][j]);
                }
                sum = Math.max(bag[i][j], sum);
//                System.out.println(i + " " + j  + " "+ bag[i][j]);
            }
        }
        System.out.println(sum);
    }
}
