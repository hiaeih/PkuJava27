public class Solution {
    public int countPrimes(int n) {
        if(n<=1) return 0;
        boolean[] arr=new boolean[n];
        for(int i=2;i<n;i++){
            arr[i]=true;
        }
        for(int i=2;i*i<n;i++){
            if(arr[i]==false) continue;
            else{
                for(int j=i*i;j<n;j+=i){
                    arr[j]=false;
                }
            }
        }
        int count=0;
        for(int i=2;i<n;i++){
            if(arr[i]==true) count++;
        }
        return count;
    }
}