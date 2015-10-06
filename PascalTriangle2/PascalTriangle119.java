import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> list2 = new ArrayList<Integer>();
		if (numRows <= 0) {
			return list;
		}
		list2.add(1);
		list.add(list2);
		for (int i = 2; i <= numRows; i++) {
			//如果不加临时存储变量，会导致list和list2内容一致，输出结果始终一样而不是新增
			//如输入的numRows为2，结果为[[1, 1], [1, 1]]，陷入覆盖，因此引入了临时集合
			//同时考虑到numRows为0时直接返回空集合，1和2时可直接计算不需要与前一项关联，所以单独提出
			ArrayList<Integer> cur = new ArrayList<Integer>();
			cur.add(1);
			for (int j = 0; j < list2.size() - 1; j++) {
				cur.add(list2.get(j) + list2.get(j + 1));
			}
			//仍不是很明白为什么注释掉的这段代码无法运行出正确的结果
			/*for (int j = i; j >= 0; j--) {
				if (i == j)
					list2.add(1);
				else if (j != 0) {
					list2.set(j, list2.get(j) + list2.get(j - 1));
				}
			}*/
			//list.add(list2);
			cur.add(1);//最后一个一定为1
			list.add(cur);
			list2 = cur;
		}
		return list;
	}
}