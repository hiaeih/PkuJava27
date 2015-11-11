//鉴于3Sum一题的解题思路，4Sum一题可采用同样的方法，只不过多加一层循环，从而固定前两个数字。
public class Solution {
    List<List<Integer>> res=new ArrayList<List<Integer>>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i,j,p,q;
        for(i=0;i<=nums.length-4;i++)
        {
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(j=i+1;j<=nums.length-3;j++){
                if(j>i+1&&nums[j]==nums[j-1]){//if里面的判定条件，一定要写正确。此次做的时候写错了，导致花费很多时间去调试
                continue;
            }
                p=j+1;q=nums.length-1;
                sortNum(nums,target,i,j,p,q);
            }
        }
        return res;
        
    }
    public void sortNum(int[] nums,int target,int i,int j,int p,int q){
        while(p<q){
            if(nums[i]+nums[j]+nums[p]+nums[q]<target){
                p++;
            }
            else if(nums[i]+nums[j]+nums[p]+nums[q]>target){
                q--;
            }
            else if(nums[i]+nums[j]+nums[p]+nums[q]==target){
                List<Integer> list=new ArrayList<Integer>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[p]);
                list.add(nums[q]);
                res.add(list);
                p++;
                q--;
                while(p<q&&nums[p]==nums[p-1]){
                    p++;
                }
                while(p<q&&nums[q]==nums[q+1]){
                    q--;
                }
            }
        }
    }
}