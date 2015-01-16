// https://oj.leetcode.com/problems/minimum-window-substring/

import java.util.HashMap;
public class Solution {
  public HashMap<Character, Integer> window;
  public HashMap<Character, Integer> target;

  public String minWindow(String S, String T) {
    if (S.length() < T.length()) return "";
    target = new HashMap<Character, Integer>();
    window = new HashMap<Character, Integer>();

    for (int i = 0; i < T.length(); i++ ) {
      char c = T.charAt(i);
      target.put(c, target.containsKey(c) ? target.get(c) + 1 : 1);
      if (!window.containsKey(c)) {
        window.put(c, 0);
      }
    }

    int start, end;
    int count = 0;
    int min = 0, max = 0;
    int length = Integer.MAX_VALUE;

    for (start = end = 0; end < S.length(); end++) {
      char _char = S.charAt(end);
      if ( target.containsKey(_char) ) {
        if (window.get(_char) < target.get(_char)) count++;
        window.put(_char, window.get(_char) + 1);
      }

      _char = S.charAt(start);
      if (count >= T.length()) {
        while( !window.containsKey(_char)
          || window.get(_char) > target.get(_char) ) {
          if ( window.containsKey(_char) )
            window.put( _char, window.get(_char) - 1 );
          start++;
          _char = S.charAt(start);
        }

        if (end - start + 1 < length) {
          min = start;
          max = end;
          length = end - start + 1;
        }

        window.put(_char, window.get(_char) - 1);
        start++;
        count--;
      }
    }

    if (length == Integer.MAX_VALUE) return "";
    return S.substring(min, max + 1);
  }

  /*-----*/
  /*-- Test Code ---*/
  /*-----*/
  public static void main(String[] args) {
    Solution s = new Solution();
    String s1 = "ADOBECODEBANC";
    String s2 = "ADB";
    System.out.println(s.minWindow(s1,s2));
  }
  /*-----*/
  /*-- Test Code ---*/
  /*-----*/
}
