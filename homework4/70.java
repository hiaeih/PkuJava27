public class Solution {
    public int climbStairs(int n) {
       /* //�õݹ鷽�����
        if(n==0||n==1)
        return n;
        
        else return climbStairs(n-1)+climbStairs(n-2);*/
        
        //��쳲������������
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