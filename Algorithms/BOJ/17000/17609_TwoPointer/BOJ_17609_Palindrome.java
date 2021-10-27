import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609_Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int start = 0, tail = s.length() - 1;
            int diffCnt = 0;
            while (start <= tail) {
                if (s.charAt(start) == s.charAt(tail)) {
                    start++;
                    tail--;
                    continue;
                }
                if (check(start + 1, tail, s)) { // 왼쪽만 1 증가시킨 것이 회문인지 확인
                    diffCnt++;
                    start++;
                    continue;
                }
                if (check(start, tail - 1, s)) { // 오른쪽만 1 증가시킨 것이 회문인지 확인
                    diffCnt++;
                    tail--;
                    continue;
                }
                diffCnt += 2; // 위의 둘 다 확인 후 아니라면 회문 아니므로 탈출
                break;
            }
            sb.append(diffCnt == 0 ? 0 : (diffCnt < 2 ? 1 : 2)).append("\n");

        }
        System.out.println(sb);
    }

    private static boolean check(int i, int j, String s) { // i부터 j까지 감소하며 확인
        for (int left = i, right = j; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}
