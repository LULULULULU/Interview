/* Write a program that takes an integer and prints out all ways to multiply smaller
integers that equal the original number, without repeating sets of factors. In other words,
if your output contains 4 * 3, you should not print out 3 * 4 again as that would be a repeating set.
Note that this is not asking for prime factorization only. Also, you can assume that the input integers
are reasonable in size; correctness is more important than efficiency.
*/

/*
Points in this code:
  Iterate for each possible factor;
  Every time find a factor, save to the result list with the current factor list;
  And then go to look for more factors for this foctor.

Comparator<T>
  implementation compare();

TreeMap<T1,T2>
  keySet();

ArrayList<T>
  add();
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Comparator;

public class PrintFactors {

  public static class stringComparator implements Comparator<String> {
    public int compare(String a, String b) {
      if (a.length() != b.length()) {
        return a.length() - b.length();
      }
      else {
        return a.compareTo(b);
      }
    }
  }

  public static void printFactors(int a) {
    TreeMap<String, Integer> results = new TreeMap<String,Integer>(new stringComparator());
    recursionGetFactors(new ArrayList<Integer>(), a, results);

    for (String s : results.keySet()) {
      System.out.println(s);
    }
  }

  public static void recursionGetFactors(ArrayList<Integer> mem, int a, TreeMap<String, Integer> results) {
    for (int i = 2; i <= a/2; i++) {
// if find one a factor
      if (a % i == 0) {
        ArrayList<Integer> newMem = new ArrayList<Integer>(mem);
        newMem.add(i);
        recursionGetFactors(newMem, a / i, results);
      }
    }

    printArrayList(mem, a, results);
  }

  public static void printArrayList(ArrayList<Integer> mem, int left, TreeMap<String, Integer> results) {
    StringBuffer buf = new StringBuffer();
    mem.add(left);
    Collections.sort(mem);
    for (Integer i : mem) {
      buf.append(i);
      buf.append(" * ");
    }
    buf.delete(buf.length() - 2, buf.length());
    results.put(buf.toString(), 1);
  }

  public static void main(String[] args) {
    printFactors(96);
  }
}


