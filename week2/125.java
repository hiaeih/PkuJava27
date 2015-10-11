package week2;

import java.util.Stack;

public class OneHundredandTwentyfive {
	public static boolean isPalindrome(String s) {
		boolean flag = false;
		if(s==null||s.length()==0) return true;
		String str = "";
		s = s.trim();
		//运行超时
		/*for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) <= 'z' && s.charAt(i) >= 'a')
					|| (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A')) {
				st = "" + s.charAt(i);
				str += st.toLowerCase();
			}
		}*/
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		int i = 0;
		for (i = 0; i < s.length()/2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)){
				flag = false;
				break;
			}
		}
		if (i == s.length() / 2)
			flag = true;
		return flag == true ? true : false;
		/*
		int len = s.length();
		if (len < 2)
			return true;

		Stack<Character> stack = new Stack<Character>();

		int index = 0;
		while (index < len / 2) {
			stack.push(s.charAt(index));
			index++;
		}
	 
		if (len % 2 == 1)
			index++;
	 
		while (index < len) {
			if (stack.empty())
				return false;
			char temp = stack.pop();
			if (s.charAt(index) != temp)
				return false;
			else
				index++;
		}
		return true;*/
	}
}

