public class ClimbingStairs{
    public int climbStairs(int n) {
        int first = 1;
        int second = 2;
        //�Ʋ���������,�ݹ�,��ʱ
        if(n == 1)
            return first;
        if(n == 2)
            return second;
        /*else
            return climbStairs(n-1) + climbStairs(n-2);*/
        //��֦
        for(int i = n;i > 2; i--){
            second += first;
            first = second - first;
        }
        return second;
    }
}