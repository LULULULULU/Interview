// https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

import java.util.Arrays;

public class SolutionTLE {
  public static int PURCHASE_CHANCE = 2;
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }

    int[] firstMax = new int[prices.length];
    int[] secondMax = new int[prices.length];



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
