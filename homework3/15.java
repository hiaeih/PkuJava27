public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        Arrays.sort(nums);
//        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> setList = new ArrayList<List<Integer>>();
        //超时简化
        if(nums.length<3){
            return setList;
        }
        if(nums[0]>0 || nums[nums.length-1]<0){
            return setList;
        }
        // if(nums.length==3 && nums[0]==nums[1]){
        //     return setList;
        // }

        for(int i=0;i<nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1]){//相同元素跳过
                continue;
            }
            int target = 0-nums[i];
            int j=i+1;
            int k=nums.length-1;
            // if(nums[i]==0 && nums[j]==0){//超时简化
            //     return setList;
            // }
            while(j<k){
                //System.out.println("j="+j);
                if(nums[j]+nums[k]==target){
                    //System.out.println("j="+j+" k="+k);
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    setList.add(list);
                    j++;//第一次超时主要原因
                    k--;
                    if(j==k){
                        break;
                    }
                    while(j<k && nums[j]==nums[j-1]) j++;
                    while(k>j && nums[k]==nums[k+1]) k--;
                //     }else{
                //         while(nums[j]==nums[j-1] && j<k && j<nums.length-1){//相同元素跳过
                //             j++;
                //         }
                //         while(nums[k]==nums[k+1] && k>j && k>2){//相同元素跳过
                //             k--;
                //         }
                //     }
                //     //return list;
                //     //break;
                // }
            }else if(nums[j]+nums[k]>target){
                    k--;
                }else{
                    j++;
                } 
        }
            
        }
        return setList;

    }
}