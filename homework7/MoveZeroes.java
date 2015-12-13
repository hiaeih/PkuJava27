/* Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/
//不为零的前移，之后从不为零个数位置开始赋0
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int cur = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0)
                nums[cur++] = nums[i];
        }
        for(;cur < nums.length; cur++)
            nums[cur] = 0;
    }
}