// https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/


        // 1 3 2 4 5 4 3 7 8 10
        //   2     3     4   2
        //   2     3         7


public class Solution {
  public int maxProfit(int[] prices) {
    int profit = 0;
    int buyPrice = Integer.MAX_VALUE;
    boolean inMarket = false;

    for (int i = 0; i < prices.length; i++ ) {
      if (buyPrice > prices[i]) {
        buyPrice = prices[i];
        continue;
      }

      if (i == prices.length - 1) {
        if (prices[i] > buyPrice) {
          profit += prices[i] - buyPrice;
        }
        continue;
      }

      if (prices[i] > prices[i+1]) {
        profit += prices[i] - buyPrice;
        buyPrice = prices[i+1];
      }
    }

    return profit;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a1 = {1,3,2, 4, 5, 4, 3, 7, 8, 10};
    System.out.println(s.maxProfit(a1));

    int[] a2 = {1, 1, 2, 3, 3, 4, 3, 2, 1};
    System.out.println(s.maxProfit(a2));

    int[] a3 = {2, 4, 1};
    System.out.println(s.maxProfit(a3));
  }
}
