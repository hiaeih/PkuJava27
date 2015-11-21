public class Solution {
    //·½·¨Ò»£ºµÝ¹é
   /* public int addDigits(int num) {
        int sum=0;
        if(num>=0&&num<10) return num;
        else {
            while(num!=0){
                sum+=num%10;
                num/=10;
            }
        }
        if(sum>=0&&sum<10) return sum;
        else  return addDigits(sum);
    }*/
    public int addDigits(int num) {
       
        while(num>=10){
            String s=String.valueOf(num);
            char[] arry=s.toCharArray();
            int sum=0;
            for(int i=0;i<arry.length;i++){
                sum+=arry[i]-'0';
            }
            num=sum;
        }
        return num;
    }
}