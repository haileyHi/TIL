class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        for (int d = 1; d <= len / 2 + 1; d++) { // 반복 단위는 len/2 넘을 수 없음.
            StringBuilder sb = new StringBuilder();
            int cnt = 1;
            String tmp = s.substring(0, d); // 맨 앞 강제로 담기.
            for (int i = d; i < len; i += d) { // 문자열 반복 확인 용도.
                if (len - i < d) { // 쪼갤 단위보다 작은 길이로 남았으면 그 전까지 비교했던 단위 담고 남은 것 tmp로 저장.
                    if (cnt > 1) {
                        sb.append(cnt);
                    }
                    sb.append(tmp);
                    cnt = 1;
                    tmp = s.substring(i);
                    break;
                }
                String cur = s.substring(i, i + d);
                if (cur.equals(tmp)) { // 앞의 단위와 같으면 cnt 증가시키기.
                    cnt++;
                } else {
                    // 앞의 단위와 다르면 현재 저장한 cnt + tmp를 sb에 저장하고 지금 단위 tmp 에 저장하기.
                    if (cnt > 1) {
                        sb.append(cnt);
                    }
                    sb.append(tmp);

                    cnt = 1;
                    tmp = cur;
                }
            }
            // 마지막 비교 대상 처리하기.
            if (cnt > 1) sb.append(cnt);
            sb.append(tmp);
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}
