import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Bakery_3109 {
    public static char[][] map;
    public static boolean[][] visited;
    public static int R, C, max, cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        makePipe(); //파이프 배치
      
        System.out.println(cnt);

    }
  
    private static void makePipe(){
        cnt = 0;
        for (int i = 0; i < R; i++) { // 첫 번째 열부터 무조건 파이프 놓기 시작.
            visited[i][0] = true;
            dfs(i, 0);
        }
    }


    static int[] dr = {-1,0,1}; //무조건 한칸 오른쪽으로 매번 이동하므로 행 좌표가 위쪽 대각선, 오른쪽, 아래 대각선 3가지 방향만 고려하면 된다.
    private static boolean dfs(int r, int c) {
        if (c == C-1) { // 끝까지 진행됐을 때만 파이프 하나 설치 완료로 보고 cnt 증가
            cnt++;
            return true;
        }
        int nr, nc = c + 1;
        for (int d = 0; d < 3; d++) {
            nr = r + dr[d];
            if (nr < 0 || nr >= R || map[nr][nc] == 'x' || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            if(dfs(nr, nc)) return true;
        }
        return false;
    }
}
