# 1261번 알고스팟
[문제 보러가기](https://www.acmicpc.net/problem/1261)

## 🅰 설계
조건 확인
- 벽 부수면서 (1,1)부터 (N,M)까지 이동할 수 있다.
- 가장 벽을 조금 부수면서 이동할 수 있는 경우에 부숴야할 벽 개수를 출력하자.

일단 벽을 부수면서 탐색해야 하니까 BFS를 이용한다.

```java
큐 + while(!큐.isEmpty()){
  큐 하나씩 poll();
  for(int d = 0; d< 4; d++){ //사방 탐색
    
  }
}
```

처음에는 위의 기본 틀과 벽 정보를 담은 `int[][] map` , 각 위치까지의 최소 부술 벽의 개수를 담은 `int[][] dist`, 방문 여부를 담을 `boolean[][] visited` 를 이용해서 코드를 구성했다.

사방 탐색 for문 안에 들어가는 내용으로 
```java
if(0 > newR || newR >= N || 0 > newC || newC >= M) continue;
if(dist[cur[0]][cur[1]] + map[newR][newC] < dist[newR][newC]){
  dist[newR][newC] = dist[cur[0]][cur[1]] + map[newR][newC];
}
dist[newR][newC] = Math.min(dist[cur[0]][cur[1]] + map[newR][newC], dist[newR][newC]);
if(visited[newR][newC]) continue;
queue.offer(new int[]{newR, newC});
visited[newR][newC] = true;
```

이렇게 짠 초안 코드로는 매번 갱신을 진행해서 짧은 거리로 갱신은 이뤄지지만
해당 갱신 정보로 다음 벽을 탐색하지는 못했다.(방문하지 않은 좌표만 queue에 offer에 넣기 때문)

그래서 `boolean flag` 변수를 하나 추가해서 갱신이 이뤄질 때는 다시 좌표를 queue에 넣어서 탐색하도록 하게 바꿔주었다.

```java
boolean flag = false;
if(dist[cur[0]][cur[1]] + map[newR][newC] < dist[newR][newC]){
  dist[newR][newC] = dist[cur[0]][cur[1]] + map[newR][newC];
  flag = true;
}
if(!flag) continue;
queue.offer(new int[]{newR, newC});
```

갱신이 이뤄질 때만 좌표에 담기 때문에 이전 벽으로 돌아가는 일은 발생하지 않게 된다. (따라서 무한 루프도 발생하지 않음)
그래서 visited 배열도 사용할 필요가 없다.

### 전체코드

    ```java
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;

    public class Main{
        private static int M, N;
        private static int[][] map; //(벽 부수면서 거리 바로 갱신할까)
        private static int[][] dist;
        private static int[][] dir = {
                {-1,0},{0,1},{1,0},{0,-1}
        };
        private static boolean[][] visited;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            dist = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = s.charAt(j) - '0';
                    dist[i][j] = Integer.MAX_VALUE - 10000;
                }
            }
            dist[0][0] = 0;

            search(0,0);
            System.out.println(dist[N-1][M-1]);
        }

        private static void search(int r, int c) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{r, c});
    //        visited[r][c] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int newR = cur[0] + dir[d][0];
                    int newC = cur[1] + dir[d][1];
                    if(0 > newR || newR >= N || 0 > newC || newC >= M) continue;
                    boolean flag = false;
                    if(dist[cur[0]][cur[1]] + map[newR][newC] < dist[newR][newC]){
                        dist[newR][newC] = dist[cur[0]][cur[1]] + map[newR][newC];
                        flag = true;
                    }
    //                dist[newR][newC] = Math.min(dist[cur[0]][cur[1]] + map[newR][newC], dist[newR][newC]);
    //                if(visited[newR][newC]) continue;
                    if(!flag) continue;
                    queue.offer(new int[]{newR, newC});
    //                visited[newR][newC] = true;
                }
            }
        }
    }

    ```

## ✅ 후기

우선순위 큐 사용해보기
