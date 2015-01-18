// https://oj.leetcode.com/problems/unique-paths/
// int有可能overflow

public class Solution {
  public int uniquePaths(int m, int n) {

    if (m <= 1 || n <= 1) {
      return 1;
    }

    int[][] mem = new int[m][n];
    int i,j;

    for(i = 0; i < m; i++) {
      mem[i][0] = 1;
    }

    for(i = 0; i < n; i++) {
      mem[0][i] = 1;
    }

    for (i = 1; i < m; i++) {
      for (j = 1; j < n; j++ ) {
        mem[i][j] = mem[i][j-1] + mem[i-1][j];
      }
    }

    return mem[m-1][n-1];
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.uniquePaths(20,20));
  }
}
