import java.util.Scanner;

public class BaseConversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt(); // num을
        int N = sc.nextInt(); // N진법으로 표기하기
        long res = Long.parseLong(change(num, N));
        System.out.println(res);
    }
    private static String change(int num, int N) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1) {
            sb.append(num % N);
            num /= N;
        }
        return sb.reverse().toString();
    }
}
