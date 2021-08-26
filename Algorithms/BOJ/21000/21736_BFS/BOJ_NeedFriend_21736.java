import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_NeedFriend_21736 {
    private static int[][] dir = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        int startR = -1, startC = -1;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                switch (c) {
                    case 'O':
                        map[i][j] = 0;
                        break;
                    case 'X':
                        map[i][j] = -1;
                        break;
                    case 'I':
                        startR = i;
                        startC = j;
                        break;
                    case 'P':
                        map[i][j] = 1;
                        break;
                }
            }
        }

        int cnt = search(startR, startC);
        if (cnt == 0) {
            System.out.println("TT");
        } else {
            System.out.println(cnt);
        }
    }

    private static int search(int startR, int startC) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == 1) {
                cnt++;
            }

            for (int d = 0; d < 4; d++) {
                int newR = cur[0] + dir[d][0];
                int newC = cur[1] + dir[d][1];
                if (0 > newR || newR >= N || 0 > newC || newC >= M || visited[newR][newC]) continue;
                if (map[newR][newC] != -1) {
                    visited[newR][newC] = true;
                    queue.offer(new int[]{newR, newC});
                }
            }
        }

        return cnt;
    }
}
