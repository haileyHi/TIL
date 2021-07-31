import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Binomial_11401 {
    private static long[] fact;
    private static final int P = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        fact = new long[N+1];
        fact[0] = 1;
        fact[1] = 1;
        for (int i = 2; i <= N; i++) {
            fact[i] = (fact[i-1] * i) % P;
        }
        long res = 0;
        long m = (fact[N-R] * fact[R])%P;
        res = fact[N] % P * getInverse(m, P -2) %P;
        System.out.println(res);

    }

    private static long getInverse(long x, int y){
        if(y == 0) return 1;
        long cur = getInverse(x, y/2);
        long curRem = (cur * cur) % P;
        if(y % 2 == 0) return curRem;
        else return (curRem * x) % P;
    }
}
