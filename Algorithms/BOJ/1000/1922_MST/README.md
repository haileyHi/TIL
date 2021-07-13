# 1922번 네트워크 연결
[문제 보러가기](https://www.acmicpc.net/problem/1922)

## 🅰 설계

조건 확인
- N개의 컴퓨터를 사이클이 없도록 연결하는 최소 비용 구하기

---

MST를 구하는 두가지 방법으로 프림 알고리즘과 크루스칼 알고리즘이 있는데

이번에는 크루스칼 알고리즘으로 풀이를 진행했다.

(간선을 기준으로 오름차순 정렬 후 사이클이 생기지 않는지 확인하면서 비용이 낮은 간선을 추가해나가는 방법)

사이클을 이루는지 확인하면서 부모를 연결해주는 union함수

```java
private static boolean union(int a, int b){
    int aRoot = findParent(a);
    int bRoot = findParent(b);
    if(aRoot == bRoot) return false;
    if(a > b){
        int tmp = aRoot;
        aRoot = bRoot;
        bRoot = tmp;
    }
    roots[bRoot] = aRoot;
    return true;
}
```
입력받은 두 번호의 컴퓨터의 루트를 찾아서 일치하면 사이클을 이루고 있는 것이니 false,
일치하지 않으면 사이클을 이루지 않았으니 둘 중 작은 번호의 컴퓨터 루트로 다른 컴퓨터의 루트 번호를 추가해준다.
더 작은 번호의 컴퓨터로 루트를 몰아주기 위해서 if(a >b) {} 부분을 넣어주었다.

그리고 각 컴퓨터의 루트 노드를 찾기 위한 findParent() 함수

```java
    private static int findParent(int a){
        if(roots[a] == a) return a;
        return roots[a] = findParent(roots[a]);
    }
```
(맨 처음에 각 컴퓨터의 roots[i] = i;로 초기화를 진행함)

이 두 함수와 Comparable을 implement 한 Edge 클래스를 이용해서 풀이를 진행하였다.


## ✅ 후기

- 입력받는 간선 정보를 `Edge[]`에 담았는데 우선순위 큐`PriorityQueue<Edge>`에 담아도 될 것 같다.

- 각 정점을 기준으로 하는 프림 알고리즘으로 푼다면 모든 간선을 확인하고 짧은 노드부터 사이클을 이루는지 확인하면서 추가해주면 된다.
