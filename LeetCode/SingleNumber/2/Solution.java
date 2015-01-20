// https://oj.leetcode.com/problems/single-number-ii/
// Bit manipulation
// {3,4,5,3,4,6,5,5,4,3} find 6
//
// Not fast!
//


public class Solution {
  public int singleNumber(int[] A) {
    int v1 = 0, v2 = 0, v3 = 0;
    int v11 = 0, v22 = 0, v33 = 0;
    for (int i = 0; i < A.length; i++) {

      v3 = ((~A[i]) & v3) | (A[i] & v2);
      v2 = ((~A[i]) & v2) | (A[i] & v1);
      v1 = ((~A[i]) & v1) | (A[i] & (~v2) & (~v3));

      //      ec3 = ec2 & ai
      //      ec2 = (ec2 | (ec1 & ai)) & (~ec3)
      //      ec1 = (ec1 | ai) & (~ec3)
      v33 = v22 & A[i];
      v22 = (v22 | (v11 & A[i])) & (~v33);
      v11 = (v11 | A[i]) & (~v33);

      System.out.println("After " + A[i] + " (v1,v2,v3)=" + " (" + v1 + ", " + v2 + ", " + v3 + ")" );
      System.out.println("After " + A[i] + "                 (v11,v22,v33)=" + " (" + v11 + ", " + v22 + ", " + v33 + ")" );
    }

    return v1;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] array = {3,4,5,3,4,6,5,5,4,3};
    System.out.println(s.singleNumber(array));
  }
}
