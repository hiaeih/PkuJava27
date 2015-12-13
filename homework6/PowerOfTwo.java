//Power of Two
//Given an integer, write a function to determine if it is a power of two.
public class Solution {
    public boolean isPowerOfTwo(int n) {
        //判断一个数是否是2的幂
		while (n%2==0&&n!=1){
			n/=2;		
		}
		if(n==1)
			return true;
		else
			return false;
    }
}