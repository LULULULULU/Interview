// https://oj.leetcode.com/problems/add-binary/
//
// Not fast!
//

import java.lang.StringBuilder;

public class Solution {
  public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int al = a.length();
    int bl = b.length();
    int carry = 0;
    int i = al - 1;
    int j = bl - 1;

    for (int sum = 0; i >= 0 && j >= 0; i--, j--, sum = 0) {
      sum = (a.charAt(i)-'0') + (b.charAt(j)-'0') + carry;
      if (sum >= 2) {
        carry = 1;
        sb.insert(0, sum - 2);
      }
      else {
        carry = 0;
        sb.insert(0, sum);
      }
    }

    if (i >= 0) {
      for (int sum = 0; i >= 0 && carry == 1; i--, sum = 0) {
        sum = (a.charAt(i) - '0') + carry;
        if (sum >= 2) {
          carry = 1;
          sb.insert(0, sum - 2);
        }
        else {
          carry = 0;
          sb.insert(0, sum);
        }
      }

      if (i >= 0) sb.insert(0,a.substring(0,i+1));
    }
    else if (j >= 0) {
      for (int sum = 0; j >= 0 && carry == 1; j--, sum = 0) {
        sum = (b.charAt(j) - '0') + carry;
        if (sum >= 2) {
          carry = 1;
          sb.insert(0, sum - 2);
        }
        else {
          carry = 0;
          sb.insert(0, sum);
        }
      }

      if (j >= 0) sb.insert(0,b.substring(0,j+1));
    }

    if (carry == 1) sb.insert(0,1);

    return sb.toString();
  }

  public static void main(String[] args) {
    String a = "111";
    String b = "111";
    Solution s = new Solution();
    System.out.println(s.addBinary(a,b));
  }
}
