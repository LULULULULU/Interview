// https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

import java.lang.Math;

public class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) return 0;

    int n = prices.length;
    int[] firstMax = new int[prices.length];
    int[] secondMax = new int[prices.length];
    int tempMax = 0;
    int temp = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      tempMax = Math.max(tempMax, prices[i] - temp);
      temp = Math.min(temp, prices[i]);
      firstMax[i] = tempMax;
    }

    temp = prices[n-1];
    tempMax = 0;
    secondMax[n-1] = 0;
    for (int i = n-1; i >= 0; i--) {
      tempMax = Math.max(tempMax, temp - prices[i]);
      temp = Math.max(temp, prices[i]);
      secondMax[i] = tempMax;
    }

    tempMax = 0;
    //    f     s
    //0   1     2   n-1
    //    2     3
    //   n-3   n-2
    // f[n-1]
    // s[0]
    for (int i = 1; i <= n - 3; i++) {
      tempMax = Math.max(tempMax, firstMax[i] + secondMax[i+1]);
    }
    tempMax = Math.max(tempMax, firstMax[n-1]);
    tempMax = Math.max(tempMax, secondMax[0]);

    return tempMax;
  }

  /*-----*/
  /*-- Test Code ---*/
  /*-----*/
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] array = {1,3,9,4,6,8,1,9,1,11};
    System.out.println(s.maxProfit(array));

    int[] array1 = {3,1};
    System.out.println(s.maxProfit(array1));
  }
}
