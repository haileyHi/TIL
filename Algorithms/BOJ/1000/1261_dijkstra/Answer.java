import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    private static int M, N;
    private static int[][] map; //(벽 부수면서 거리 바로 갱신할까)
    private static int[][] dist;
    private static int[][] dir = {
            {-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE - 10000;
            }
        }
        dist[0][0] = 0;

        search(0,0);
        System.out.println(dist[N-1][M-1]);
    }

    private static void search(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int newR = cur[0] + dir[d][0];
                int newC = cur[1] + dir[d][1];
                if(0 > newR || newR >= N || 0 > newC || newC >= M) continue;
                boolean flag = false;
                if(dist[cur[0]][cur[1]] + map[newR][newC] < dist[newR][newC]){
                    dist[newR][newC] = dist[cur[0]][cur[1]] + map[newR][newC];
                    flag = true;
                }
                if(!flag) continue;
                queue.offer(new int[]{newR, newC});
            }
        }
    }
}
