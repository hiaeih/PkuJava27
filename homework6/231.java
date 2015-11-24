public class Solution {
    public boolean isPowerOfTwo(int n) {
        //查看二进制中的1的个数，只有一个则true
        String st = Integer.toBinaryString(n);
        if(n==-2147483648){
            return false;
        }
        //System.out.println(st);
        int sum = Integer.bitCount(n);
        if(sum==1) {
            return true;
        }
        return false;
    }
}