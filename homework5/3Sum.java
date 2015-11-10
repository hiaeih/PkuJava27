/*Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.*/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length < 3)
            return result;
        Arrays.sort(nums);
        
        //暴力破解--超时
        /*for(int i = 0; i < nums.length - 2; i++){
            for(int j = i+1; j < nums.length - 1; j++){
                for(int k = j+1; k < nums.length; k++){
                    list = new ArrayList<Integer>();
                    int sum = nums[i] + nums[j] + nums[k];
                    if(sum == 0){
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if(!result.contains(list))
                            result.add(list);
                    }
                }
            }
        }*/
        //三指针
        for(int i = 0; i < nums.length - 2; i++){
            //去除重复计算问题
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int p = i+1;
            int q = nums.length - 1;
            while(p < q){  //前往别忘记了！！！！
                int sum = nums[i] + nums[p] + nums[q];
                if(sum == 0){
                    list = new ArrayList<Integer>();
                    list.add(nums[i]);
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
                }else if(sum > 0)
                        q--;
                      else
                        p++;
            }
        }
        return result;
    }
}