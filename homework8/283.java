public class Solution {
    public void moveZeroes(int[] nums) {
        int i=0,count=0;
        if(nums.length==0||nums.length==1) return;
        for(int m=0;m<nums.length;m++){
            if(nums[m]==0) count++;
        }
        int q=0;
        while(i<nums.length){
            if(q==count) break;
            int j=i;
            if(nums[j]==0){
                q++;
                int tem=nums[j];
                for(int k=j+1;k<nums.length;k++){
                    nums[k-1]=nums[k];
                }
                nums[nums.length-1]=tem;
                i=j;
            }
            else i++;
        }
    }
}