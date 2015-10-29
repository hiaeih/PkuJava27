public class Solution {
   public static void main(String[] args) {
		// TODO Auto-generated method stub
        String test="a a";
        System.out.println(lengthOfLastWord(test));
        		
	}
    public static int lengthOfLastWord(String s) {
       if(s.trim().equals(" ")||s.length()==0) return 0;
       String[] ss=s.trim().split(" ");
       return ss[ss.length-1].length();
    }
}