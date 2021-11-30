class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int minDist = 20000000;
        int len = fares.length;
        int[][] dist = new int[n+1][n+1]; // 각 거리 연결할 것.
        for (int i = 1; i<= n; i++){
            for (int j = 1; j<= n; j++){
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = minDist;
            }
        }
        // 각 지점 간 택시 요금 입력하기.
        for (int i = 0; i< len; i++){
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        
        for (int k = 1; k <= n; k++){ // 경유지
            for (int i = 1; i <= n; i++){ // 출발지
                for (int j = 1; j<= n; j++){ // 도착지
                    if (i == k || j == k || i == j || dist[i][k] == minDist || dist[k][j] == minDist) continue; // 같은 경우거나 경로가 없는 경우 넘어가기.
                    if (dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }    
                }   
            }
        }
        
        for (int k = 1; k<= n; k++){
            // dist[s][k] : 시작부터 함께 갈 가장 먼 경유지까지의 비용
            // dist[k][a] : A가 혼자 도착지까지 갈 요금.
            // dist[k][b] : B가 혼자 도착지까지 갈 요금.
            minDist = Math.min(minDist, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        answer = minDist;
        return answer;
    }
}
