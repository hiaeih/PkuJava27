public class Solution {
    //��2��ѭ�����Time Limit Exceeded 
   /* public boolean containsDuplicate(int[] nums) {
       for(int i=0;i<nums.length-1;i++){
           for(int j=i+1;j<nums.length;j++){
               if(nums[i]==nums[j]) return true;
           }
       }
       return false;
    }*/
    //��map������������ͷ��ʼ�����map���������������򷵻�true�����������������ӽ�map
     public boolean containsDuplicate(int[] nums) {
         Set set=new HashSet();
         for(int x:nums){
             if(set.contains(x)) return true;
             else set.add(x);
         }
         return false;
     }
}