import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS {
    public static void main(String[] args) throws IOException {

        /*
        *   1. 부분 수열 구하기
        *  비트로 각 위치에 포함된 수열 String.으로 만들어서 비교하기
        *   =>시간 복잡도가 너무 오래 걸림 (중복 제외 해도 최대 부분 수열 개수가 2^(문자열의 길이) -1)
        *
        *   2. LIS처럼 풀기
        *   LIS는 앞에 것보다 증가하면 배열에 1씩 더해서 (문자열1길이) * (문자열2길이) 만큼만 비교하기
        *   일치하면 지금까지 나온 최대값 + 1
        * */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s1 = reader.readLine();
        String s2 = reader.readLine();
        
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] strMatrix = new int[len1 + 1][len2 + 1];

        int cnt = 0;

        int maxLength = -1;

        for(int i = 1 ; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    strMatrix[i][j] = strMatrix[i-1][j-1] + 1;
                }else {
                    strMatrix[i][j] = Math.max(strMatrix[i-1][j], strMatrix[i][j-1]);
                }
                maxLength = Math.max(maxLength , strMatrix[i][j]);
            }
        }
        System.out.println(maxLength);
    }
}
