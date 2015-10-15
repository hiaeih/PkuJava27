//Single Number
public class Solution {
    public int singleNumber(int[] nums) {
     // have a linear runtime complexity
	 //without using extra memory
	 //线性时间复杂度，同时空间复杂度为O(1)
	 //不能使用查找算法
	 //使用按位异或的方法
	 int result = 0;
	 for(int i =0;i<nums.length;i++){
		 result=result^nums[i];
	 }
     return result;
    }
}