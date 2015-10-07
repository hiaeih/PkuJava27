//Pascal's Triangle
public class Solution {
    public List<List<Integer>> generate(int numRows) {
		// 2.循环相加上一层arraylist的相邻元素得到新一层arraylist
		List<Integer> init= new ArrayList<Integer>();
		// init.add(0);
		init.add(1);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// 1.刚开始忘记考虑numRow==0的情况,错误
		if(numRows==0) {
			return result;
		}
		result.add(init);
		List<Integer> temp =  new ArrayList<Integer>(); 
        for(int i =0;i<numRows-1;i++){
			temp = result.get(i);
			List<Integer> values =  new ArrayList<Integer>();
			// values.add(0);
			   values.add(1);
			// for(int j=0;j<i+1;j++){				 
				// values.add(temp.get(j)+temp.get(j+1));
			// }
			 for(int j=0;j<i;j++){				 
				values.add(temp.get(j)+temp.get(j+1));
			}
			//列表尾部添加一个1 
			values.add(1);
			result.add(values);
		}
		//移除（用于计算下一行数的）每一行数组第一个元素0， 多循环了一次所以进行了优化从计算时只有尾部添加1，变成列表的首尾都添加1；
        // for(int i =0;i<numRows;i++){
        	// result.get(i).remove(0);
        // }
		return result;
    }
}