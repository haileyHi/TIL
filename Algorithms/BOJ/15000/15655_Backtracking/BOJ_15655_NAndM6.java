import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15655_NAndM6 {
    private static int[] arr;
    private static int[] num;
    private static boolean[] used;
    private static int N, M;
    private static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];
        num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }
        used = new boolean[N];
        sb = new StringBuilder();
        Arrays.sort(num);
        search(0, 0);
        System.out.println(sb);
    }

    private static void search(int idx, int start) {
        if (idx == M) {
            print();
            return;
        }
        for (int i = start; i < N; i++) {
            if(used[i]) continue;
            arr[idx] = num[i];
            used[i] = true;
            search(idx + 1, i);
            used[i] = false;
        }
    }
    private static void print(){
        for (int i = 0; i < M; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }
}
