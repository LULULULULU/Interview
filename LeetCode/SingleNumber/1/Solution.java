// https://oj.leetcode.com/problems/single-number/

public class Solution {
  public int singleNumber(int[] A) {
    int v1 = 0;
    for (int i = 0; i < A.length; i++)
      v1 ^= A[i];

    return v1;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] array = {3,4,5,3,4,6,5,5,4,3,4,3,6};
    System.out.println(s.singleNumber(array));
  }
}
