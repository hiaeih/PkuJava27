public class Solution {
    public int removeDuplicates(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int f=1;
        if(nums.length<=1){
            return nums.length;
        }
        
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(nums[i])){
                continue;
            }else {
                map.put(nums[i],i);
                if(i!=0){//处理数组
                    nums[f]=nums[i];
                    f++;
                }
            }
        }
        //处理数组
        //int count=0;
        // int first=1;
        // for(Integer i:map.keySet()){
        //   // System.out.println(map.get(i)+"  i="+i);
        //     // if(count==0){
        //     //     first = map.get(i);
        //     //     count++;
        //     //     continue;
        //     // }
        //   // System.out.println(map.get(i-1)+"  i-1="+(i-1));
        //     //if((map.get(i)!=(map.get(i-1)+1))&& i>1){
        //         //System.out.println( "map.get(i)="+map.get(i)+"  map.get(i-1)="+map.get(i-1));
        //         //first++;
        //         nums[first]=nums[map.get(i)];
        //         first++;
        //     //}
        //     //count++;
        // }

        
        return map.size();
    }
}