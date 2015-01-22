// https://oj.leetcode.com/problems/jump-game-ii/
//
//
//
//
//

import java.util.Arrays;

public class Solution {
  public int jump(int[] A) {
    int[] jumpCount = new int[A.length];
    int jump, i ,j, currentNewest = 0;
    Arrays.fill(jumpCount,Integer.MAX_VALUE);

    if (A.length == 1) {
      return 0;
    }

    for (i = 0, jumpCount[0] = 0; i < A.length; i++) {
      if (i + A[i] <= currentNewest) {
        continue;
      }
      jump = jumpCount[i] + 1;
      j = 1;
      while(j <= A[i] && i+j < A.length) {
        jumpCount[i+j] = jump < jumpCount[i+j] ? jump : jumpCount[i+j];
        j++;
      }
      currentNewest = i + A[i];

      if(jumpCount[A.length-1] < Integer.MAX_VALUE) return jumpCount[A.length-1];
    }

    return jumpCount[A.length];
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = {1,3,3,1,2,5,4};
    System.out.println("->|" + s.jump(a) + "|<-");

    int[] b = {5,1};
    System.out.println("->|" + s.jump(b) + "|<-");
  }

  public void printArray(int[] a) {
    for (int i = 0; i < a.length; i++ ) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }
}
