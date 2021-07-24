import java.util.Scanner;

public class BOJ_KthNum_1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        long ans = 0;

        long left = 1;
        long right = K;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 1; i <= N; i++) {
                cnt += Math.min(mid / i, N);
            }
            if (cnt < K) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid -1;
            }
        }

        System.out.println(ans);

    }
}
