public class Solution {
    public String reverseWords(String s) {
        if(s.length()==0||s==null) return "";
        String[] s1=s.split(" ");
        String s2="";
        for(int i=s1.length-1;i>=0;i--){
             if(!s1[i].equals(""))
            {
             s2+=s1[i]+" ";
            }
        }
        return s2.length()==0?"":s2.trim();
    }
}