// CareerUp: http://www.careercup.com/question?id=5653460783464448

public class NoAdjacentSequence {

  public static int getNoAdjacentSquence(int[] numbers) {
    StringBuffer newSequence = new StringBuffer();
    return recursiveSequence(newSequence, numbers, numbers[0]+numbers[1]+numbers[2]+numbers[3]);
  }

  private static int recursiveSequence(StringBuffer currentSequence, int[] numbers, int length) {

    if (currentSequence.length() == length) {
      System.out.println(currentSequence);
      return 1;
    }

    char last = ' ';
    if (currentSequence.length() > 0) {
      last = currentSequence.charAt(currentSequence.length() - 1);
    }

    int count = 0;
    for (int i = 0; i <= 3; i++) {
      if ( numbers[i] > 0 && last - '1' != i) {
        StringBuffer newSequence = new StringBuffer(currentSequence);
        newSequence.append((char)('1'+i));
        numbers[i] = numbers[i] - 1;
        count += recursiveSequence(newSequence, numbers, length);
        numbers[i] = numbers[i] + 1;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[] numbers = {1,2,0,0};
    System.out.println("Count: " + getNoAdjacentSquence(numbers) );
  }
}
