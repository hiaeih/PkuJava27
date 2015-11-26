/*
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Array Dynamic Programming.
*/
public class BestTimetoBuyAndSellStock {
    public static int maxProfit(int[] prices) {
		if(prices.length < 1)
			return 0;
		int low = prices[0];
		int best = 0;
		for(int i = 1; i< prices.length; i++){
			if(prices[i] < low)
				low = prices[i];
			if(prices[i] - low > best)
				best = prices[i] - low;
		}
        return best;
    }
}