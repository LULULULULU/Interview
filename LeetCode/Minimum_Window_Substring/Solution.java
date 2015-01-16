// https://oj.leetcode.com/problems/minimum-window-substring/
/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Character;

public class Solution {

  public HashMap<Character, CountNode> mem;
  public HashMap<Character, Integer> target;

  public static class CountNode {
    public int count;
    public int position;
    public CountNode next;
    public CountNode() {
      count = 0;
      position = -1;
      next = null;
    }
  }

  public static class Result {
    public int start;
    public int end;
    public Result(int s1, int s2) {
      start = s1;
      end = s2;
    }
  }

  public String minWindow(String S, String T) {
    mem = new HashMap<Character, CountNode>();
    target = getTargetCounts(T);
    ArrayList<Character> keys = new ArrayList<Character>(target.keySet());
    Result r = null;
    String returnS = "";
    int min = -1;
    int max = -1;

    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      if (keys.contains(c)) {
        pushCharacterInMap(c,i);
        r = getResult(S);
        if (r != null) {
          if (min < 0 || (r.end - r.start) < (max - min) ) {
            min = r.start;
            max = r.end;
          }
        }
      }
    }

    if (min > 0 || max > 0) {
      returnS = S.substring(min, max + 1);
    }

    return returnS;
  }

  /*
   * Generate the target counts map
   */
  private HashMap<Character, Integer> getTargetCounts(String S) {
    HashMap<Character, Integer> target = new HashMap<Character, Integer>();
    for (int i = 0; i < S.length(); i++ ) {
      char c = S.charAt(i);
      if (target.containsKey(c)) {
        target.put(c,target.get(c) + 1);
      }
      else {
        target.put(c,1);
      }
    }
    return target;
  }

  /*
   * Push char into the linklist
   */
  private void pushCharacterInMap(char c, int position) {
    CountNode node = new CountNode();
    node.position = position;

    // If there is node for this key,
    if (mem.containsKey(c)) {
      CountNode current = mem.get(c);
      node.count = current.count + 1;
      node.next = current;
      mem.put(c, node);
    }
    else
    {
      node.count = 1;
      mem.put(c, node);
    }
  }

  /*
   * Get result of the s
   */
  private Result getResult(String S) {
    int min = -1;
    int max = -1;
    int targetCount;
    CountNode node;
    int i;

    for (char c : target.keySet()) {
      if (!mem.containsKey(c)) return null;
      targetCount = target.get(c);
      node = mem.get(c);
      if (node.count < targetCount) return null;

      if (min < 0 || node.position < min) min = node.position;
      if (max < 0 || node.position > max) max = node.position;

      i = targetCount;
      if (i != 1) {
        while(i != 1) {
          i--;
          node = node.next;
        }
        if (min < 0 || node.position < min) min = node.position;
        if (max < 0 || node.position > max) max = node.position;
      }
    }
    return new Result(min, max);
  }


  public static void main(String[] args) {
    Solution s = new Solution();
    String s1 = "ADOBECODEBANC";
    String s2 = "ADBOOC";
    System.out.println(s.minWindow(s1,s2));
  }

  public static void printMem(HashMap<Character, CountNode> mem) {
    CountNode n;
    System.out.println("--------------");
    for (char c : mem.keySet()) {
      n = mem.get(c);
      System.out.print(c + ": ");
      while (n != null) {
        System.out.print(n.position + "(" + n.count + ") ");
        n = n.next;
      }
      System.out.println();
    }
    System.out.println("--------------");
  }
}
