// CareerUp: http://www.careercup.com/question?id=5863307617501184
import java.util.Arrays;
import java.util.Set;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.TreeMap;

import java.lang.Comparable;
import java.util.Comparator;

public class TriangleTriplets {

  /*----- -------------------- -----*/
  /*----- Implement Comparable -----*/
  /* int compareTo(E obj) must be implemented */
  /*----- -------------------- -----*/
  public static class Triplets implements Comparable<Triplets> {
    public int[] t;
    public Triplets() {
      this.t = null;
    }
    public Triplets(int[] array) {
      Arrays.sort(array); // Use Arrays.sort() to sort basic item arrays: String, Integer ...
                          // Another way to use this is Arrays.sort(array, new MyComparator());
      this.t = array;
    }

    public int compareTo(Triplets tt) {
      int value1 = this.t[0] - tt.t[0];
      int value2 = this.t[1] - tt.t[1];
      int value3 = this.t[2] - tt.t[2];

      if (value1 == 0 && value2 == 0 && value3 == 0) {
        return 0;
      }

      if (value1 != 0) {
        return value1;
      } else if (value2 != 0) {
        return value2;
      } else {
        return value3;
      }
    }
  }

  /*----- -------------------- -----*/
  /*----- Implement Comparator -----*/
  /* int compare(E obj1, E obj2) must be implemented */
  /*----- -------------------- -----*/
  public static class TripletComparator implements Comparator<Triplets> {
    public int compare(Triplets t1, Triplets t2) {
      return t1.compareTo(t2);
    }
  }

  /*----- ------------ -----*/
  /*----- Result Class -----*/
  /*----- ------------ -----*/
  public static class Result {
    public ArrayList<Triplets> results;
    public int number;
    public Result() {
      this.results = null;
      this.number = 0;
    }

    public Result(ArrayList<Triplets> al, int n) {
      this.results = al;
      this.number = n;
    }

    public void printResult() {
      System.out.println(this.number);
      for (Triplets t : this.results) {
        for (int i = 0; i < t.t.length ;i++ ) {
          System.out.print(t.t[i] + " ");
        }
        System.out.println();
      }
    }
  }

  /*----- -------------- -----*/
  /*----- Static Methods -----*/
  /*----- ------------- ------*/
  public static Result getAllTriangleTriplets(int[] array) {
    TripletComparator comparator = new TripletComparator();
    // To initaite a TreeMap with a comparator
    TreeMap<Triplets, Integer> mem = new TreeMap<Triplets, Integer>(comparator);

    for (int i = 0; i < array.length; i++ ) {
      for (int j = i+1; j < array.length; j++ ) {
        for (int k = j+1; k < array.length; k++) {
          int[] a = {array[i], array[j], array[k]};
          Triplets t = new Triplets(a);
          if (!mem.containsKey(t)) {
            mem.put(t,1);
          }
        }
      }
    }

    ArrayList<Triplets> list = new ArrayList<Triplets>(mem.keySet());
    int r = mem.size();
    return new Result(list,r);
  }

  public static void main(String[] args) {
    // int[] array = {7,8,8,7,7,7,7,7,9,10};
    int[] array = {9,8,10,7};
    Result result = getAllTriangleTriplets(array);
    result.printResult();
  }
}
