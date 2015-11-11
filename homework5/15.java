
//用下面的三重循环的暴力方法来解决，总是报Time Limit Exceeded错误，而且没有想出去掉重复结果的方法，故需要考虑其他方法。
/*public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>(); 
        int i,j,k;
        for(i=0;i<nums.length-2;i++){
            for(j=i+1;j<nums.length-1;j++){
                for(k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> list=new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if(res.contains(list)) continue;
                        else  res.add(list);
                    }
                }
            }
        }
        return res;
    }
}*/

public class Solution {
    private List<List<Integer>> res; 
    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<List<Integer>>(); 
        //先对整数数组排序
        Arrays.sort(nums);
        //求出和为0的三个整数的所有组合
        for(int i=0;i<=nums.length-3;i++){
            //从固定值的角度去排除掉重复的组合
            if((i!=0)&&(nums[i]==nums[i-1])){
                continue;
            }
            else{
             sortList(nums,nums[i],i + 1,nums.length-1);
            }
    }
    return res;
    }
    //先固定一个数，再求出和为0的其他两个整数
    public void sortList(int[] nums,int target,int begin,int end){
         int p = begin, q = end;  
         //这里if..else一定要注意对应书写正确，否则会导致wrong answer
            while(p<q){
                if(target+nums[p]+nums[q]>0){
                    q--;
                }
                else if(target+nums[p]+nums[q]<0){
                    p++;
                }
                else if(target+nums[p]+nums[q]==0){
                   List<Integer> list=new ArrayList<Integer>();
                   list.add(target);
                   list.add(nums[p]);
                   list.add(nums[q]);
                   res.add(list);
                   p++;
                   q--;
                   //去掉可能重复的组合
                while((p<q)&&(nums[p]==nums[p-1])){
                    p++;
                }
                while((p<q)&&(nums[q]==nums[q+1])){
                    q--;
                }
                 }
        
            }
        }
}