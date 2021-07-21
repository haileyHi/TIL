import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_MinCost_1916 {
    public static class Bus implements Comparable <Bus>{
        int to, cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }

    private static int N, start, end;
    private static PriorityQueue<Bus>[] list; 
    private static int[] dist = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        list = new PriorityQueue[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new PriorityQueue<Bus>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Bus(to, cost));
        }
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        search(start);
        System.out.println(dist[end]);
    }

    private static void search(int start){
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Bus cur = pq.poll(); // 제일 짧은 비용 뽑기

            if (!visited[cur.to]) { // 아직 방문하지 않은 곳이면 여기로 보낸 도시 번호까지의 가자 짧은 거리 일 것..
                visited[cur.to] = true;

                for(Bus b : list[cur.to]){ //다음 번호에서 시작하는 리스트에서 가는 길 넣기
                    if(!visited[b.to] && dist[b.to] > dist[cur.to] + b.cost){
                        dist[b.to] = dist[cur.to] + b.cost;
                        pq.offer(new Bus(b.to, dist[b.to]));
                    }
                }
            }
        }
    }
}
