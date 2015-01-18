// CareerUp: http://www.careercup.com/question?id=5705985515585536

public class Palindromic {

  public String str;
  public Palindromic() {
    this.str = null;
  }
  public Palindromic(String s) {
    this.str = s;
  }


  /* ----------------------------------- */
  /* ------ Recursive Forcing Way ------ */
  /* ----------------------------------- */
  public int recursiveLPS() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0 ; i <= this.str.length() - 1; i++) {
      sb.append('#');
      sb.append(this.str.charAt(i));
    }
    sb.append('#');

    System.out.println(sb);

    int maxLength = 0;
    int currentLength;
    for (int i = 0; i <= sb.length() -1; i++) {
      currentLength = recursiveLPSFromDigit(i,sb);
      if (maxLength < currentLength) {
        maxLength = currentLength;
        System.out.println("Max " + i);
      }
    }
    return maxLength;
  }

  private int recursiveLPSFromDigit(int i, StringBuffer sb) {
    int l = i;
    int r = i;
    for (; r <= sb.length() - 1 && l >= 0; l--, r++) {
      if (sb.charAt(l) != sb.charAt(r)) {
        break;
      }
    }
    l++;
    r--;

    int length = r - l + 1;
    // Return the correct value
    return (length - 1) / 2;
  }




  public static void main(String[] args) {
    Palindromic p = new Palindromic("41234565434444444");
    System.out.println("maxLength =" + p.recursiveLPS());
  }
}
