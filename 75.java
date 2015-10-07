//Sort Colors
public class Solution {
    public void sortColors(int[] nums) {
		//1.利用Arrays的sort方法 ，
		//又看到了 You are not suppose to use the library's sort function for this problem.
        // Arrays.sort(nums);
		//2.计数排序 两遍扫描
		//但是题目要求an one-pass algorithm using only constant space 扫描一遍
		// int num0=0;
		// int num1=0;
		// int num2=0;
		// for(int i =0;i<nums.length;i++){
			// switch(nums[i]){
				// case 0: num0=num0+1;break;
				// case 1: num1=num1+1;break;
				// case 2: num2=num2+1;break;
			// }
		// }
		// for(int i=0;i<nums.length;i++){
			// if(i>=0&&i<num0){
				// nums[i]=0;
			// }else if(i>=num0&&i<num0+num1){
				// nums[i]=1;
			// }else if(i>=num0+num1){
				// nums[i]=2;
			// }
		// }
		//3.只有三种颜色  设置两个index 一个代表红色 一个代表蓝色
	    int red =0;
		int blue=nums.length-1;
		int i = 0;
        while(i <= blue){
            if(nums[i] == 0){
                swap(nums, red, i);
                red++;
                i++;
            }else if(nums[i] == 1){
                i++;
            }else{
                swap(nums, blue, i);
                blue--;
            }
		}
    }
	private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}