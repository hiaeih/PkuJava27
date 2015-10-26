import java.util.*;
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //方法1：超时！！
        // int[] index = new int[2];
        // int[] nums2 = new int[nums.length];
        // int flag1 = -1;
        // int flag2 = -1;
        // System.arraycopy(nums,0,nums2,0,nums.length);//原数组备份
        
        //Arrays.sort(nums);//nums = Arrays.sort(nums);  索引值会改变！！！
        //target<nums[0] || target>nums[length-1]
        
        //头尾指针所指数相加，若小于target，则头指针前进；反之尾指针前进
    //     for(int i=0;i<nums.length;i++){
    //         for(int j=nums.length-1;j>i;j--){
    //             System.out.println("I'm in double for!!!");
    //             if((nums[i]+nums[j])>target){//j--(i不变！！)
    //                 continue;//j--;
    //             }
    //             if((nums[i]+nums[j])<target){//i++(j不变！！)
    //                 i++;j--;continue;//i++
    //             }
    //             if((nums[i]+nums[j])==target){
    //                 System.out.println("i="+i+" j="+j+" nums[i]="+nums[i]+" nums[j]="+nums[j]);
    //                 flag1=nums[i];
    //                 flag2=nums[j];
    //                 break;//只能跳出一层循环
    //                 //return index;
    //             }
    //         }
    //         if(flag1!=-1){
    //             break;
    //         }
    //     }
    //     // for(int i=0,j=nums.length-1;i<nums.length,j>i;i++,j--){
    //     //     if((nums[i]+nums[j])>target)
    //     // }
        
    //     //查找原索引
    //     for(int i=0;i<nums2.length;i++){
    //         if(nums2[i]==flag1){
    //             index[0]=i+1;
    //         }
    //         if(nums2[i]==flag2){
    //             index[1]=i+1;
    //             return index;
    //         }
    //     }
    //     return index;
        
    // }超时！！！

    /**
     * 方法二：HashMap
     * key-->number[i],value-->i
     * value map.get(key)
     * 如果HashMap中包含target-number[i]，通过map.get(key)获得其编号（map->value），此时i即为另一编号（大号）
     * 否则将number[i]--i+1放入map中
     */ 
     int[] index = new int[2];
     Map<Integer,Integer> map = new HashMap<Integer,Integer>();
     
     for(int i=0;i<nums.length;i++){
         if(map.containsKey(target-nums[i])){
             index[0] = map.get(target-nums[i]) + 1;
             index[1] = i + 1;
             break;
         }else{
             map.put(nums[i],i);
         }
     }
     return index;
    }
}