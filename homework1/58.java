public class Solution {
    public int lengthOfLastWord(String s) {
		String string = s.trim();
		if (string.length() < 1)
			return 0;
		else{
			//Matcher matcher = Pattern.compile(".*[a-zA-Z]+.*").matcher(string);
				String[] str = string.split(" ");
				return str[str.length - 1].length();
		}
	}
}