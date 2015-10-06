public class Solution {
    public int myAtoi(String str) {
		str = str.trim();
		int flag = 0;
		String resultString = "";
		if (str.length() == 0 | str == null) {
			return 0;
		} else {
			for (int i = 0; i < str.length(); i++) {
				// Runtime Error Message:Line 15:
				// java.lang.NumberFormatException: For input string: "" Last
				// executed input:"+1"
				if (str.charAt(i) == '+' && flag == 1)
					return 0;
				else if(str.charAt(i) == '+') {
					flag = 1;
					continue;
				}
				if (str.charAt(i) == '-'){
					//判断该-是否为非法字符
					if (flag == 0) {
						{
							flag = 1;
							resultString +="-";
						}
					}else
						return 0;
				}else if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
					resultString += str.charAt(i);
				}else{
					break;
				}
			}
			
			//java.lang.NumberFormatException: For input string: "",可能为"---abc",应输出0
			//如果得到的字符串中没有数字，则为---仍然输出0
			if (resultString.equals("") || resultString.matches("[^0-9]*$") || resultString.equals("-")) {
				return 0;
			}else if(isJ(resultString)){
				return 0;
			}
			System.out.println(resultString);
			//java.lang.NumberFormatException: For input string: "9223372036854775809"对于超过int范围内的，仍输出int最大值
			//说明为负数
			if (resultString.charAt(0) == '-') {
				String s = resultString.substring(1, resultString.length());
				if (s.length() == 10) {
					if (Long.parseLong(s) >= Math.pow(2, 31)) {
						return (int) ((int) -1 * (Math.pow(2, 31)));
					}
				} else if (s.length() > 10) {
					return (int) ((int) -1 * Math.pow(2, 31));
				}
			}
			else if (resultString.length() > 10 || Long.parseLong(resultString) >= Math.pow(2, 31)){
				return (int) Math.pow(2, 31);
			}
			return Integer.parseInt(String.valueOf(resultString));
		}
	}
	private boolean isJ(String resultString) {
		int count = 0;
		for (int i = 0; i < resultString.length(); i++) {
			if (resultString.charAt(i) == '-') {
				count++;
			}
		}
		return count>1?true:false;
	}
}