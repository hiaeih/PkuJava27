import java.util.Hashtable;
/*import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;*/
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        //开始未加导致出错
        if(nums.length < 2)
			return false;
		Arrays.sort(nums);
		int i = 0;
		boolean flag = false;
        Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
		//HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(;i < nums.length; i++){
			if(table.containsKey(nums[i])){
				table.put(nums[i],table.get(nums[i]).intValue() + 1);
				//map.put(nums[i],map.get(nums[i]).intValue() + 1);
				//flag = false;--未申清楚题意，只要有重复的就返回真！
				return true;
			}else{
			    table.put(nums[i],1);
				//map.put(nums[i],1);
			}
		}
		/*for(Entry<Integer, Integer> entry:map.entrySet()){  
			if(entry.getValue() == 2){
				flag = true;
			}
		}*/
		return flag;
    }
}