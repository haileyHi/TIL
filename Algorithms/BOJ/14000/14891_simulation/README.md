# 14891번 톱니바퀴
[문제 보러가기](https://www.acmicpc.net/problem/14891)

## 🅰 설계

톱니바퀴가 시계방향으로 돌면

`01234567` -> `70123456` 이 되고

반시계 방향으로 돌면

`01234567` -> `12345670` 이 되므로 

처음에는 deque 덱 자료구조를 이용할 생각이었다. (addFirst, pollFirst, addLast, pollLast함수를 이용하기 위해)

그런데 회전을 시키면서 각 2번째, 6번째 위치의 톱니를 비교해야 하므로 별도의 배열을 따로 두기 귀찮아 보여서 `자료구조.get(index)` 를 쓸 수 있는 LinkedList를 사용했다.


```java
private static LinkedList<Integer>[] list = new LinkedList[4];
```

위의 LinkedList 형 배열에 4개의 톱니 정보를 잘 담아서 `list[순서].get(index)` 형식으로 사용하기 편했다.

몇 번째 톱니바퀴를 어떤 방향으로 선택해서 회전 시킬 때 다른 극의 톱니를 옆에 마주한다면 두 톱니는 서로 다른 방향으로 회전하고, 

서로 같은 극의 톱니를 마주하는 순간 그 방향으로의 회전은 일어나지 않는다.


그래서 회전할 톱니 정보를 입력받을 때마다 어떤 걸 어느 방향으로 회전시키고, 어떤 톱니를 고정시킬지 저장할 `int[] check`를 두었다.


먼저 회전시키는 톱니를 기준으로 왼쪽, 오른쪽의 회전 여부를 확인할 checkSmall, checkBig 함수를 만들어주었다.

### 회전 여부 파악

```java
private static void checkSmall(int num, boolean clockwise) {
    check[num] = clockwise ? 1: -1;
    while (num >= 1) {
        if(!list[num].get(6).equals(list[num - 1].get(2))){ // 둘 다 회전
            check[num - 1] = check[num] == 1? -1: 1;
        }else{ //현재 num만 회전
            check[num - 1] = 0;
            return;
        }
        num--;
    }
}

private static void checkBig(int num, boolean clockwise){
    check[num] = clockwise ? 1: -1;
    while (num < 3) {
        if (!list[num].get(2).equals(list[num + 1].get(6))) {
            check[num + 1] = check[num] == 1? -1: 1;
        }else{ // 현재 num만 회전
            check[num + 1] = 0;
            return;
        }
        num++;
    }
}
```

서로 양 끝에 닿기 전까지 이 전 톱니의 회전 정보를 이용해서 회전 정보를 갱신한다.(만약 옆에 있는 톱니와 맞닿은 부분이 같은 극이라면 회전을 멈추고 return

### 회전 시키기

각 톱니는 시계방향일 때

```java
int tmp = list[순서].getLast();
list[순서].addFirst(tmp);
```


반 시계 방향일 때
```java
int tmp = list[순서].getFirst();
list[순서].addLast();로 회전 시켜줄 수 있고
```

회전을 안 할 땐 그냥 다음으로 넘어가면 되므로

```java
private static void rotate() {
    for (int i = 0; i < 4; i++) {
        int tmp = 0;
        if (check[i] == 0) continue; //회전 안 함.
        if (check[i] == 1) { //시계 방향
            tmp = list[i].pollLast();
            list[i].offerFirst(tmp);
        }else{ // 반시계 방향
            tmp = list[i].pollFirst();
            list[i].offerLast(tmp);
        }
    }
}
```

## ✅ 후기

- 처음에 check함수에서 num만 갱신해서 회전이 연쇄적으로 일어나야 하는데 엉뚱하게 현재 상태만 이용해서 확인했다.
- 어떤 것에 영향을 받고 갱신해야 하는지 명확하게 확인하고 넘어갈 것!
- 
