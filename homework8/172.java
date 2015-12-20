public class Solution {
    public int trailingZeroes(int n) {
        /**
         * 没有思路，参考网上的
         * ①2*5=10
         * ②n!=2^x*3^y*5^z*...(证明可知x>z，因此以“5”作为标准统计)
         * ③zeors(n)=n/5 + n/25 + n/125 ...(!!不理解，为什么还需要25,125,,,)
         * */
         int sum=0;
         while(n>0){
             sum += n/5;
             n /= 5;
         }
         return sum;
    }
}