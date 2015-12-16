/*Given an integer n, return the number of trailing zeroes in n!.
Note: Your solution should be in logarithmic time complexity.*/
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        if(n < 1)
            return 0;
        //The Limit Exceeded
        int result = 0, count = 0;
        /*while(n > 1)
            result *= n;
        String res = String.valueOf(result);
        char[] arr = res.toCharArray();
        for(int i = arr.length - 1; i > 0; i++)
            if(arr[i] == '0')
                count++;
            else
                break;*/
        /*�ֽ�����, ���ҽ��� �����г��� һ�� (2,5)ʱ, �����������һ�� trailing zero.
        1.  2�ĸ�����Զ����5������.
        2.  ����5�ĸ���ʱ, ��򵥵ķ����� SUM(N/5^1,  N/5^2, N/5^3...)*/
        while(n/5 > 0) {    
            n /= 5;   
            count += n;   
        }
        return count;
    }
}