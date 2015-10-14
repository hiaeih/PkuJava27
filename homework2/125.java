public class Solution {
    public boolean isPalindrome(String s) {
//        int num0=s.length();
        //¿Õ×Ö·û´®
        if(s=="")return true;
        s=s.replaceAll("[\\p{Punct}\\p{Space}]+", "");
        s=s.toLowerCase();//s=s.toLowerCase(s);
        
        int num=s.length();
        if(s.length()==1){
            return true;
        }else{
            for(int i=0;i<(num/2);i++){
                if(s.charAt(i)!=s.charAt(num-1-i)){
                    return false;
                }
            }
        }
        return true;
        
    }
}