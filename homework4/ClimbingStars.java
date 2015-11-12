//Climbing Stairs
//You are climbing a stair case. It takes n steps to reach to the top.

//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
public class Solution {
    public int climbStairs(int n) {
		//1.超时
		// int result=0;
        // if(n==1){
			// result=1;
		// }else if(n==2){
			// result=2;
		// }else {
			// result=climbStairs(n-1)+climbStairs(n-2);
		// } 
		// return result;
		
		//2.空间换时间 动态规划   AC 
		// if(n==1){
			// return 1;
		// }
		// int result[] = new int[n];
		
		// result[0]=1;
		// result[1]=2;
		// for(int i =2;i<n;i++){
			// result[i]=result[i-1]+result[i-2];
		// }
		// return result[n-1];
		//迭代
		if(n==1){
			return 0;
		}
		for()
		//3.
		 if(n==1){
			return 1;
		}else if(n==2){
			return 2;
		}
		int first=1;
		int second=2;		
		for (int i = 3; i <=n; i++){
			int temp=first+second;
			first=second;
			second=temp;
		}	
		return second;

		
		
    }
}