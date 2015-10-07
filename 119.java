// Pascal's Triangle II
public class Solution {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> nums = new ArrayList<Integer>();
		for(int i = 0; i<=rowIndex; i++){
			nums.add(0, 1);
			for(int j = 1; j<i; j++){
				nums.set(j,nums.get(j+1)+nums.get(j));
			}
		}
		return nums;
	}
}