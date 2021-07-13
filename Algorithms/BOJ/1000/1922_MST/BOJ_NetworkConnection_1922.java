import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] roots;
    private static Edge[] lists;

    private static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
    private static int findParent(int a){
        if(roots[a] == a) return a;
        return roots[a] = findParent(roots[a]);
    }

    private static boolean union(int a, int b){
        int aRoot = findParent(a);
        int bRoot = findParent(b);
        if(aRoot == bRoot) return false;
        if(a > b){
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        roots[bRoot] = aRoot;

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        roots = new int[N];
        lists = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lists[i] = new Edge(Integer.parseInt(st.nextToken()) -1 , Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
        Arrays.sort(lists);

        int total = 0;
        int cnt = 0;

        for (Edge e : lists) {
            if (union(e.from, e.to)) {
                total += e.cost;
                if(cnt++ == N-1) break;
            }
        }

        System.out.println(total);
    }
}
