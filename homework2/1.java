public class Solution {
	public static int[] twoSum(int[] nums, int target) {
		int[] numbers = new int[2];
		if (nums == null || nums.length < 2)
			return numbers;
		/*int sum = 0;
		for (int i = 0, j = i + 1; i < nums.length - 1 && j < nums.length; j++) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				numbers[0] = i + 1;
				numbers[1] = j + 1;
				break;
			}
			if (j == nums.length - 1) {
				i++;
				j = i;
			}
			sum = 0;
		}*/
		//Hashmap
		int numbers[] = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(target - nums[i])) {
				map.put(nums[i], i);
			}else {
				numbers[0] = map.get(target - nums[i]) + 1;
				numbers[1] = i+1;
				break;
			}
		}
		return numbers;
	}
}