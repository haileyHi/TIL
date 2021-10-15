import java.util.Scanner;

public class Main {
    private static int[] arr;
    private static int[] opr = new int[4];
    private static long max = -2000000000;
    private static long min = 2000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            opr[i] = sc.nextInt();
        }
        calc(arr[0],1,N);
        System.out.println(max);
        System.out.println(min);
    }

    private static void calc(int val, int idx, int N) {
        if (idx == N) {
            if (val > max) {
                max = val;
            }
            if (val < min) {
                min = val;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (opr[i] > 0) { //연산자 남아있으면
                opr[i]--;
                if (i == 0) {
                    if (val + arr[idx] > 1000000000) {
                        continue;
                    }
                    calc(val + arr[idx], idx + 1, N);
                } else if (i == 1) {
                    if (val - arr[idx] < -1000000000) {
                        continue;
                    }
                    calc(val - arr[idx], idx + 1, N);
                } else if (i == 2) {
                    if (val * arr[idx] > 1000000000) {
                        continue;
                    }
                    calc(val * arr[idx], idx + 1, N);
                } else {
                    calc(val / arr[idx], idx + 1, N);
                }
                opr[i]++;
            }
        }
    }
}
