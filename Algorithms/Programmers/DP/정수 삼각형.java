class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        int[] dp = new int[len];
        int[] cpy = new int[len];
        dp[0] = triangle[0][0];
        for (int i = 0; i< len ; i++){
            for (int j = 0; j <= i; j++){
                if (j >= 1){
                    cpy[j] = Math.max(dp[j] + triangle[i][j] , dp[j-1] + triangle[i][j]);
                } else {
                    cpy[j] += triangle[i][j];
                }
            }
            for( int j = 0; j<= i; j++){
                dp[j] = cpy[j];
            }
        }
        for (int i = 0 ; i < len; i++){
            answer = Math.max(answer,dp[i]);
        }
        return answer;
    }
}
