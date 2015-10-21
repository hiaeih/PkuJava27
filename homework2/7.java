package week2;

public class Seven {
	public static int reverse(int x) {
		/*int res = 0;
		while(Math.abs(x) != 0){
			if (Math.abs(res) > Integer.MAX_VALUE) {
				return 0;
			}
			res = res*10 + x%10;
			x /=10;
		}
		return res;*/
		
		String str = String.valueOf(x);
		String str2 = "";
		boolean flag = false;
		if (x < 0) {
			str2 = str.substring(1,str.length());
			flag = true;
		}else 
			str2 = str;
		StringBuffer strBuf = new StringBuffer(str2);
		strBuf.reverse();
		String s = strBuf.toString();
		if (flag) {
			s = "-" + s;
		}
		//相当于reverse算法
		/*for (int i = 0; i < strBuf.length() / 2; i++) {
			char temp = strBuf.charAt(i);
			strBuf.setCharAt(i, strBuf.charAt(strBuf.length() - i - 1));
			strBuf.setCharAt(strBuf.length() - i - 1, temp);
		}*/
		Long long1 = Long.parseLong(s);
		if (long1 <= Integer.MIN_VALUE || long1 >= Integer.MAX_VALUE) {
			return 0;
		}else {
			return Integer.parseInt(s);
		}
	}
}
