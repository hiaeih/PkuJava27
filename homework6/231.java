public class Solution {
    public boolean isPowerOfTwo(int n) {
        //�鿴�������е�1�ĸ�����ֻ��һ����true
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