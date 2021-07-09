import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        while (str.contains(bomb)) {
            str = str.replace(bomb, "");
        }
        System.out.println(str.length() == 0 ? "FRULA" : str);
    }
}

//위의 방법은 replace 함수 때문에 메모리초과가 발생한다. StringBuilder로 방법을 바꿔서 다시 시도해보아야겠다.
