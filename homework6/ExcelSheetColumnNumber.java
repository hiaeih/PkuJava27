// Excel Sheet Column Number
// Related to question Excel Sheet Column Title
// Given a column title as appear in an Excel sheet, return its corresponding column number.
// For example:

    // A -> 1
    // B -> 2
    // C -> 3
    // ...
    // Z -> 26
    // AA -> 27
    // AB -> 28 
public class Solution {
    public int titleToNumber(String s) {
		//26进制转化为10进制
		int result =0;
        int digit = 1;
		//转换成字符数组
        char[] temp = s.toCharArray();
        for(int i=temp.length-1;i>=0;i--){
			int number= int(temp[i]-'A')+1;
            result =result+number*deliver;
            deliver=deliver*26;
        }
        return result;
    }
  
}