import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int minCnt = 0;
    private static int[][] dir = {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][1 <<6];

        int startX = 0, startY = 0;
        for (int i = 0; i < N; i++) {
            char[] chArr = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                map[i][j] = chArr[j];
                if(map[i][j] == '0'){
                    startX = i; startY = j;
                }
            }
        }
        System.out.println(bfs(startX, startY, 0));
    }

    private static int bfs(int x, int y, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y][0] = true; // 아무런 키도 안 들고 갔을 때.
        queue.offer(new int[] {x,y,0,0}); // x,y,key,count
        int temp[], nx, ny, key = 0;
        while(!queue.isEmpty()){
            temp = queue.poll();
            //출구라면
            if(map[temp[0]][temp[1]] == '1') return temp[3]; //count

            for (int d = 0; d < 4; d++) {
                nx = temp[0] +dir[d][0];
                ny = temp[1]+dir[d][1];
                key = temp[2]; //열쇠

                //경계를 벗어나거나 벽이면 다음으로
                if(0 > nx || nx >= N || 0 > ny || ny >= M || map[nx][ny] == '#') continue;

                // 이동할 위치에 문이 있다면 기존 열쇠들로 문을 열 수 있는지 판단
                if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F'){
                    if((key & (1<<(map[nx][ny] -'A'))) == 0) continue; // 열쇠 없으면 다음으로.
                    //이동할 위치에 열쇠가 있다면 기존 열쇠의 조합에 처리.
                }else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                    key |= (1<<(map[nx][ny] - 'a'));
                }

                if (!visited[nx][ny][key]) {
                    visited[nx][ny][key] = true;
                    queue.offer(new int[]{nx, ny, key, temp[3] + 1});
                }
            }
        }
        return -1;
    }
}
