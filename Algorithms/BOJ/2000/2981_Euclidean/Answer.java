import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_Check_2981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(list);
        int n = list[1] - list[0]; // 맨 앞의 차이
        for (int i = 2; i < N; i++) {
            int tmp = list[i] - list[i-1]; // 차이의 약수 구하기 공통된 것 찾기!
            n = gcd(tmp, n);
        }

        // n = list 차이의 최대공약수
        for(int i = 2; i <= n; i++){
            if (n % i == 0) { // 약수
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }

    private static int gcd(int a, int b) {
        if(a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 0) {
            return a;
        }else{
            return gcd(b, a%b);
        }
    }
}
