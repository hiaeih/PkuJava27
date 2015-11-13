public class Solution {
    public int myAtoi(String str) {
        if(str==" "||str.length()==0) return 0;
        //除去字符串两端的空格
        String str1=str.trim();
        int flag=0;
        //因为要判断是否超出整数范围，故sum类型不能为整型，否则无法判断
        double sum=0;
        int i=0;
        if(str1.charAt(i)=='+') {flag=0;i++;}
        else if(str1.charAt(i)=='-') {flag=1;i++;}
        else if(str1.charAt(i)<'0'||str1.charAt(i)>'9') return 0;
        while(i<str1.length()){
            //如果字符串中出现非0-9之间的字符时，就跳出循环
            if(str1.charAt(i)<'0'||str1.charAt(i)>'9') break;
            else sum=sum*10+(str1.charAt(i)-'0');
            i++;
        }
        if(flag==1) sum=-sum;
        if(sum>Integer.MAX_VALUE) sum=Integer.MAX_VALUE;
        if(sum<Integer.MIN_VALUE) sum=Integer.MIN_VALUE;
         //该函数返回值为int，而sum定义为double，所以要强制类型转换
        return (int)sum;
    }
}
