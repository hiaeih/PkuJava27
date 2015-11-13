//Given an integer, convert it to a roman numeral.
//Input is guaranteed to be within the range from 1 to 3999.
public class Solution {
    public String intToRoman(int num) {
		//1到3999的数
		//罗马Ｉ、Ｖ、Ｘ、Ｌ、Ｃ、Ｄ和Ｍ，分别表示１、５、１０、５０、１００、５００和１０００
		
		StringBuilder result = new StringBuilder();  
		String[] numbers=  {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 }; 
		//刚开始用if，错误 【使用了runcode进行测试】
		// for(int i =0;i<values.length;i++){
			// if(num>=values[i]){
				// result.append(numbers[i]);
				// num=num-values[i];
			// }   
		// }
		for(int i =0;i<values.length;i++){
			while(num>=values[i]){
				result.append(numbers[i]);
				num=num-values[i];
			}   
		}
		return result.toString();
		
        
    }
}