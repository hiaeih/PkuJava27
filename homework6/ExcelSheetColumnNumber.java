/*Related to question Excel Sheet Column Title
Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 26*1+1
AB -> 28
...
BA ->26*2+1
NA -> 26*(toLowerCase-64) + 1
Math
*/
public class Solution {
    public int titleToNumber(String s) {
        char[] characterSet = s.toCharArray();
        int result = 0;
		for(int i = 0; i < characterSet.length; i++){
			result = result*26 + (characterSet[i] - 64);
		}
		return result;
    }
}