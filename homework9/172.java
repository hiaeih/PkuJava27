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
   /* �ֽ�����, ���ҽ��� �����г��� һ�� (2,5)ʱ, �����������һ�� trailing zero.
1.  2�ĸ�����Զ����5������.
2.  ����5�ĸ���ʱ, ��򵥵ķ����� SUM(N/5^1,  N/5^2, N/5^3...)*/
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