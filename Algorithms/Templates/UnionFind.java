import java.util.Scanner;

public class UnionFind {
    public static int[] root;
    public static int[] treeSize;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        root = new int[N];
        treeSize = new int[N];

    }
    public static void init(){
        int len = root.length;
        for (int i = 0; i < len; i++) {
            root[i] = i;
            treeSize[i] = 1;
        }
    }
    public static void union(int a, int b) {
        int i = findRoot(a);
        int j = findRoot(b);
        if (i == j) return;
        if (treeSize[i] < treeSize[j]){
            root[i] = j;
            treeSize[j] += treeSize[i];
        } else {
            root[j] = i;
            treeSize[i] += treeSize[j];
        }
    }

    public static boolean connected(int a, int b){
        return findRoot(a) == findRoot(b);
    }

    public static int findRoot(int a){
        while (a != root[a]) {
            root[a] = root[root[a]];
            a = root[a];
        }
        return a;
    }
}
