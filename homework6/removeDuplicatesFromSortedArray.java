/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
For example,
Given input array nums = [1,1,2], 
Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
Array TwoPointers.
*/
public class removeDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
		int len = nums.length;
        if(nums.length < 2)
			return len;
		/*int first = 0,last = first + 1;
		while(first <= last && last < nums.length){
			if(nums[first] == nums[last]){
				len--;
			}
			first = last;
			last++;
		}
		return len;*/
		int count = 1;
		int next = 1,pre = next - 1;
		for(; next < len; next++){
			pre = next - 1;
			if(nums[pre] != nums[next]){
				nums[count]=nums[next];
				count++;
			}
		}
		return count;
    }
}