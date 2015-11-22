public class Solution {
    public int singleNumber(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int m=nums[i];
            int j=0;
            for(int k=0;k<nums.length;k++){
                if(m==nums[k]) j++;
            }
            if(j==1) return nums[i];
            else continue;
        }
        return 0;
    }
}