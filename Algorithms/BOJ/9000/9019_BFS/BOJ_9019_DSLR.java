import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_DSLR_9019 {
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int TC;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
            visited = new boolean[10000];
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            String get = search(from, to);
            System.out.println( get);

        }
    }

    private static String search(int from, int to) {
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{from, ""});
        visited[from] = true;
        while (!queue.isEmpty()) {
            Object[] cur = queue.poll();
            if((int) cur[0] == to) {
                return String.valueOf(cur[1]);
            }
            int curCnt = (int) cur[0];
            String curState = (String) cur[1];
            int twice = (curCnt * 2) % 10000;
            if (!visited[twice]) {
                queue.offer(new Object[]{twice, curState + "D"});
                visited[twice] = true;
            }
            if (curCnt > 0 && !visited[curCnt - 1]) {
                queue.offer(new Object[]{curCnt - 1, curState + "S"});
                visited[curCnt - 1] = true;
            } else if( curCnt == 0 && !visited[9999]) {
                visited[9999] = true;
                queue.offer(new Object[]{9999, curState + "S"});
            }
            // L 회전
            int leftRoll = (curCnt % 1000) * 10 + (curCnt / 1000);
            if (!visited[leftRoll]) {
                queue.offer(new Object[]{leftRoll, curState + "L"});
                visited[leftRoll] = true;
            }

            int rightRoll = (curCnt % 10) * 1000 + (curCnt / 10);
            if (!visited[rightRoll]) {
                queue.offer(new Object[]{rightRoll, curState + "R"});
                visited[rightRoll] = true;
            }
        }
        return "";
    }

}
