public class Solution {
    public int trailingZeroes(int n) {
        /**
         * û��˼·���ο����ϵ�
         * ��2*5=10
         * ��n!=2^x*3^y*5^z*...(֤����֪x>z������ԡ�5����Ϊ��׼ͳ��)
         * ��zeors(n)=n/5 + n/25 + n/125 ...(!!����⣬Ϊʲô����Ҫ25,125,,,)
         * */
         int sum=0;
         while(n>0){
             sum += n/5;
             n /= 5;
         }
         return sum;
    }
}