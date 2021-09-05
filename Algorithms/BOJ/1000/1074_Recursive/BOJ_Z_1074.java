import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int amount = (int) Math.pow(2, N);
        int cnt = search(amount, r, c);
        System.out.println(cnt);
    }

    private static int search(int n, int r, int c) {
        int half = n/2;
        if (n == 2) {
            return r * 2 + c;
        }
        if (r < half) {
            if (c < half) {
                return search(half, r, c);
            } else {
                return half * half + search(half, r, c - half);
            }
        } else {
            if (c < half) {
                return n * n / 2 + search(half, r - half, c);
            } else {
                return half * half * 3 + search(half, r - half, c - half);
            }
        }
    }
}
