//Best Time to Buy and Sell Stock

//Say you have an array for which the ith element is the price of a given stock on day i.
//If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
public class Solution {
    public int maxProfit(int[] prices) {
        //1.设置一个数组存储当天买的股票的最大收益
		//超时
		
		// int max[] = new int [prices.length];
		// for(int i = 0 ; i < prices.length ; i++){
			// max[i]=0;
			// for(int j = i+1;j<prices.length ; j++){
				// if ((prices[j]-prices[i])>max[i])
					// max[i]=prices[j]-prices[i];
			// }
		// }
		// int result=0;
		// for(int i =0 ;i< prices.length;i++){
			// result=max[i]>result?max[i]:result;
		// }
		// return result;
		//2.动态规划
		//从前向后遍历数组，记录当前出现过的最低价格作为买入价格，然后计算以当天价格出售的收益
		//遍历结束就得到了股票的最大收益
		//错误，忘记考虑数组长度小于2
		if(prices.length<2)
			return 0 ;
		int result=0;
		int buy=prices[0];
		for(int i =0;i<prices.length;i++){
			buy=Math.min(buy,prices[i]);
			if(prices[i]-buy>result){
				result=prices[i]-buy;
			}
		}
		return result;
    }
}