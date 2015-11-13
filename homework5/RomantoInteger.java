//Given a roman numeral, convert it to an integer.
//Input is guaranteed to be within the range from 1 to 3999.
public class Solution {
    public int romanToInt(String s) {
		int result=0;
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 }; 
		String[] numbers=  {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		//从前往后扫描，匹配一个罗马字符就截取一个。
		while(s.length()>0){
			for(int i =0;i<numbers.length;i++){
				while(s.startsWith(numbers[i])==true){
					result=result+values[i];
					s=s.substring(numbers[i].length(),s.length());
				}
					
			}
		}
		return result;
		
		
		
    }
}