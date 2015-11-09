public class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);//升序排序
        List<List<Integer>> setList = new ArrayList<List<Integer>>();
        HashSet<List<Integer>> hashSet = new HashSet<List<Integer>>();//控制不存在重复的
         if(nums.length<4){
             return setList;
         }
        
         for(int i=0;i<nums.length-3;i++){
            //  if(i==1 && nums[i]==nums[i-1]){//相同元素跳过
            //     System.out.println("i-1=i="+i);
            //     continue;
            // }
            //int tar = target-nums[i];
            //System.out.println("i="+i);
            for(int t=i+1;t<nums.length-2;t++) {
                //System.out.println("i="+i+" t="+t);
                // if(t==2 && nums[t]==nums[t-1]){//相同元素跳过 t>1
                //     continue;
                // }
                int tar = target-nums[i]-nums[t];
                int j = t+1;
                int k=nums.length-1;
                while(j<k) {
                    //System.out.println("t="+t+" j="+j+" k="+k);
                    //System.out.println("I'm in while!!");
                    if(nums[j]+nums[k]==tar){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[t]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        //setList.add(list);
                        //continue;
                        //break;
                        //return setList;
                        if (!hashSet.contains(list)) {
						    setList.add(list);
						    hashSet.add(list);
					    }
                        
                        k--;
                        j++;
                    }else if(nums[j]+nums[k]>tar) {
                        k--;
                    }else{// if(nums[j]+nums[k]<tar)
                        j++;
                    }
                }
            }
         }
         return setList;
        
    }
}