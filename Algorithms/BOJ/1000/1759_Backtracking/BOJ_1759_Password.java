import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static char[] arr;
    private static boolean[] used;
    private static char[] code;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[M];
        used = new boolean[M];
        code = new char[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        search(0, 0, 0, 0, 0);
    }

    private static void search(int idx, int start, int vCnt, int cCnt, int cnt) {
        if (cnt == N) {
            if (vCnt >= 1 && cCnt >= 2) {
                print();
            }
            return;
        }
        for (int i = start; i < M; i++) {
            if (used[i]) continue;
            code[idx] = arr[i];
            used[i] = true;
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                search(idx + 1, i + 1, vCnt + 1, cCnt, cnt + 1);
            } else {
                search(idx + 1, i + 1, vCnt, cCnt + 1, cnt + 1);
            }
            used[i] = false;
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            System.out.print(code[i]);
        }
        System.out.println();
    }
}
