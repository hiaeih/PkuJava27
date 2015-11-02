public class Solution {
    public int climbStairs(int n) {
        //递归，n=44时，Time Limit Exceeded
        // if(n==1) {
        //     return 1;
        // }
        // if(n==2){
        //     return 1;
        // }
        
        // return climbStairs(n-1)+climbStairs(n-2);
        
        
        //斐波那契数列 f(n)=f(n-1)+f(n-2)  f(1)=1,f(2)=1
        int sum1 = 1;
        int sum2 = 1;
        int sum = 0;
        
        if(n == 0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        
        for(int i=2;i<=n;i++){
            sum = sum1 + sum2;
            sum1 = sum2;
            sum2 = sum;
        }
        
        return sum;

    }
}