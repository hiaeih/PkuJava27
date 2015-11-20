public class Solution {
    //重点学习了StringBuilder的用法
    public String reverseWords(String s) {
        if(s.length()==0||s==null) return "";
        String[] s1=s.split(" ");
        StringBuilder s2=new StringBuilder();
        for(int i=s1.length-1;i>=0;i--){
            if(!s1[i].equals(""))
            {
             s2.append(s1[i]).append(" ");
            }
        }
        return s2.length()==0?"":s2.substring(0,s2.length()-1);
    }
}