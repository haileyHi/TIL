import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_톱니바퀴_14891 {
    private static LinkedList<Integer>[] list = new LinkedList[4];
    private static int[] check = new int[4]; //각 톱니바퀴 굴릴 방향 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < 4; i++) {
            list[i] = new LinkedList<>();
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                list[i].offer(Integer.parseInt(String.valueOf(s.charAt(j))));
            }
        }
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            check = new int[4];
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) -1;
            boolean clockwise = true;
            if(Integer.parseInt(st.nextToken()) == -1){
                clockwise = false;
            }
            checkSmall(num, clockwise);
            checkBig(num, clockwise);
            rotate();
        }
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if(list[i].get(0) == 1){
                cnt += Math.pow(2, i);
            }
        }
        System.out.println(cnt);

//        for (int i = 0; i < 4; i++) {
//            for (int a : list[i]) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }
    }

    private static void checkSmall(int num, boolean clockwise) {
        check[num] = clockwise ? 1: -1;
        while (num >= 1) {
            if(!list[num].get(6).equals(list[num - 1].get(2))){ // 둘 다 회전
                check[num - 1] = check[num] == 1? -1: 1;
            }else{ //현재 num만 회전
                check[num - 1] = 0;
                return;
            }
            num--;
        }

    }

    private static void checkBig(int num, boolean clockwise){
        check[num] = clockwise ? 1: -1;
        while (num < 3) {
            if (!list[num].get(2).equals(list[num + 1].get(6))) {
                check[num + 1] = check[num] == 1? -1: 1;
            }else{ // 현재 num만 회전
                check[num + 1] = 0;
                return;
            }
            num++;
        }
    }

    private static void rotate() {
        for (int i = 0; i < 4; i++) {
            int tmp = 0;
            if (check[i] == 0) continue; //회전 안 함.
            if (check[i] == 1) { //시계 방향 : 맨 뒤의 것 빼서 앞에 넣기.
                tmp = list[i].pollLast();
                list[i].offerFirst(tmp);
            }else{ // 반시계 방향 : 맨 앞의 것 빼서 뒤에 넣기 .
                tmp = list[i].pollFirst();
                list[i].offerLast(tmp);
            }
        }
    }

}
