//Remove Element
public class Solution {
    public int removeElement(int[] nums, int val) {
		//ʹ��һ����count��¼������length-count
		int size = nums.length;
		int count =0;
		// for(int i =0;i<size;i++){
		for(int i=0;i<size-count;i++){
			if (nums[i]==val){
				//���鳤��-1����ǰ����β��������
				int temp=nums[i];
				// nums[i]=nums[size-1];
				nums[i]=nums[size-count-1];
				count++;
				i--;
			}
		}
        return size-count;
    }
}