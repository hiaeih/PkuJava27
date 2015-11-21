public class Solution {
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(val==nums[i]){
                if(i==n-1) n=n-1;
                else{
                for(int j=i+1;j<nums.length;j++){
                    nums[j-1]=nums[j];
                }
                i=i-1;
                n=n-1;
                }
            }
        }
        return n;
    }
}