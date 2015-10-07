public class Solution {
    public void rotate(int[] nums, int k) {
		if (nums.length==0){
			return ;
		}
		//
		k=k%nums.length;
		//1.我的初始做法，效率不高，容易实现 （提交了第一次超时，重新提交后AC）
		// int[]result = new int[nums.length];
		// for(int i =0;i<k;i++){
			// result[i]=nums[nums.length-k+i];
		// }
		// for(int i = k;i<nums.length;i++){
		    // result[i]=nums[i-k];
		// }
		// for(int i=0;i<nums.length;i++){
		    // nums[i]=result[i];
		// }
		//2.改进算法 旋转数组三次
		if(k==0){
			return;
		}
		reverse(nums,0,nums.length-k-1);
		reverse(nums,nums.length-k,nums.length-1);
		reverse(nums,0,nums.length-1);
    }
	public void reverse(int[]nums,int start,int end){
		do{
			int temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}while(start<end);
	}
}