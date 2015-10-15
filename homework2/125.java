//Valid Palindrome
public class Solution {
    public boolean isPalindrome(String s) {
        //when the string is empty ,return true.
		if(s.length()==0){
			return true;
		}
		s=s.toLowerCase();
		//replace all the punctuation to ""使用正则表达式
		// s=s.replaceAll("[\\p{Punct}\\p{Space}]+", "")
		// s=s.replaceAll("[\\pP‘’“”]", "");
		// s=s.replaceAll(" ","");
		//使用StringBuilder筛选非数字和字母字符利用了 isLetterOrDigits()
		StringBuilder stringBuilder = new StringBuilder();
        for(int i =0;i<s.length();i++){
            if(Character.isLetterOrDigit(s.charAt(i)))
                stringBuilder.append(s.charAt(i));
        }
        s=stringBuilder.toString();
		for(int i =0;i<s.length()/2;i++){
				if(s.charAt(i)!=s.charAt(s.length()-i-1)){
					return false;
				}
		}
		return true;
    }
}