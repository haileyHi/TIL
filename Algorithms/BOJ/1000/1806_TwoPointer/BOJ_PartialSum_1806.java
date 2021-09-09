import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N, S;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int minLen = 100001;
        int[] sum = new int[N+1];
        int tmpSum = 0;
        for (int i = 1; i <= N; i++) {
            tmpSum += arr[i-1];
            sum[i] = tmpSum;
        }
        int i = 0, j = i + 1;
        while (i <= j && j <= N) {
            int tmp = sum[j] - sum[i];
            if (tmp >= S && i != j) {
                minLen = Math.min(minLen, j - i++);
            }else {
                j++;
            }
        }

        if (minLen == 100001) {
            System.out.println("0");
        }else {
            System.out.println(minLen);
        }
    }
}
