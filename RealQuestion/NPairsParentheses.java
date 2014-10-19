public class NPairsParentheses {

  // Method one
  // n=1 ^(^)^
  // brackets=2 slots=3
  // n=2 slot1=()() slot2=(()) slot3=()()
  //
  // n=2 ^(^)^(^)^  ^(^(^)^)^
  // brackets=4 slots=5
  // n=3 ()()() (())() ()()() ()(()) ()()()
  //     ()(()) (()()) ((())) (()()) (())()
  // Insert slot and remove the duplicates
  // CONS: slow, duplicates, removing is not necessary

  // Method two
  // Build from scratch
  // For n you can build n ('s and n )'s

  // How many possible results for input n
  // n=0 Sn=0
  // n=1 Sn=1
  // n=2 Sn=SUM( S(i) * S(n-i) )i=1~n

  public long getNumberRecursive(int leftBracketsLeft, int rightBracketsLeft)
  {
    // if leftBracketsLeft = 0, we just found a way
    if (leftBracketsLeft == 0 ) {
      return 1;
    }
    // When leftBracketsLeft > 0
    else if (leftBracketsLeft < rightBracketsLeft) {
      // If leftBracketsLeft == rightBracket, we can put a left or a right
      return getNumberRecursive(leftBracketsLeft-1, rightBracketsLeft) + getNumberRecursive(leftBracketsLeft, rightBracketsLeft-1);
    }
    else {
      // If leftBracketsLeft == rightBrackets, only put a left there
      return getNumberRecursive(leftBracketsLeft-1, rightBracketsLeft);
    }
  }

  public void getCasesRecursive(StringBuffer sb, int lbl, int rbl)
  {
    // if lbl = 0, we just found a way
    if (lbl == 0 ) {
      for (int i = rbl; i > 0 ; i--) {
        sb.append(')');
      }
      System.out.println(sb);
    }
    // When lbl > 0
    else if (lbl < rbl) {
      // If lbl == rightBracket, we can put a left or a right
      StringBuffer sb1 = new StringBuffer(sb);
      StringBuffer sb2 = new StringBuffer(sb);
      getCasesRecursive(sb1.append('('), lbl-1, rbl);
      getCasesRecursive(sb2.append(')'), lbl, rbl-1);
    }
    else {
      // If lbl == rightBrackets, only put a left there
      StringBuffer sb3 = new StringBuffer(sb);
      getCasesRecursive(sb3.append('('), lbl-1, rbl);
    }
  }

  public static void main(String[] args) {
    NPairsParentheses p = new NPairsParentheses();

    int input = 5;
    StringBuffer sb = new StringBuffer();
    System.out.println(p.getNumberRecursive(input, input));
    p.getCasesRecursive(sb, input, input);

    input = 6;
    sb = new StringBuffer();
    System.out.println(p.getNumberRecursive(input, input));
    // p.getCasesRecursive(sb, input, input);

    input = 7;
    sb = new StringBuffer();
    System.out.println(p.getNumberRecursive(input, input));
    // p.getCasesRecursive(sb, input, input);

    input = 15;
    sb = new StringBuffer();
    System.out.println(p.getNumberRecursive(input, input));
    // p.getCasesRecursive(sb, input, input);
  }
}
