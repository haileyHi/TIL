import java.util.Scanner;

public class Main {
    private static int[] arr;
    private static int N, M;
    private static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];
        sb = new StringBuilder();
        search(0, 1);
        System.out.println(sb);
    }

    private static void search(int idx, int start) {
        if (idx == M) {
            print();
            return;
        }
        for (int i = start; i <= N; i++) {
            arr[idx] = i;
            search(idx + 1, i);
        }
    }
    private static void print(){
        for (int i = 0; i < M; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }
}
