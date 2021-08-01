import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dist;
    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dist = new int[N][N];
        setINF(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a-1][b-1] = dist[b-1][a-1] = 1;
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minCnt = INF;
        int minNum = -1;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                cnt += dist[i][j];
            }
            if (minCnt > cnt) {
                minNum = i;
                minCnt = cnt;
            }
        }
        System.out.println(minNum+1);
    }

    private static void setINF(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                dist[i][j] = INF;
            }
        }
    }
}
