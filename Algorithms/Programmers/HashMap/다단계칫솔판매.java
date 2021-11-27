import java.util.*;

public class PRG_Pyramid {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] tmp = solution(enroll, referral, seller, amount);
        for (int i = 0; i < tmp.length; i++) {
            System.out.print(tmp[i] + " ");
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> map = new HashMap<>();
        int len = enroll.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            map.put(enroll[i], i);
        }

        Map<String, String> refer = new HashMap<>();
        for (int i = 0; i < len; i++) {
            refer.put(enroll[i], referral[i]);
        }

        int soldCnt = seller.length;
        Map<String, Integer> pyramidBenefit = new HashMap<>();
        for (int i = 0; i < soldCnt; i++) {
            int money = 100 * amount[i];

            String name = seller[i]; // seller 이름 저장.

            Map<String, Integer> benefit = new HashMap<>();
            while (true) {
                String recommend = refer.getOrDefault(name, "null");
                if ("null".equals(recommend)) break;
                if (money == 0) break;
                int charge = money - (money / 10);
                if ((money / 10) >= 1) {
                    benefit.put(name, charge); //(money / 10) * 9);`
                } else {
                    benefit.put(name, money);
                }
                name = recommend;
//                if (money == 1) break;
                money /= 10;

            }
            for(String key: benefit.keySet()) {
                pyramidBenefit.put(key, pyramidBenefit.getOrDefault(key, 0) + benefit.getOrDefault(key, 0)); // 임시 이익을 전체 이익에 더하기.
            }
        }
        for (String key : pyramidBenefit.keySet()) { // 베네핏 얻은 애한테 수익 넘겨주기.
            answer[map.get(key)] = pyramidBenefit.get(key);
        }

        return answer;

    }
}
