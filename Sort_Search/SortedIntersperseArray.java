public class SortedIntersperseArray {
  public String[] array;

  public int search(String s) {
    return search(s,0,this.array.length - 1);
  }

  private int search(String s, int start, int end) {
    int mid = (start + end) / 2;

    // if mid is target, return.
    if (this.array[mid].compareTo(s) == 0) {
      return mid;
    }

    int midToLeft = mid;
    int midToRight = mid;

    // if mid is "", go to left and right until find a valid value
    while (midToLeft >= start && this.array[midToLeft].compareTo("") == 0 && midToRight <= end && this.array[midToRight].compareTo("") == 0) {
      midToLeft--;
      midToRight++;
    }

    // After find the cloest none "" string,
    if (this.array[midToLeft].length() > 0) {
      // If find the string some whereleft
      if (this.array[midToLeft].equals(s)) {
        return midToLeft;
      }
      if (this.array[midToRight].compareTo(s) > 0) {
        return search(s,start,midToLeft);
      }
      else {
        return search(s,midToRight,end);
      }
    }
    else if (this.array[midToRight].length() > 0) {
      // If find the string somewhere right
      if (this.array[midToRight].equals(s)) {
        return midToRight;
      }
      if (this.array[midToRight].compareTo(s) > 0) {
        return search(s,start,midToLeft);
      }
      else {
        return search(s,midToRight,end);
      }
    }
    else {
      // If neight neither midToLeft nore midToRight has content,
      if (midToLeft < start) {
        if (midToRight > end) {
          return -1;
        } else {
          return search(s, midToRight, end);
        }
      }
      else {
        return  search(s,start,midToLeft);
      }
    }
  }

  public static void main(String[] args) {

    String s1 = new String("carrrr");
    String s2 = new String("at");
    System.out.println(s1.compareTo(s2));  // return 2
    System.out.println(s2.compareTo(s1));  // return -2
    System.out.println();

    SortedIntersperseArray array = new SortedIntersperseArray();
    String[] a = {"at", "", "", "", "ball", "", "", "car", "", "", "", "dad" ,"", ""};
    array.array = a;
    System.out.println(array.search("dad"));
  }
}
