import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14503_RobotVacuum {
    private static int N, M;
    private static int[][] map;
    private static int[][] dir = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    static class Robot {
        int r, c, d;

        Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = clean(r, c, d);
        System.out.println(cnt);
    }

    private static int clean(int r, int c, int d) {
        int cnt = 0;
        Queue<Robot> queue = new LinkedList<>();
        queue.offer(new Robot(r, c, d));

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();
            // 1. 현재 위치 청소
            if (map[cur.r][cur.c] == 0) {
                map[cur.r][cur.c] = 2;
                cnt++;
//                System.out.println(cur.r + " " + cur.c);
            }

            boolean check = checkFour(cur.r, cur.c);
            if (check) {
                int checkR = cur.r + dir[(cur.d + 2) % 4][0];
                int checkC = cur.c + dir[(cur.d + 2) % 4][1];
                if (0 > checkR || checkR >= N || 0 > checkC || checkC >= M) {
                }
                else if (map[checkR][checkC] != 1) {
                    // 2-c. 네 방향 모두 청소가 되어있거나 벽인 경우, 바라보는 방향으로 한 칸 후진
                    queue.offer(new Robot(checkR, checkC, cur.d));
                    continue;
                } else if (map[checkR][checkC] == 1) {
                    // 2-d. 네방향 모두 청소가 되어있거나 벽이면서 뒤쪽 방향이 벽이라 후진 못하는 경우.
                    break;
                }
            }
            int newD = (cur.d + 3) % 4;
            int newR = cur.r + dir[newD][0];
            int newC = cur.c + dir[newD][1];
            // 2-a, 왼쪽 방향에 청소하지 않은 공간이 있는지 확인
            if (0 <= newR && newR < N && 0 <= newC && newC < M && map[newR][newC] < 1 && checkLeft(newR, newC, newD)) {
                if (map[newR][newC] < 1) {
                    queue.offer(new Robot(newR, newC, newD));
                }
            }
            // 2-b . 왼쪽 방향에 청소할 공간이 없다면 그 방향으로 회전하고 2번으로 돌아가기.
            else {
                queue.offer(new Robot(cur.r, cur.c, newD));
            }
        }
        return cnt;
    }

    private static boolean checkLeft(int r, int c, int d) {
        int newR = r, newC = c;
        while (0 <= newR && newR <= N && 0 <= newC && newC <= M) {
            if (map[newR][newC] == 0) {
                return true;
            }
            newR += dir[d][0];
            newC += dir[d][1];
        }
        return false;
    }
    private static boolean checkFour(int r, int c){
        int tmpR, tmpC;
        for (int d = 0; d < 4; d++) {
            tmpR = r + dir[d][0];
            tmpC = c + dir[d][1];
            if(0 > tmpR || tmpR >= N || 0 > tmpC || tmpC >= M) continue;
            if (map[tmpR][tmpC] < 1) {
                return false;
            }
        }
        return true; // 사방이 청소가 되어있거나 벽인 경우
    }
}
