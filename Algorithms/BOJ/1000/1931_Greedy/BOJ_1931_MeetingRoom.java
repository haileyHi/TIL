import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931_MeetingRoom {
    private static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        Meeting[] list = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[i] = new Meeting(start, end);
        }
        Arrays.sort(list);

        int cnt = 0;
        Meeting tmp = new Meeting(0,0);

        for (int i = 0; i < N; i++) {
            if (tmp.end <= list[i].end && list[i].start >= tmp.end) {
                cnt++;
                tmp = list[i];
            }
        }

        System.out.println(cnt);
    }
}
