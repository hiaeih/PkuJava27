//Move Zeros
//Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

//For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
public class Solution {
    public void moveZeroes(int[] nums) {
		//顺序错误
		// int start = 0;
		// int end = nums.length-1;
		// while(start<end){
			// if(nums[start]==0){
				// nums[start]=nums[end];
				// nums[end]=0;
				// end--;
			// }
			// if(nums[start]!=0){
				// start++;
			// }
		// }
		//对非0的数计数
		int number=0;
		for(int i =0;i<nums.length;i++){
			if(nums[i]!=0){
				nums[number]=nums[i];
				number++;
			}
		}
		for(int i = nums.length-1;i>=number;i--){
			nums[i]==0;
		}
		
        
    }
}