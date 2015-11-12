// Majority Element
//Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.
//You may assume that the array is non-empty and the majority element always exist in the array.
public class Solution {
    public int majorityElement(int[] nums) {
		//1.思路 排序后寻找中位数返回 时间复杂度O(nlogn)
		Arrays.sort(nums);
		int median=0;
		if(nums.length%2==0){
			// median=nums.length/2;
			median=nums.length/2-1;
		}else{
			// median=(nums.length+1)/2;
			median=(nums.length+1)/2-1;
		}
        return nums[median];
    }
}