# 문제 번호 10026

[문제 보러가기](https://www.acmicpc.net/problem/10026)

## 🅰 설계
- bfs 4방 탐색은 내가 자주 사용하는 방식을 그대로 이용했다.
- Queue를 이용하여 방문가능한 좌표 담고 방문처리해줌.(visited배열 사용)
- 
```java
static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};//상우하좌

...
탐색할 함수(r 좌표, c 좌표) {
    큐 queue 생성;
    queue.add(현재 방문할 좌표둘을 int 배열로 추가한다.);
    visited[r][c] = true;
    
    //큐가 비어있지 않은 경우 아래 반복
    for(int d = 0; d<4; d++){
        int newR = curR + dir[d][0];
        int newC = curC + dir[d][1];
        if(건너뛸 조건) continue;
        //건너뛰지 않는 경우 
        //map[newR][newC]를 방문해야할 조건
        //색약인 경우 현재 칸의 색이 B가 아니고 [newR][newC]도 B가 아니면 이어서 방문 가능 || 둘 다 B인 경우
        //색약이 아닌 경우는 현재칸 == [newR][newC]가 같은 경우
        //에 탐색을 진행하게 된다.
        if(위에 해당하는 조건){
            queue.add(new int[]{newR, newC});
            visited[newR][newC] = true;
        }
    }

}
```
이중 for 문으로 각 좌표를 돌면서 방문하지 않았다면 탐색함수 돌리기
이렇게 설계하고 바로 구현했다.

## ✅ BFS 유의사항
bfs에서 방문 처리를 할 때는 **반드시** queue에 담고 해주어야 한다!!! 꺼낼 때 방문처리를 하면 중복 방문이 발생할 수 있기 때문.

## 후기
짤 때는 같은 동작을 하는 함수를 두개나 만들었는데 리드미 작성하면서 보니까 함수 파라미터로 색약 여부를 하나 더 받아서 색약인 경우와 아닌 경우의 if 처리를 했다면 더 깔끔했을 것 같다.