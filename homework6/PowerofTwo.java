/*Given an integer, write a function to determine if it is a power of two. 
Math	Bit Manipulation
*/
import java.lang.*;
public class PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        //2^m=n->m=logn,m属于整数
		//double num = (double)n;
		//return Math.log(num) instanceof Integer?true:false;
		//因为2的幂次特点：其对应的二进制在首位为1，所以也他下一位与，结果为0一定是2的幂次
		if(n == 0 || n < 0)
		    return false;
		n = n&(n-1);
		return (n == 0)?true:false;
    }
}