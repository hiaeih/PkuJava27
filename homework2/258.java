//Add Digits
//Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

//Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
public class Solution {
    public int addDigits(int num) {
		//1.蛮力算法
        //取整
		//数字的位数
		// while(num>=10){
			// num=addOnce(num);
		// }
		// return num;
    // }
		// public int addOnce(int num){
		// ArrayList list =new ArrayList<Integer>();
		
		// while(num>=10){
		// int temp=(int)Math.floor(num/10);
		// list.add(num-temp*10);
		// num=temp;
		// }
		// list.add(num);
		// int sum=0;
		// for(int i=0;i<list.size();i++){
			// int j=(Integer) list.get(i);
			// sum+=j;
		// }
		// return sum;
		//2.找规律 时间复杂度O(1)
		//公式法求数根： a的数根b = ( a - 1) % 9 + 1
		return (num-1)%9+1;
	}
}
