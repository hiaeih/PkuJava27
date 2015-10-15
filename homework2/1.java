//Two Sum
import java.util.Hashtable;
public class Solution {
    public int[] twoSum(int[] nums, int target) {
		//1.brute force 蛮力算法  时间复杂度O(n^2) 耗时太长了
		// int index[]=new int[2];
        // for(int i = 0;i<nums.length;i++){
			// for(int j= i+1;j<nums.length;j++){
				// if (nums[j]==target-nums[i]){
					// index[0]=i+1;
					// index[1]=j+1;
				// }
			// }
		// }
		// return index;
		//2.hash table  时间复杂度O(n)  Solution提示可以用HashTable求解 
		//HashTable中查找元素时间复杂度为O(1)
		Hashtable<Integer,Integer> hash= new Hashtable<Integer,Integer>();
		int[] index= new int [2];
		for(int i =0;i<nums.length;i++){
		    Integer nums1=hash.get(nums[i]);
			if(nums1==null){
				hash.put(nums[i],i);
			}
			//如果数组中有重复的值出现，那么第二次出现时就不会加入到hashtable里了
			if(hash.get(target-nums[i])!=null&&hash.get(target-nums[i])<i){
				index[1]=i+1;
				index[0]=hash.get(target-nums[i])+1;
				break;
			}
		}
		return index;
    }
}