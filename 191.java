//Number of 1 Bits
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        String s =Integer.toBinaryString(n);
        int num=0;
		//遍历字符串，遇到1的位返回值数量加1
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                num++;
            }
        }
        return num;
    }
}