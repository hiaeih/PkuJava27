//Contains Duplicate
//Given an array of integers, find if the array contains any duplicates. 
//Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
public class Solution {
    public boolean containsDuplicate(int[] nums) {
		//排序，然后从头检查相邻两项是否一样。
		nums=Arrays.sort();
		for(int i = 0;i<nums.length-1;i++){
			if(nums[i]==nums[i+1])
				return true;
			
		}
		return false;
        
    }
}