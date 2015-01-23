// https://oj.leetcode.com/problems/dungeon-game/
//
//       0    1    2    3    4    5
// 0     -1   -1   -1   -1   -1   -1
// 1     -1   -1   -1   -1   -1   -1
// 2     -1   -1   -1   -1   -1   -1
// 3     -20  -1   -1   -1   -1   -1
// 4     100  -1   -1   -1   -1   -1
// 5     -1   -1   -1   -1   -1   -1
// 6     -1   -1   -1   -1   -1   (-50)/(-1)
//
//      (6,5) -                           11
//      (6,4)(5,5) -                      10
//      (6,3)(5,4)(4,5) -                  9
//      (6,2)(5,3)(4,4)(3,5) -             8
//      (6,1)(5,2)(4,3)(3,4)(2,5) -        7
//      (6,0)(5,1)(4,2)(3,3)(2,4)(1,5) -   6
//      (5,0)(4,1)(3,2)(2,3)(1,4)(0,5) -   5
//      (4,0)(3,1)(2,2)(1,3)(0,4) -        4
//      (3,0)(2,1)(1,2)(0,3) -             3
//      (2,0)(1,1)(0,2) -                  2
//      (1,0)(0,1) -                       1
//      (0,0) -                            0
//
//      This could be 23 if target is -50
//      or 12 if target is -1

import java.util.Arrays;

public class Solution {
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon.length < 0 || dungeon[0].length < 0) {
      return 1;
    }
    int m = dungeon.length;
    int n = dungeon[0].length;
    int[][] map = new int[m][n];

    for (int i = 0; i < m; i++) Arrays.fill(map[i], Integer.MAX_VALUE);
    map[m-1][n-1] = dungeon[m-1][n-1] >= 0 ? 0 : -dungeon[m-1][n-1];

    int i, j, temp;
    for(int v = m+n-2 - 1; v >= 0; v--) {
      i = v<=(m-1) ? v : (m-1);
      j = v - i;

      System.out.println("Started one round: (v i j) = (" + v + " " + i + " " + j + ")");

      while(j < n && j <= v) {

        System.out.println(i + " " + j);
        // for current item map[i][j]
        // if it has right item
        if (j + 1 < n) {
          map[i][j] = map[i][j+1] - dungeon[i][j] > 0 ? map[i][j+1] - dungeon[i][j] : 0;
        }

        // if it has down item
        if (i + 1 < m) {
          temp = map[i+1][j] - dungeon[i][j] > 0 ? map[i+1][j] - dungeon[i][j] : 0;
          map[i][j] = map[i][j] < temp ? map[i][j] : temp;
        }

        --i;
        ++j;
      }
    }

    // Add one to make sure hero is alive
    return map[0][0] + 1;
  }

  public static void main(String[] args) {

    int[] arrays0 = {-1 ,-1,-1,-1,-1,-1};
    int[] arrays1 = {-1 ,-1,-1,-1,-1,-1};
    int[] arrays2 = {-1 ,-1,-1,-1,-1,-1};
    int[] arrays3 = {-20,-1,-1,-1,-1,-1};
    int[] arrays4 = {100,-1,-1,-1,-1,-1};
    int[] arrays5 = {-1 ,-1,-1,-1,-1,-1};
    int[] arrays6 = {-1 ,-1,-1,-1,-1,-50};

    int[] a = {0};

    // int[][] arrays = {arrays0, arrays1, arrays2, arrays3, arrays4, arrays5, arrays6};
    int[][] arrays = {a};
    Solution s = new Solution();
    System.out.println(s.calculateMinimumHP(arrays));
  }
}
