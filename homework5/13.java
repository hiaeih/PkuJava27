/*12�����˼·�ǽ���������ַ�����Ϊ"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV"��"I"��
��һ�������Ӵ�ʼ�ԡ����Ա������˼·�Ǵ��ַ�����һλ��ʼ������ת����������ӣ��������ֱ���һ�����ִ���Ҫ���ϱ����ּ�ȥ2��ǰһ�����ֵ�ֵ��*/
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