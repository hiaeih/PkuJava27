package week2;

public class TwentySeven {
	public static int removeElement(int[] nums, int val) {
		int count = 0, flag = 0;
		int len = nums.length;
		int i = 0, j = 0;
		while (len - flag > 0) {
			j = i;
			if (nums[j] == val) {
				count++;
				for (; j < len - count; j++)
					nums[j] = nums[j + 1];
			} else {
				i++;
			}
			flag++;
		}
		return len - count;
	}
}
