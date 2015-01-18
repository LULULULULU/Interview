public class MyString {
  public String str;
  public static boolean isUniqueChar(String str) {
    int[] charCnt = new int[256];
    /* -----------

    string.length()

    ------------- */
    for (int i = 0; i < str.length(); i++) {
      int j = (int)str.charAt(i);
      charCnt[j]++;
      if (charCnt[j] > 1) {
        System.out.println(str + " not unique.");
        return false;
      }
    }
    System.out.println(str + " is unique.");
    return true;
  }

  public static boolean isPermutation(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }

    int[] charCnt = new int[256];
    for (int i = 0; i < str1.length(); i++) {
      int j = (int)str1.charAt(i);
      charCnt[j]++;
    }

    for (int i = 0; i < str2.length(); i++) {
      int j = (int)str2.charAt(i);
      charCnt[j]--;
      if (charCnt[j] < 0) {
        return false;
      }
    }
    System.out.println(str1 + " " + str2 + " isPermutation");
    return true;
  }

  public static void main(String[] args) {
    String s = "akljvoiewrx,mnc301952";
    String ss = "qwertyuiop[asdfghjkl;'zxcvbnm,./']1234567890-=\\";

    String s1 = "abcdefghijklmnopqrstuvwxyz";
    String s2 = "qrstuvwxyzghijklmnopacdefb";
    MyString.isUniqueChar(s);
    MyString.isPermutation(s1, s2);
  }

}
