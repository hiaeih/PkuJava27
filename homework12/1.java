public class Solution {
    //��ֱ�ӵķ���
    /*public int[] twoSum(int[] nums, int target) {
        int[] index=new int[2];
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    index[0]=i+1;
                    index[1]=j+1;
                }
            }
        }
        return index;
    }*/
    //�Ľ��ķ���:hashmap��keyҪ��Ψһ,value���ܴ�����
    public int[] twoSum(int[] numbers, int target) {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	int[] result = new int[2];
	for (int i = 0; i < numbers.length; i++) {
	    map.put(numbers[i],i);
	}
	for (int i = 0; i < numbers.length; i++) {
	//Integer key=map.get(numbers[i]);
	//if(key==null) map.put(numbers[i],i);
	Integer key1=map.get(target-numbers[i]);
	if(key1!=null){
	    //����һ����i������ֵ���бȽϣ���Ϊ���������ͬ��key����Ҫ����ǰ�ߵ�key
	    if(i<key1){
	        result[0]=i+1;
	        result[1]=key1+1;
	        return result;
	    }
	    if(i>key1){
	        result[0]=key1+1;
	        result[1]=i+1;
	        return result;
	    }
	}
	}
 
	return null;
    }
}