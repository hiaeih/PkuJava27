public class Solution {
    public String reverseWords(String s) {
        String string = s.trim();//��ʼû�ӣ����¿ո�����
		if (string.length() == 0 || string.equals(" ")) {
			return string;
		}else {
			String[] str = string.split(" ");
			String reverse = "";
			for (int i = 0; i < str.length; i++) {
				if (!str[str.length - 1 - i].equals("")) //��ʼ������жϵģ������ж��ַ�������if (str[str.length - 1 - i] != "") 
					reverse += str[str.length - 1 - i] + " ";
			}
			return reverse.substring(0,reverse.length() - 1);
		}
    }
}