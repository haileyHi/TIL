import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10422_Brackets {
    private static long[] cnts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int L = Integer.parseInt(br.readLine());
        cnts = new long[5001];

        setCnt();

        for (int i = 0; i < L; i++) {
            int input = Integer.parseInt(br.readLine());
            sb.append(cnts[input]).append("\n");
        }
        System.out.println(sb);
    }
    private static void setCnt(){
        cnts[0] = 1;
        cnts[2] = 1;
        for (int i = 3; i <= 5000; i++) {
            for(int j = 2; j<= i; j++){
                cnts[i] = (cnts[i] + (cnts[j-2] * cnts[i-j])) % 1000000007;;
            }
        }
    }
}
