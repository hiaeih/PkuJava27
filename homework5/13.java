/*12题解题思路是将罗马基本字符扩充为"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV"，"I"，
把一个整数从大开始试。所以本题解题思路是从字符串第一位开始，依次转化成数字相加，若本数字比上一个数字大，则要加上本数字减去2倍前一个数字的值。*/
public class Solution {
      public int tonum(char c){
            switch(c){
                case 'M':return 1000;
                case 'D':return 500;
                case 'C':return 100;
                case 'L':return 50;
                case 'X':return 10;
                case 'V':return 5;
                case 'I':return 1;
                default:return 0;
            }
        }
    public int romanToInt(String s) {
        int res=tonum(s.charAt(0));
        for(int i=1;i<s.length();i++)
        {
            if(tonum(s.charAt(i-1))>=tonum(s.charAt(i))){
                res+=tonum(s.charAt(i));
            }
            else{
                res+=tonum(s.charAt(i))-2*tonum(s.charAt(i-1));
            }
        }
        return res;
    }
}