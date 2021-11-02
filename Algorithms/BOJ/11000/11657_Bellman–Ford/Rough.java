import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static ArrayList<Bus>[] list;
    private static long[] dist;
    private static int MAX = 987654321;

    public static class Bus {
        int to, w;

        public Bus(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        dist = new long[N + 1];
        // 맵 초기화 (거리 무한하게)
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 버스 노선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[from].add(new Bus(to, weight));
        }

        boolean check = checkRoute(); // true라면 음수 사이클 발생!

        if (check) {
            sb.append("-1").append("\n");
        } else {
            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] == MAX ? "-1" : dist[i]).append("\n");
            }
        }
        System.out.println(sb);

    }

    private static boolean checkRoute() {
        Arrays.fill(dist, MAX);
        dist[1] = 0; //1번 도시에서 시작하니까 1번은 0

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Bus b : list[j]) {
                    if (dist[b.to] > dist[j] + b.w) {
                        dist[b.to] = dist[j] + b.w;
                        if (i == N) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
