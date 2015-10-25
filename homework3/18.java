//4Sum
//Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
//1.4sum  ×ª»»Îª3sum
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
		for(int i = 0;i<nums.length;i++){
			for(int j = i+1;j<nums.length;j++){
				int sum= target-nums[i]-nums[j];
				int start = j+1;
				int end = nums.length-1;
				while(start<end){
					if(nums[start]+nums[end]==sum){
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[start]);
						list.add(nums[end]);
						if(result.contains(list)==false){
							result.add(list);
						}
						start++;
						end--;
					}else if(nums[start]+nums[end]<sum){
						start++;
					}else{
						end--;
					}
				}
			}
		}
		return result;
    }
}