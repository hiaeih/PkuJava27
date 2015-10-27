import java.util.*;
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if (nums.length < 3)
			return result;
		Arrays.sort(nums);
		// 暴力破解
		/*for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					list = new ArrayList<Integer>();
					if (nums[i] + nums[j] + nums[k] == 0) {
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[k]);
						if (!result.contains(list))
							result.add(list);
					}
				}
			}
		}*/
		int p = 0, q = nums.length-1, sum = 0;
		for(int i = 0; i < nums.length; i++)
        {
            //去除重复计算问题
            if (i > 0 && nums[i] == nums[i-1]) continue;
            p = i+1;q = nums.length-1;
            //产生了p<q的情况，然而继续编译了[-2,0,1,1,2]p=3，q=2停止
            //while(p == q)
            while(p < q)
            {
                sum = nums[i] + nums[p] + nums[q];
                if(sum == 0){
                    list = new ArrayList<Integer>();
                    list.add(nums[i]);
        			list.add(nums[p]);
        			list.add(nums[q]);
        			if (!result.contains(list))
        				result.add(list);
        			p++;
        			q--;
        			//去重
                    while (p < q && nums[p] == nums[p-1]) p++;  
                    while (p < q && nums[q] == nums[q+1]) q--; 
                }else if(sum > 0)
                        q--;
                      else
                        p++;
                 
            }
        }
		return result;
	}
}