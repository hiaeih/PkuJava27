public class Solution {
    public int removeDuplicates(int[] nums) {
      if(nums.length==0) return 0;
      //if(nums.length==1) return 1;
      //设置count很重要
      int count=1;
      for(int i=1;i<nums.length;i++){
          if(nums[i-1]!=nums[i]) {
              nums[count]=nums[i];
              count++;
      }
      }
      return count;
    }
}