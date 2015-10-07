//String to Integer (atoi)
public class Solution {
    public int myAtoi(String str) {
		if(str.length()==0){
            return 0;
        }
        str=str.trim();
        int sign=1;
        int index=0;
		//错误1，没有考虑没有"+" "-"号的情况
        if(str.startsWith("-")){
            sign=-1;
			str=str.substring(1);
        }else if(str.startsWith("+")){
			str=str.substring(1);
		}
		//错误2，int不够大
		// int result=0;
		long result=0;
        // for(int i=1;i<str.length();i++){
		  for(int i=0;i<str.length();i++){
            if(str.charAt(i)<'0'||str.charAt(i)>'9'){
				break;
			}
			result= result*10+str.charAt(i)-48;
		//
		    if(result>Integer.MAX_VALUE){break;}
        }
		result=result*sign;
		if(result>Integer.MAX_VALUE){return Integer.MAX_VALUE;}
		if(result<Integer.MIN_VALUE){return Integer.MIN_VALUE;}
		// return result;
		return (int)result;
    }
}