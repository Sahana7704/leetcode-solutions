class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for(int i = 1; i < n; i++) {
            if(prices[i] > prices[i - 1]) {
                int priceDiff = prices[i] - prices[i - 1];
                maxProfit += priceDiff;
            }
        }

        return maxProfit;
    }
}