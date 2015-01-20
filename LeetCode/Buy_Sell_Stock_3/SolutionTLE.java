// https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
// Time limit exceeded
//
// Could potentially be solution for 3 purchases
//
  //   0  1  2  3  4  5
  // 0 0  2
  // 1 -  0  3
  // 2 -  -  0  0
  // 3 -  -  -  0  1
  // 4 -  -  -  -  0  3
  // 5 -  -  -  -  -  0

import java.util.Arrays;

public class SolutionTLE {
  public static int PURCHASE_CHANCE = 2;
  public int maxProfit(int[] prices) {

    if (prices.length <= 1) {
      return 0;
    }

    // map[i][j] stands for the maxProfit from item i to item j
    // map(0,0) (1,1) (n,n) = 0
    // map(i,j) i MUST BE SMALLER THAN OR EQUAL TO j
    // map(i,j) = max{ map(i,k)+map(k+1,j) }^k=i~(j-1)
    // map(0,n) is target
    int[][] map = new int[prices.length][prices.length];
    int[][] count = new int[prices.length][prices.length];
    int i, k;

    // Set all 0
    for (i = 0; i < prices.length; i++) {
      Arrays.fill(map[i], 0);
      Arrays.fill(count[i],0);
    }

    // Set (0,1) (1,2) ... (n-1,n) diagonal
    for (i = 0; i < prices.length - 1; i++) {
      if (prices[i+1] > prices[i]) {
        map[i][i+1] = prices[i+1] - prices[i];
        count[i][i+1] = 1;
      }
    }

    // Set (0,2) (1,3) ... (n-2,n) diagonal
    for (i = 0; i < prices.length - 2; i++) {
      map[i][i+2] = map[i][i+1] + map[i+1][i+2];
      count[i][i+2] = map[i][i+2]>0 ? 1 : 0;
    }

    // Get all value from each diagonal
    // (0,3) (1,4) ... (n-3,n)
    // (0,4) (1,5) ... (n-4,n)
    // (0,n)
    for (k = 3; k < prices.length; k++) {
      for (i = 0; i+k < prices.length; i++ ) {
        map[i][i+k] = maxForTuple(i, i+k,map, count);
      }
    }

    return map[0][prices.length-1];
  }


  // map(i,j) = max{ map(i,k)+map(k+1,j) }^k=i~(j-1)
  private int maxForTuple(int i, int j, int[][] map, int[][] count) {
    int max = 0;
    int countMax = 0;
    for (int k = i; k < j; k++) {
      if ((count[i][k] + count[k+1][j]) <= PURCHASE_CHANCE &&
        (map[i][k]+map[k+1][j]) > max) {
        max = map[i][k] + map[k+1][j];
        countMax = count[i][k] + count[k+1][j];
      }

      if (map[i][k] >= max && countMax <= count[i][k]) {
        max = map[i][k];
        countMax = count[i][k];
      }

      if (map[k+1][j] >= max && countMax <= count[k+1][j]) {
        max = map[k+1][j];
        countMax = count[k+1][j];
      }
    }

    count[i][j] = countMax;
    return max;
  }

  /*-----*/
  /*-- Test Code ---*/
  /*-----*/
  public static void main(String[] args) {
    SolutionTLE s = new SolutionTLE();
    int[] array = {1,3,9,4,6,8,1,9,1,11};
    System.out.println(s.maxProfit(array));

    int[] array1 = {3,1};
    System.out.println(s.maxProfit(array1));
  }

  public static void printMap(int[][] map) {
    int n = map.length;
    System.out.println("----------------------");
    for (int i = 0; i<n ;i++) {
      for (int j = 0; j<map[i].length; j++) {
        if (i <= j) System.out.print(map[i][j] + "   ");
        else System.out.print( "-   ");
      }
      System.out.println();
    }
    System.out.println("----------------------");
  }
}
