import java.util.*;

class Solution {
    private static Map<String, ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        StringTokenizer st = null;
        
        for (String s : info) { // 각 지원자 정보 기준으로 가능한 모든 키 생성.
            st = new StringTokenizer(s);
            mapSetting(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        // map.get(key) 안에 있는 점수들 정렬하기. 
        // how to get both key and value in map. -> https://stackabuse.com/java-how-to-get-keys-and-values-from-a-map/
        /*
        for (Map.Entry<String, ArrayList<Integer>> item : map.entrySet()){
            Collections.sort(item.getValue());
        }
        */
        for (ArrayList<Integer> item : map.values()) {
            Collections.sort(item);
        }
        
        
        int idx = 0;
        for (String s : query) {
            st = new StringTokenizer(s, " ");
            int cnt = 0;
            String q_lan = st.nextToken();
            st.nextToken();
            String q_part = st.nextToken();
            st.nextToken();
            String q_exp = st.nextToken();
            st.nextToken();
            String q_food = st.nextToken();
            Integer q_score = Integer.parseInt(st.nextToken());
            // 지원자 필터링하기.
            String checkKey = q_lan + q_part + q_exp + q_food;
            if (map.containsKey(checkKey)) {
                int count = search(map.get(checkKey), q_score);
                cnt += count;
            }

            answer[idx++] = cnt;
        }
        
        return answer;
    }
    
    private static void mapSetting(String lan, String part, String exp, String food, int score){ // 지원자 정보 + "-" 로 만들어진 키 저장하기.
        // 지원자 정보 또는 -로 검색 가능해야 하므로 조건 16개
        for (int l = 0; l < 2; l++) {
            String tmp_l = l == 0 ? lan : "-";
            for (int p = 0; p < 2; p++){
                String tmp_p = p == 0 ? part : "-";
                for (int e = 0; e < 2; e++){
                    String tmp_e = e == 0 ? exp : "-";
                    for (int f = 0; f < 2; f++){
                        String tmp_f = f == 0 ? food : "-";
                        String key = tmp_l + tmp_p + tmp_e + tmp_f; // 그냥 모든 조합을 다 키로 만들어 버리자.
                        // 이미 있는 키면 현재 지원자의 score를 추가한 list를 담고 아니면 새로 생성한 arrayList에 score를 추가해서 담는다.
                        ArrayList<Integer> val = map.getOrDefault(key, new ArrayList<>());
                        val.add(score);
                        map.put(key, val);
                    }
                }
            }
        }
    }
    
    private static int search(ArrayList<Integer> list, int standard_score) {
        // standard보다 큰 갯수 반환하기. standard보다 작은 인덱스 찾기.
        int start = 0;
        int end = list.size();
        int mid;
        while (start < end){
            mid = (start + end) / 2;
            if (list.get(mid) >= standard_score) {
                end = mid;
            } else if (list.get(mid) < standard_score) {
                start = mid + 1;
            }
        }
        // start == end 면 standard보다 제일 작은 수의 인덱스(start == mid == end)를 뺀 나머지가 곧 standard 이상 점수를 받은 사람 수이다!
        return list.size() - end;
    }
}
