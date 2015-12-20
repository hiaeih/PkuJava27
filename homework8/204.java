public class Solution {
    public int countPrimes(int n) {
        int sum=0;
        
        if(n<2) return 0;//if(n==1) return 0;
        
        int[] flag=new int[n];//用于标记质数（1）与非质数（0）
        for(int i=0;i<n;i++){
            flag[i]=1;
        }
        /**
         * 思路：
         * 依次将2、3、5、7、11、13...的倍数拿掉，剩下的即为所求
         * */
        for(int i=2;i*i<n;i++){
            if(flag[i]==1){
                for(int j=2;j*i<n;j++){
                    flag[j*i]=0;
                }
            }
        }
        for(int i=2;i<n;i++){
            if(flag[i]==1){
                sum++;
            }
        }
        return sum;
    }
}