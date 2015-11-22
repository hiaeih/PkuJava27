public class Solution {
    public int reverse(int x) {
        //|x|<10的情况
        if(Math.abs(x)>=0&&Math.abs(x)<10) return x;
        //x的绝对值大于10的情况
        else{
        int flag=0;
        String s=String.valueOf(x);
        char[] arr=s.toCharArray();
        if(arr[0]=='-') flag=1;
        else  flag=0;
        int i=arr.length-1;
        while(arr[i]=='0'){
            i--;
        }
        double sum=0;
        int val=10;
        //正数的求解方法
        if(flag==0){
        for(;i>=0;i--){
            sum=sum*val+arr[i]-'0';
        }
        //越界处理
        if(sum>Integer.MAX_VALUE) return 0;
        else return (int)sum;
        }
        //负数的求解方法
        else if(flag==1){
            for(;i>=1;i--){
            sum=sum*val+arr[i]-'0';
        }
        //越界处理
        if(sum>Integer.MAX_VALUE) return 0;
        else 
        {
            sum=-sum;
            return (int)sum;
        }
        }
        return 0;
    }
    }
}