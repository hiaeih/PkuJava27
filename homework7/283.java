public class Solution {
    public void moveZeroes(int[] nums) {
        //int sum=0;//统计0的个数
        for(int i=0;i<nums.length;i++)
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==0 && nums[j]!=0){
                    //sum++;
                    //while(nums[j]!=0){
                        nums[i]=nums[j];
                        nums[j]=0;
                        break;
                    // }else {
                        
                    // }
                }
            }
    }
}