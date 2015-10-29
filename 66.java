import java.util.Scanner;
/*Given a non-negative number represented as an array of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list.
Subscribe to see which companies asked this question*/
public class Solution {
    public int[] plusOne(int[] digits) {
        int flag = 1;
        int i = 0;
        for(i = digits.length - 1;i >= 0;i--)
       {
           digits[i] = digits[i] + flag;
            if(digits[i] > 9)
            {
                digits[i] = 0;
            }else
                return digits;
       }
       int[] digits2 = new int[digits.length + 1];
       if(i == -1 && flag == 1)
        {
            digits2[0] = 1;
            for(i = 1;i < digits2.length;i++)
                digits2[i] = digits[i - 1];
        }
        return digits2;
    }
}