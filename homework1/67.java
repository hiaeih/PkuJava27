public class Solution {
    int flag = 0;
	int l1,l2;
	String resultString = "";
	public String addBinary(String a, String b) {
		String aString = a;
		String bString = b;
		String resultString2 = "";
		int[] num1 = new int[aString.length()];//Integer.parseInt(aString);
		for (int i = 0; i < num1.length; i++) {
			num1[i] = aString.charAt(i) - 48;
		}
		int[] num2 = new int[bString.length()];//Integer.parseInt(bString);
		for (int i = 0; i < num2.length; i++) {
			num2[i] = bString.charAt(i) - 48;
		}
		l1 = num1.length - 1;
		l2 = num2.length - 1;
		int len = l1 < l2?l1:l2;
		while (len >= 0) {
			int temp = num1[l1] + num2[l2] + flag;
			if (temp > 1) {
				resultString += temp%2;//保证数字在0和1之间选择变化
				flag = 1;
			}else {
				resultString += temp;
				flag = 0;//开始未加，导致进位出错，有不连续部分可能多加了之前的一个进位
			}
			l1--;
			l2--;
			len = Math.min(l1, l2);
		}
		if (l1 >= 0)
			resultString = tranString(num1,l1);
		else if (l2>= 0)
			resultString = tranString(num2, l2);
		else if(flag == 1)
			resultString += "1";
		for (int i = resultString.length() - 1; i >= 0; i--) {
			resultString2 += resultString.charAt(i);
		}
		return resultString2;
	}
    private int isZero(int i) {
			return (int) (i == 2?0:1);
	}
	private String tranString(int[] num,int l){
		while (l >= 0) {
			if (isZero(num[l] + flag) == 0) {
				resultString += "0";
				l--;
			}else {
				resultString += num[l--] + flag;
				flag = 0;
			}
		}
		if (isZero(num[0] + flag) == 0){
			resultString +="1";
			flag = 0;
		}
		return resultString;
	}
}