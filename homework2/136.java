import java.util.*;
public class Solution {
    public int singleNumber(int[] nums) {
        //利用HashSet只能存储不重复对象的特征
    	HashSet<Integer> set = new HashSet<Integer>();
    	for (int n : nums) {
    		if (!set.add(n))
    			set.remove(n);
    	}
    	Iterator<Integer> it = set.iterator();
    	return it.next();
    }
}