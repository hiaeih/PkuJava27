//Remove Element
public class Solution {
    public int removeElement(int[] nums, int val) {
		//使用一个数count记录并返回length-count
		int size = nums.length;
		int count =0;
		// for(int i =0;i<size;i++){
		for(int i=0;i<size-count;i++){
			if (nums[i]==val){
				//数组长度-1，当前数和尾部数交换
				int temp=nums[i];
				// nums[i]=nums[size-1];
				nums[i]=nums[size-count-1];
				count++;
				i--;
			}
		}
        return size-count;
    }
}