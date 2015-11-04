public class Solution {
    public int climbStairs(int n) {
       /* //用递归方法求解
        if(n==0||n==1)
        return n;
        
        else return climbStairs(n-1)+climbStairs(n-2);*/
        
        //用斐波那契数列求解
        int a=0;
        int b=1;
        int fib=0;
        int i=1;
        while(i<=n){
        fib=a+b;
        a=b;
        b=fib;
        i++;
        }
        return fib;
    }
}