public class Solution {
    public void rotate(int[] nums, int k) {
		k = k % nums.length;//降低代码运行时间，避免重复运行
		if (k == 0 || nums.length == 1)
			return;
		else {
		    reversal(nums,0,nums.length - k - 1);
		    reversal(nums,nums.length - k,nums.length-1);
		    reversal(nums,0,nums.length - 1);
		}
    }
    public static int[] reversal(int nums[], int begin, int end) {
		for (int i = begin,j = end; i <= (begin + end) / 2; i++,j--) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
		return nums;
	}
}