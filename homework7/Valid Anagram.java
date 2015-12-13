//Valid Anagram

//Given two strings s and t, write a function to determine if t is an anagram of s.

// For example,
// s = "anagram", t = "nagaram", return true.
// s = "rat", t = "car", return false.
public class Solution {
    public boolean isAnagram(String s, String t) {
		if(s.length()!=t.length()) return false;
        //1.排序后比较
		// char[] c1=new char[s.length()];
		// char[] c2=new char[t.length()];
		// for(int i =0;i<s.length();i++){
			// c1[i]=s.charAt(i);
			// c2[i]=t.charAt(i);
		// }
		// Arrays.sort(c1);
		// Arrays.sort(c2);
		// for(int i =0;i<s.length();i++){
			// if(c1[i]!=c2[i]){
				// return false;
			// }
		// }
		// return true;
		
		// char[] c1 = s.toCharArray();  
        // char[] c2 = t.toCharArray();          
        // Arrays.sort(c1);  
        // Arrays.sort(c2);  
        // return Arrays.equals(c1, c2); 
		
		
		//2.使用HashMap
    }
}