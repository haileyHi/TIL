import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_CrazyRobot {
    private static int [][] dir = {
            {0,1},{0,-1}, {1,0}, {-1, 0}
    };

    private static int N;
    private static double result = 0.0;
    private static double[] possibility = new double[4];
    private static boolean[][] visited = new boolean[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            possibility[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }
        dfs(15, 15, 0, 1);
        System.out.println(result);


    }
    private static void dfs(int r, int c, int cnt, double choose){
        if(cnt == N) {
            result += choose;
            return;
        }
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int newR = r + dir[d][0];
            int newC = c + dir[d][1];
            if(visited[newR][newC]) continue;
            // visited 처리는 똑같음!
            dfs(newR, newC, cnt + 1, choose * possibility[d]);
            visited[newR][newC] = false;
        }

    }
}
