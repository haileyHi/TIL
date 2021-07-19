import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean[] prime;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        prime = new boolean[N + 1];
        Arrays.fill(prime,true);
        eratosthenes(N);

        int tmp = 0;
        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if(!prime[i]) continue;
            //소수면 일단 시작
            tmp = i;
            if(tmp == N){
                cnt++;
                break;
            }
            for (int j = i + 1; j <= N; j++) {
                if(!prime[j]) continue;
                //소수면
                if(tmp + j == N){ //합이 N일때
                    cnt++;
                    break;
                }
                if(tmp + j > N){//합이 N보다 클 때 다음 i로 넘어가기.
                    break;
                }
                //합이 아직 N에 못 미칠 때
                tmp += j;
            }
        }

        System.out.println(cnt);
    }
    private static void eratosthenes(int N) {
        prime[0] = prime[1] = false;
        for (int i = 2; i <= Math.sqrt(N); i++) {
               if(prime[i]){
                   for (int j = i * i; j <= N; j += i) {
                       prime[j] = false;
                   }
               }
        }
    }
}

