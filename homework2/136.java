//Single Number
public class Solution {
    public int singleNumber(int[] nums) {
     // have a linear runtime complexity
	 //without using extra memory
	 //����ʱ�临�Ӷȣ�ͬʱ�ռ临�Ӷ�ΪO(1)
	 //����ʹ�ò����㷨
	 //ʹ�ð�λ���ķ���
	 int result = 0;
	 for(int i =0;i<nums.length;i++){
		 result=result^nums[i];
	 }
     return result;
    }
}