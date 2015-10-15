//Two Sum
import java.util.Hashtable;
public class Solution {
    public int[] twoSum(int[] nums, int target) {
		//1.brute force �����㷨  ʱ�临�Ӷ�O(n^2) ��ʱ̫����
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
		//2.hash table  ʱ�临�Ӷ�O(n)  Solution��ʾ������HashTable��� 
		//HashTable�в���Ԫ��ʱ�临�Ӷ�ΪO(1)
		Hashtable<Integer,Integer> hash= new Hashtable<Integer,Integer>();
		int[] index= new int [2];
		for(int i =0;i<nums.length;i++){
		    Integer nums1=hash.get(nums[i]);
			if(nums1==null){
				hash.put(nums[i],i);
			}
			//������������ظ���ֵ���֣���ô�ڶ��γ���ʱ�Ͳ�����뵽hashtable����
			if(hash.get(target-nums[i])!=null&&hash.get(target-nums[i])<i){
				index[1]=i+1;
				index[0]=hash.get(target-nums[i])+1;
				break;
			}
		}
		return index;
    }
}