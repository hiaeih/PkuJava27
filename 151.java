public class Solution {
    public String reverseWords(String s) {
        String string = s.trim();//开始没加，导致空格问题
		if (string.length() == 0 || string.equals(" ")) {
			return string;
		}else {
			String[] str = string.split(" ");
			String reverse = "";
			for (int i = 0; i < str.length; i++) {
				if (!str[str.length - 1 - i].equals("")) //开始用这个判断的，错误判断字符串问题if (str[str.length - 1 - i] != "") 
					reverse += str[str.length - 1 - i] + " ";
			}
			return reverse.substring(0,reverse.length() - 1);
		}
    }
}