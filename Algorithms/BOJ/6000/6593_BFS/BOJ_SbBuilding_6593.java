import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int L, R, C;
    private static int[][][] building;
    private static boolean[][][] visited;
    private static int[][] dir = {
            {0,-1,0},{0,0,1},{0,1,0},{0,0,-1},{-1,0,0},{1,0,0} // 북, 동, 남, 서, 하, 상
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;
            building = new int[L][R][C];
            visited = new boolean[L][R][C];

            int startFloor = -1, startR = -1, startC = -1;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        int tmp = 0;
                        switch (s.charAt(k)) {
                            case 'S':
                                startFloor = i;
                                startR = j;
                                startC = k;
                                break;
                            case '#':
                                tmp = -1;
                                break;
                            case 'E':
                                tmp = 2;
                                break;
                            default:
                                tmp = 0;
                        }
                        building[i][j][k] = tmp;
                    }
                }
                String next = br.readLine();
            }

            int cnt = search(startFloor, startR, startC);
            if (cnt == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + cnt + " minute(s).");
            }
        }
    }

    private static int search(int startFloor, int startR, int startC) {
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startFloor, startR, startC, res});
        visited[startFloor][startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 6방향 탐색
            for (int d = 0; d < 6; d++) {
                int newF = cur[0] + dir[d][0];
                int newR = cur[1] + dir[d][1];
                int newC = cur[2] + dir[d][2];
                //1. 범위가 빌딩 내부인지 체크 2. 벽이 아닌지 체크 3. 방문 안 한 곳인지 체크
                if (0 > newF || newF >= L || 0 > newR || newR >= R || 0 > newC || newC >= C || building[newF][newR][newC] == -1 || visited[newF][newR][newC]) {
                    continue;
                }
                // 출구라면?
                if (building[newF][newR][newC] == 2) {
                    return cur[3] + 1;
                }
                //방문 안 한 비어있는 곳이면
                visited[newF][newR][newC] = true;
                queue.offer(new int[]{newF, newR, newC, cur[3] + 1});
            }
        }

        return -1;
    }
}
