public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        String num = Integer.toBinaryString(n);
        System.out.println(num);
        for(int i = 0;i < num.length();i++){
           if(num.charAt(i) == '1')
                count++;
        }
        return count;
    }
}