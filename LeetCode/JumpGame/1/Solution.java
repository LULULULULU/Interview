// https://oj.leetcode.com/problems/jump-game/

public class Solution {
  public boolean canJump(int[] A) {
    if (A.length == 0) return true;
    int maxReach = 0;
    for (int i = 0; i < A.length; i++) {
      if (i > maxReach) return false;
      if (i + A[i] <= maxReach) continue;
      maxReach = i + A[i];
      if (maxReach >= A.length - 1) return true;
    }

    return maxReach >= A.length - 1;
  }
}
