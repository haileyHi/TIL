import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Safezone_2468 {
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] dir = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        map = new int[N][N];
        visited = new boolean[N][N];
        int max = 0;
        for (int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j< N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        int answer = 1;
        for (int k = 1; k < max; k++) {
            visited = new boolean[N][N];
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > k){
                        bfs(i, j, k);
                        tmp++;
                    }
                }
            }
            answer = Math.max(answer, tmp);
        }
        System.out.println(answer);

    }

    private static void bfs(int r, int c, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
                if (map[nr][nc] > height) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
