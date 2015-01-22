// https://oj.leetcode.com/problems/dungeon-game/

public class Solution {
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon.length < 0 || dungeon[0].length < 0) {
      return 0;
    }
    int m = dungeon.length;
    int n = dungeon[0].length;
    Grid[][] map = new Grid[m][n];
    map[0][0] = new Grid();

    if (dungeon[0][0] >= 0) {
      map[0][0].minHP = 0;
      map[0][0].currentHPWithMinHPb = dungeon[0][0];
    }
    else {
      map[0][0].minHP = 0 - map[0][0].minHP;
      map[0][0].currentHPWithMinHPb = 0;
    }

    Grid left = dungeon[0][0];
    for (int j = 1; j < n; j++) {
      if (dungeon[0][j] >= 0 || dungeon[0][j] + map[0][j-1].currentHP >= 0)
        map[0][j] = new Grid(map[0][j-1].minHP, map[0][j-1].currentHP + dungeon[0][j])
      else
        map[0][j] = new Grid(map[0][j-1].minHP - (dungeon[0][j] + map[0][j-1].currentHP) ,0);
    }
  }

  class Grid {
    int minHP;
    int currentHP;
    Grid() {
      minHP = 0;
      currentHP = 0;
    }
    Grid(int min, int current) {
      minHP = min;
      currentHP = current;
    }
  }
}
