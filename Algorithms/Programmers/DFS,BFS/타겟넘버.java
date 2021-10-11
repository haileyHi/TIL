import java.util.*;

class Solution {
  // DFS
    private static int T;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        T = target;
        answer = dfs(numbers, 0, 0);
        
        return answer;
    }
    public static int dfs(int[] numbers, int idx, int value){
        if (idx == numbers.length){
            if (value == T){
                return 1;
            }
            return 0;
        }
        return dfs(numbers, idx + 1, value + numbers[idx]) + dfs(numbers, idx + 1, value - numbers[idx]);
    }
}
