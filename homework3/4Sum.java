import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)*/
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if (nums.length < 4)
			return result;
		Arrays.sort(nums);
		//可以将问题转化为3Sum，只是三个数的和不是0，而是target-nums[i]
		int p = 0, q = nums.length-1, sum = 0;
		for(int i = 0; i < nums.length - 3; i++)
        {
            int temp = nums[i];
            //去除重复计算问题
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1;j < nums.length -2; j++)
            {
                //if (j > 1 && nums[j] == nums[j-1]) continue;
                //应该和自己当前的比，如果j>1，在[-4,-1,-1，0,1,2]中，则出现i=1时j=2，大于1，但是-1-1组合还没进行判断，因此缺少了判断的机会，所以要进行j>i+1的判断
                if (j > i + 1 && nums[j] == nums[j-1]) continue;
                p = j+1;q = nums.length-1;
                //下面又转变为3Sum了
                //产生了p<q的情况，然而继续编译了[-2,0,1,1,2]p=3，q=2停止
                //while(p == q)
                while(p < q)
                {
                    sum = temp + nums[j] + nums[p] + nums[q];
                    if(sum == target){
                        list = new ArrayList<Integer>();
                        list.add(temp);
                        list.add(nums[j]);
            			list.add(nums[p]);
            			list.add(nums[q]);
            			if (!result.contains(list))
            				result.add(list);
            			p++;
            			q--;
            			//去重
                        while (p < q && nums[p] == nums[p-1]) p++;  
                        while (p < q && nums[q] == nums[q+1]) q--; 
                    }else if(sum > target)
                            q--;
                          else
                            p++;
                }
            }
        }
		return result;
    }
}