import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        int len = gems.length;
        Set<String> kind = new HashSet<>();
        for (int i = 0; i< len; i++){
            kind.add(gems[i]); // 전체 보석 개수 세기
        }
        int gemCnt = kind.size();
        int[] arr = new int[len];
        int l = 0, r = 0;
        Map<String, Integer> map = new HashMap<>();
        int minLen = 100001;
        int minLIdx = 0, minRIdx = 0;
        while (r < len){
            map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);
            if (map.size() == gemCnt) { // 보석이 종류 만큼 다 있는 경우 (start 인덱스를 최대한으로 늘리기 위해서)
                while (true) {
                    if (map.get(gems[l]) > 1) { //(개수가 1보다 많은 경우라면 하나 제거 하고 왼쪽 인덱스 증가시키기)
                        map.put(gems[l], map.get(gems[l]) - 1);
                        l++;
                    } else if (map.get(gems[l]) == 1) { // (현재 왼쪽 인덱스가 1개라면 해당 l부터 r까지의 위치가 최소인 셈.)
                        if (minLen > (r - l)) {
                            minLIdx = l;
                            minRIdx = r;
                            minLen = r - l;
                        }
                        map.remove(gems[l++]); // map에서 l 없애고 l값 1증가 시키기
                        break;
                    }
                }
            }
            r++;
        }
        answer = new int[]{minLIdx + 1, minRIdx + 1};
        return answer;
    }
}
