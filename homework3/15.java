//3Sum
//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //1.蛮力 三重循环 超时
		// List<Integer> list = new ArrayList<Integer>();
		// List<List<Integer>> result = new ArrayList<List<Integer>>();
		// for(int i=0;i<nums.length;i++){
			// for(int j =i+1;j<nums.length;j++){
				// for(int k = j+1;k<nums.length;k++){
					// if(nums[i]+nums[j]+nums[k]==1){
						// List<Integer> list = new ArrayList<Integer>();
						// list.add(nums[i]);
						// list.add(nums[j]);
						// list.add(nums[k]);
						// Collection.sort(list);
						// 忘记判断重复
						// if(result.contains(list)==false){
							// result.add(list);
						// }
						
					// }
				// }
			// }
		// }
		// return result; 
		//2.3Sum转化为two sum 两重循环  依旧超时= =。
		 // List<Integer> list = new ArrayList<Integer>();
		 // List<List<Integer>> result = new ArrayList<List<Integer>>();
		 // for(int i =0;i<nums.length;i++){
			 // ArrayList<int[]> indexList = new ArrayList<int[]>();
			 // indexList=TwoSum(nums,i);
			 // for(int j = 0; j<indexList.size(); j++){
				 // int []index=indexList.get(j);
				 // if(index[0]!=0&&index[1]!=0){
					 // List<Integer> list = new ArrayList<Integer>();
					 // list.add(nums[i]);
					 // list.add(nums[index[0]]);
					 // list.add(nums[index[1]]);
					 // Collections.sort(list);
					 // if(result.contains(list)==false){
					 // result.add(list);
					 // }
			    // }	 
			 // }
		 // }
		 // return result;
    // }
	// 开始函数只能返回一组索引，修改
	// private  ArrayList<int[]> TwoSum(int[] nums,int positon){	
		// ArrayList<int[]> list= new ArrayList<int[]>();
		// HashMap map = new HashMap();
		// for(int i = positon+1 ;i<nums.length;i++){
			// int[] index=new int[2];
			// if(map.containsKey(nums[i])==false){
				// map.put(nums[i],i);	
			// }
			// if(map.containsKey(0-nums[positon]-nums[i])==true&&map.get(0-nums[positon]-nums[i])!=i){
				// index[0]=i;
				// index[1]=map.get(0-nums[positon]-nums[i]);
			// }
			// list.add(index);
		// }
		// return list;
		
	// }
	//3.先排序 固定一个值设置两个指针查找
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		for(int i =0;i<nums.length;i++){
			int start=i+1;
			int end = nums.length-1;
			int sum = 0-nums[i];
				while(start<end){
			    if(nums[start]+nums[end]==sum){
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[start]);
					list.add(nums[end]);
					if(result.contains(list)==false){
						result.add(list);
					}
					start++;
					end--;
				}else if(nums[start]+nums[end]<sum){
					start++;
				}else if(nums[start]+nums[end]>sum){
					end--;
				}
				
			}
	
		}
		return result;
	}
}
