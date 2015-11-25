public class Solution {
    //用2重循环造成Time Limit Exceeded 
   /* public boolean containsDuplicate(int[] nums) {
       for(int i=0;i<nums.length-1;i++){
           for(int j=i+1;j<nums.length;j++){
               if(nums[i]==nums[j]) return true;
           }
       }
       return false;
    }*/
    //用map来做，从数组头开始，如果map里包含这个整数，则返回true；如果不包含，则添加进map
     public boolean containsDuplicate(int[] nums) {
         Set set=new HashSet();
         for(int x:nums){
             if(set.contains(x)) return true;
             else set.add(x);
         }
         return false;
     }
}