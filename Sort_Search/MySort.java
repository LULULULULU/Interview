import java.util.Comparator;
import java.util.Arrays;

public class MySort {

  /*----- ---------- -----*/
  /*----- Merge Sort -----*/
  /*----- ---------- -----*/
  // Divid array into two, sort the separatly and merge.
  // Runtime: O(n Log(n)) average and worstcase.
  // Memory:Depends. Normally a buffer array with n size.
  public void mergeSort(int[] array) {
    int[] helper = new int[array.length];
    mergeSort(array, helper, 0, array.length - 1);
  }

  private void mergeSort(int[] array, int[] helper, int start, int end) {
    if (start < end) {
      int middle = (start + end) / 2;
      mergeSort(array, helper, start, middle);
      mergeSort(array, helper, middle + 1, end);
      merge(array, helper, start, middle, end);
    }
  }

  private void merge(int[] array, int[] helper, int start, int middle, int end) {
    for (int i = start; i <= end; i++) {
      helper[i] = array[i];
    }

    int helperLeft = start;
    int helperRight = middle + 1;
    int current = start;

    while(helperLeft <= middle && helperRight <= end) {
      if (helper[helperLeft] <= helper[helperRight]) {
        array[current++] = helper[helperLeft++];
      }
      else {
        array[current++] = helper[helperRight++];
      }
    }

    while(helperLeft <= middle) {
      array[current++] = helper[helperLeft++];
    }
  }

  /*----- ---------- -----*/
  /*----- Quick Sort -----*/
  /*----- ---------- -----*/
  // Pick a pivot and partition the array.
  // In each after each partition, first half is smaller than the pivot, second half is bigger than the pivot.
  public void quickSort(int[] array) {
    quickSort(array, 0, array.length -1);
  }

  private int partition(int[] array, int start, int end) {
    int pivot = getPivot(array, start, end);
    int pivotV = array[pivot];

    while(start <= end) {
      while(array[start] < pivotV) start++;
      while(array[end] > pivotV) end--;
      if (start <= end) {
        swap(array, start++, end--);
      }
    }

    return start;
  }

  private void quickSort(int[] array, int start, int end) {
    System.out.println(start + " " + end);
    int pivot = partition(array, start, end);
    // printIntArray(array);
    if (start < pivot - 1) {
      quickSort(array, start, pivot - 1);
    }
    if (end > pivot) {
      quickSort(array, pivot, end);
    }
  }

  private int getPivot(int[] array, int start, int end) {
    int pivot = (array[start] >= array[end]) ? start : end;
    return (array[pivot] > array[(start+end) / 2]) ? (start+end)/2 : pivot;
  }

  private void swap(int[] array, int indexA, int indexB) {
    if (indexB == indexA) {
      return;
    }
    int v = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = v;
  }

  /*----- ---------- -----*/
  /*----- Comparator -----*/
  /*----- ---------- -----*/
  // This is the comparator that is used to sort Anagram strings
  public class MyStringAnagramComparator implements Comparator<String>
  {
    public String sortChars(String s) {
      char[] content = s.toCharArray();
      Arrays.sort(content);
      return new String(content);
    }

    public int compare(String s1, String s2) {
      return sortChars(s1).compareTo(sortChars(s2));
    }
  }

  /*----- ---------- -----*/
  /*-----  Others    -----*/
  /*----- ---------- -----*/

  public static void printIntArray(int[] array) {
    System.out.print("Array: ");
    for (int i = 0; i < array.length ; i++) {
      System.out.print(array[i]+ " ");
    }
    System.out.println();
  }

  public static void printStrArray(String[] array) {
    System.out.print("String Array: ");
    for (int i = 0; i < array.length ; i++) {
      System.out.print(array[i]+ " ");
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    MySort sort = new MySort();

    int[] array = {1,4,6,4,7,9,4,7,4,2,5,0,6,7,9,6,8,9,5,3,4,6,3,6,8,4,6,8,5,3,7,4,9,3,7,4,8,9,3,6,3,5,7,4,2,5,7,8,9,5,3,2};
    sort.mergeSort(array);
    MySort.printIntArray(array);


    int[] array1 = {1,4,6,4,7,9,4,7,4,2,5,0,6,7,9,6,8,9,5,3,4,6,3,6,8,4,6,8,5,3,7,4,9,3,7,4,8,9,3,6,3,5,7,4,2,5,7,8,9,5,3,2};
    MySort.printIntArray(array1);
    sort.quickSort(array1);
    MySort.printIntArray(array1);

    int[] array1_1 = {1,4,6,4,7,9,4,7,4,2,5,0,6,7,9,6,8,9,5,3,4,6,3,6,8,4,6,8,5,3,7,4,9,3,7,4,8,9,3,6,3,5,7,4,2,5,7,8,9,5,3,2};
    Arrays.sort(array1_1);
    MySort.printIntArray(array1_1);

    String[] array2 = {"a", "ab", "baba", "abab", "ba", "fasdf", "bbaa", "ovis", "kayak", "vios", "asdf", "fasfs"};
    /*-----
    Difference between inner class & static nested class.
    Without the static keyword it is an inner class and you will need
    an instance of MySort to access MySort.MyStringComparator.
     -----*/
    Arrays.sort(array2, sort.new MyStringAnagramComparator());
    MySort.printStrArray(array2);
  }
}
