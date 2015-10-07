public class Solution {
    public boolean isHappy(int n) {
        int result = 0;
        boolean flag = false;
        int[] temp= new int[10001]; 
        String num = String.valueOf(n);
        while(result != 1)
        {
            result = 0;
            for(int i = 0;i < num.length();i++)
                result += Math.pow(num.charAt(i) - 48,2);
            num = String.valueOf(result);
            /*内存超过限制，开始没有进行重复的判断，导致如果不得1的进入死循环*/
            temp[result]++;
            if(temp[result] > 1)
              {
                  flag = false;
                  break;
              }
            else
                flag = true;
        }
        return flag;
    }
}