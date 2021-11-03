## ✅문제 (프로그래머스 순위검색)
[프로그래머스 순위검색(문제바로가기)](https://programmers.co.kr/learn/courses/30/lessons/72412)

### 접근

조건 4개와 점수를 이용해서 필터링해야겠다고 생각했지만 효율성을 따지는 문제이니만큼 입력 값이 아주 컸다.

그대로 풀었다가는 백퍼 시간 초과다! 싶어서 다양한 방법을 생각해보았다.

`String[] query`는 검색 조건, `String[] info`는 지원자 정보

1. ~처음 떠올린대로 각 query조건을 stringTokenizer로 가져와서 for문을 걸어서 모든 지원자와 1:1 비교하며 카운팅 하기.~
2. int[언어][파트][경력][소울푸드] 배열에 점수 넣어서 4중 for문으로 돌려보기 -> 같은 점수가 있을 수도 있음
3. 지원자 조건을 key로 검색하기 (map의 get이 O(1)인 걸 생각하면 key에 가능한 모든 경우의 수를 다 넣어도 조건이 4개 밖에 없으니까 더 이득이라고 생각했다.)

### 설계
`Map<String, Integer> map` 이렇게 만들려고 했는데 생각해보니 2번에 떠올렸던 같은 점수에 대한 처리를 어쨌든 해줘야 했다.

그렇다면 다익스트라 문제 풀 때 버스 노선을 도시 별로 주렁주렁 달았듯 모든 지원자의 점수를 키에 주렁주렁 달아주자..

만만한 HashMap을 이용해서 value에는 ArrayList<Integer>를 넣고 Key는 String으로 사용했다. 
  
```java
  Map<String, ArrayList<Integer>> map = new HashMap<>();
  /*
  틀을 만들었으니 key를 생성해줘야 한다.
  지원자 정보 or "-" 로 생성해주면 되니까
  */
  void 맵에_담는_함수(String lan, String part, String exp, String food, int score){ // 이걸 인자로 받는 함수를 만들었다.
      for (int l = 0; l < 2; l++) {
          String tmp_l = l == 0 ? lan : "-";
          for (int p = 0; p < 2; p++) {
              String tmp_p = p == 0 ? part : "-";
              for (int e = 0; e < 2; e++) {
                  String tmp_e = e == 0 ? exp : "-";
                  for (int f = 0; f < 2; f++) {
                      String tmp_f = f == 0 ? food : "-";
                      String key = tmp_l + tmp_p + tmp_e + tmp_f;
                      // 이렇게 - 조건 필터링이나 지원자 정보 필터링에 써먹을 수 있는 키가 생성되었다. map에 넣어주자.
                      // 1) 이미 map에 있는 키면 담겨져 있는 ArrayList<Integer>를 꺼내서 score를 새로 add하고
                      // 2) map에 없는 key라면 new ArrayList<Integer> 에 score를 add한다.
                  }
              }
          }
      }
  }
```
  
위 함수를 이용해서 맵에 사이 좋게 지원자들의 정보를 담았다. ( 정보들은 하나의 키로 모든 지원자 데이터를 검색할 수 있도록 중복돼서 잘 담겨졌다)

사실 이러고 까먹고 바로 조건마다 if문 걸면서 지원자 수 카운팅했다가 보기 좋게 효율성이 0점이 나왔다. ㅎㅎ,,,
  
값을 ArrayList<Integer>로 만들었으니 효율적으로 각 맵 안에 들어있는 값들을 정렬해서 필요한 개수만 구해야 한다!
  
map에서 key를 가져오는 keySet()은 써봤는데 value만 가져오는 values()나 <K, V>를 모두 가져오는 entrySet()은 써본 적이 없어서 이번에 사용!
```java
    for (ArrayList<Integer> item : map.values()) {
        Collections.sort(item);
    }
   // 이거나
    for (Map.Entry<String, ArrayList<Integer>> item : map.entrySet()){
        Collections.sort(item.getValue());
    }
  // 이거나 안에 들어있는 값 정렬하는 건 똑같다.
```

**Collections.sort()는 Tim 정렬을 사용하고 평균, 최악 시간복잡도가 모두 O(NlogN)이다.**

정렬을 했으면 검색 기준이 되는 인덱스를 찾아야 한다. for문으로 하나하나 찾을 수 없으니 이분 탐색을 사용했다.

풀이 과정은 다음과 같다!

### 풀이 과정
![image](https://user-images.githubusercontent.com/23499504/140072112-54281877-0bd3-490d-933e-f3c3fd33d1aa.png)
  
## 📍느낀 점

- 기본이 되는 정렬 알고리즘, 탐색 알고리즘 직접 구현해 볼 것... 머리로 이해하는 것과 손으로 짜는 건 역시 달랐다!

- 조건 잘 확인하고 넘기기
  



