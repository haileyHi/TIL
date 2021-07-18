import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_Postfix_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        // A * ( B + C )
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> oprStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        //A*(B+C)
        int strLen = expression.length();
        for (int i = 0; i < strLen; i++) { //앞에서부터
            char c = expression.charAt(i); //
            if(c == '(') {
                oprStack.push(c);
                continue;
            }
            if(c == ')'){
                while (oprStack.peek() != '(') {
                    sb.append(oprStack.pop());
                }
                oprStack.pop();
                continue;
            }
            if (priority(c) == 2) { //알파벳
                sb.append(c);
            } else if(oprStack.isEmpty()) { //연산자 아직 아무것도 안 들어갔을 때
                oprStack.push(c);
            } else if (priority(oprStack.peek()) > priority(c)) { // 스택 맨 위 연산자보다 현재 연산자의 우선순위가 높을 때
                oprStack.push(c);
            } else if(priority(oprStack.peek()) == priority(c)) { // 스택 맨 위 연산자와 현재 연산자 우선순위가 같을 때
                sb.append(oprStack.pop());
                oprStack.push(c);
            } else { // 스택 맨 위 연산자의 우선순위가 현재 연산자보다 높을 때
                while (!oprStack.isEmpty() && priority(oprStack.peek()) != priority(c)){
                    sb.append(oprStack.pop());
                }
                if(!oprStack.isEmpty() && priority(oprStack.peek()) == priority(c)){
                    sb.append(oprStack.pop());
                    oprStack.push(c);
                    continue;
                }
                oprStack.push(c);
            }
        }
        while (!oprStack.isEmpty()) {
            sb.append(oprStack.pop());
        }

        System.out.println(sb);
    }
    private static int priority(char c){
        if(c == '*' || c == '/') return 0;
        else if(c == '+' || c == '-') return 1;
        else if(c == '(') return 2;
        else return 2;
    }
}
