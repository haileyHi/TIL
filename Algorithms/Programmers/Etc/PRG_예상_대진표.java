class Solution
{
    public int solution(int n, int a, int b)
    {
        int cnt = 1;
        while(true){
            int aNo = (a + 1) / 2;
            int bNo = (b + 1) / 2;
            if (aNo == bNo){
                return cnt;
            }else {
                cnt++;
                a = (a + 1) / 2;
                b = (b + 1) / 2;
            }
        }

    }
}
