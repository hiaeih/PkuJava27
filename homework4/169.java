import java.util.Arrays;
public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        //int i = 0;
        // if(nums[0]==nums[nums.length-1])
        //     return nums[0];
            
        // while(i<nums.length/2) {
        //     System.out.println("nums[i]="+nums[i]);
        //     if(nums[i]==nums[i+(nums.length/2-1)]){
        //         break;//return nums[i];
        //     }else {
        //         i++;
        //     }
        // }
        // return nums[i];
        //return nums[]
        
        int mid = nums.length/2;
        //if(nums[mid]==nums[0] || nums[mid]==nums[nums.length-1]){
            return nums[mid];
        //}
    }
}