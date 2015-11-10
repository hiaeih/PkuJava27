/*Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.*/
public class FourSum{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if (nums.length < 4)
			return result;
		Arrays.sort(nums);
		int sum = 0;
		for(int i = 0; i < nums.length - 3; i++){
		    if(i > 0 && nums[i] == nums[i-1]) continue;
		    for(int j = i+1; j < nums.length - 2; j++){
		        int p = j+1;
		        int q = nums.length - 1;
		        while(p < q){
		            sum = nums[i] + nums[j] + nums[p] + nums[q];
		            if(sum == target){
		                list = new ArrayList<Integer>();
		                list.add(nums[i]);
		                list.add(nums[j]);
		                list.add(nums[p]);
		                list.add(nums[q]);
		                if(!result.contains(list))
		                    result.add(list);
		                p++;
		                q--;
		                if(p < q && nums[p] == nums[p-1])
		                    p++;
                        if(p < q && nums[q] == nums[q+1])
		                    q--;
		            }else if(sum > target){
		                q--;
		            }else
		                p++;
		        }
		    }
		}
		return result;
    }
}