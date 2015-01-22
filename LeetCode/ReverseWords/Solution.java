// https://oj.leetcode.com/problems/reverse-words-in-a-string/
// For example,
// Given s = "the sky is blue",
// return "blue is sky the".

import java.lang.StringBuilder;

public class Solution {
    public String reverseWords(String s) {
        int start = s.length() - 1;
        int end;
        StringBuilder sb = new StringBuilder();
        while(start >= 0) {
            while(start >= 0 && s.charAt(start) == ' ') start--;
            if (start < 0) return sb.toString().trim();
            end = start;
            while(end >= 0 && s.charAt(end) != ' ') end--;
            sb.append(s.substring(end+1,start+1));
            sb.append(' ');
            start = end;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
      Solution s = new Solution();
      String a = "  a  b  c  d   ";
      String b = "a";
      String c = " a";
      String d = "c ";
      String e = "afadsf fsdf ";
      String f = " afadsf fsdf";
      String g = "aa  afadsf fsdf";
      System.out.println("->|" + s.reverseWords(a) + "|<-");
      System.out.println("->|" + s.reverseWords(b) + "|<-");
      System.out.println("->|" + s.reverseWords(c) + "|<-");
      System.out.println("->|" + s.reverseWords(d) + "|<-");
      System.out.println("->|" + s.reverseWords(e) + "|<-");
      System.out.println("->|" + s.reverseWords(f) + "|<-");
      System.out.println("->|" + s.reverseWords(g) + "|<-");
    }
}
