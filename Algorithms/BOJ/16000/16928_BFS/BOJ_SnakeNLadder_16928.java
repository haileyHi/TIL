import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] map = new int[100];
    private static int[] dist = new int[100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            map[from] = to;
        }
        System.out.println(dp());
    }
    private static int dp() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 99) {
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int tmp = cur + i;
                if (tmp > 99) {
                    continue;
                }
                if (map[tmp] != 0) {
                    tmp = map[tmp];
                }
                if (dist[tmp] == 0) {
                    dist[tmp] = dist[cur] + 1;
                    queue.offer(tmp);
                }
            }
        }
        return dist[99];
    }
}
