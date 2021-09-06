import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;

    private static int findParent(int num) {
        if (parent[num] == num) {
            return num;
        } else {
            return parent[num] = findParent(parent[num]);
        }
    }

    private static boolean union(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);
        if (rootA == rootB) {
            return false;
        }
        if (rootA < rootB) {
            int tmp = rootB;
            rootB = rootA;
            rootA = tmp;
        }
        parent[rootB] = rootA;
        return true;
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
        Queue<Edge> queue = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            queue.offer(new Edge(from, to, w));
        }

        int total = 0;
        int cnt = 0;
        for (int i = 0; i < E; i++) {
            Edge cur = queue.poll();
//            System.out.println(cur.from + "==" + cur.weight + "==" + cur.to);

            if (!union(cur.from, cur.to)) {
                continue;
            }
            parent[cur.to] = findParent(cur.from);
            total += cur.weight;
            if (++cnt == V - 1) break;
        }

        System.out.println(total);
    }
}
