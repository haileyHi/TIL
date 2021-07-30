import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        String s = divide(0, 0, N);
        System.out.println(s);
    }

    public static String divide(int x, int y, int range) {
        int start = map[x][y];
        boolean checkNext = true;

        for (int i = x; i < x + range; i++) {
            for (int j = y; j < y + range; j++) {
                if (map[i][j] != start) {
                    checkNext = false;
                }
            }
        }
        if (checkNext) {
            return start + "";
        }
        return "(" +
                divide(x, y, range/2) +
                divide(x, y+range/2, range/2) +
                divide(x + range/2, y, range/2) +
                divide(x +range/2, y + range/2, range/2) +
                ")";
    }
}
