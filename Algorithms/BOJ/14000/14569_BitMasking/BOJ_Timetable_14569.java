import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int STU_CNT;
    private static long[] lectures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        lectures = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            long time = 0;
            for (int j = 0; j < cnt; j++) {
                long tmp = Long.parseLong(st.nextToken()) - 1;
                time |= (1L << tmp);
            }
            lectures[i] = time;
        }
        STU_CNT = Integer.parseInt(br.readLine());

        for (int i = 0; i < STU_CNT; i++) {
            int res = 0;
            st = new StringTokenizer(br.readLine());
            int ableTime = Integer.parseInt(st.nextToken());
            long able = 0;
            for (int j = 0; j < ableTime; j++) {
                long empty = Long.parseLong(st.nextToken()) - 1;
                able |= ( 1L << empty);
            }
            // 저장된 과목별로 학생 able 과 연산해서 학생 공강 & 수업 시간표 !
            for (int check = 0; check < N; check++) {
                if ((lectures[check] & able) == lectures[check]) {
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}
