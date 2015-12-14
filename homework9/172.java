public class Solution {
    //Time Limit Exceeded 
    /*public int trailingZeroes(int n) {
        double res=1;
        for(int i=n;i>=1;i--){
            res=res*i;
        }
        if(res>=Integer.MAX_VALUE) return 0;
        String s1=String.valueOf((int)res);
        char[] arr=s1.toCharArray();
        int m=0;
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]!=0) return m;
            else m++;
        }
        return 0;
    }*/
   /* 分解因子, 当且仅当 因子中出现 一对 (2,5)时, 最后结果会增加一个 trailing zero.
1.  2的个数永远多于5个个数.
2.  计算5的个数时, 最简单的方法是 SUM(N/5^1,  N/5^2, N/5^3...)*/
    public int trailingZeroes(int n) {
        if(n<1) return 0;
        int res=0;
        while(n/5!=0){
            res+=n/5;
            n/=5;
        }
        return res;
    }
}