import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            boolean tangled = false;
            Stack<Character> stack = new Stack<>();
            String s = br.readLine();
            int len = s.length();
            for (int j = 0; j < len; j++) {
                char cur = s.charAt(j);
                if (!stack.isEmpty()) {
                    char c = stack.peek();
                    if (c == cur){
                        stack.pop();
                    }else {
                        stack.push(cur);
                    }
                } else {
                    stack.push(cur);
                }
            }
            if (!stack.isEmpty()) {
                tangled = true;
            }
            if (!tangled) cnt++;
        }
        System.out.println(cnt);
    }
}
