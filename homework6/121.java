public class Solution {
    public int maxProfit(int[] prices) {
        //方法一：超时！！
        // int profit=0;
        // for(int i=0;i<prices.length;i++) {//i->buy
        //     for(int j=i+1;j<prices.length;j++) {//j->sell
        //         profit=(prices[j]-prices[i]>profit)?(prices[j]-prices[i]):profit;
        //     }
        // }
        // return profit;

        if(prices.length<2){
            return 0;
        }
        int buy=prices[0];//buy=0;
        int profit=0;
        
        // if(prices.length<2){
        //     return 0;
        // }
        
        for(int i=0;i<prices.length;i++){
            buy=Math.min(buy,prices[i]);
            profit=Math.max(profit,prices[i]-buy);
        }
        return profit;
    }
}