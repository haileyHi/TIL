import java.util.*;

public class CheckSocialDistancing {
    public static int[][] dir = {
        {-1,0}, {0,1}, {1,0}, {0,-1}  
    };
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i< 5; i++){
            int[][] map = new int[5][5];
            // 배열에 담아서 체크하기
            for (int j = 0; j< 5; j++){
                String s = places[i][j];
                for (int k = 0; k<5; k++){
                    char c = s.charAt(k);
                    if (c == 'P'){
                        map[j][k] = 1;
                    } else if (c == 'O'){
                        map[j][k] = 0;
                    } else {
                        map[j][k] = -1;
                    }
                }
            }
            // for 문 2개 돌면서 확인하기.
            boolean flag = false;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (map[j][k] == 1) {
                        if (!check(map, j, k)) {
                            flag = true;
                        }
                    }
                }
                if (flag) break;
            }
            
            if (!flag) {
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    public static boolean check(int[][] map, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (0 < cur[2] && map[cur[0]][cur[1]] == 1) {
                return false;
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if (0 > nr || nr >= 5 || 0 > nc || nc >= 5 || map[nr][nc] == -1 || visited[nr][nc]) continue; // 범위 벗어나거나 벽이면 체크 안함.
                int manDist = Math.abs(nr - r) + Math.abs(nc - c);
                if (manDist >= 3) continue;
                queue.offer(new int[]{nr, nc, manDist});
                visited[nr][nc] = true;
            }
        }

        return true;
    }
}
