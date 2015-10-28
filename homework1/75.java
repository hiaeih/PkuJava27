public class Solution {
    public void sortColors(int[] nums) {
        //Arrays.sort(nums);
        //two pointer
        int pre = 0;
        int red = 0;
        int blue = nums.length - 1;
        while(pre <= blue){
            if(nums[pre] == 0){
                swap(nums,red,pre);
                red++;
                pre++;
            }else if(nums[pre] == 1)
                pre++;
                else{
                    swap(nums,pre,blue);
                    blue--;
                }
        }
    }
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
