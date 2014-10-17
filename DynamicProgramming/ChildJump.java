public class ChildJump {

  public long[] steps;

  public long jump(int n) {
    if (n <= 3)
      steps = new long[4];
    else
      steps = new long[n + 1];

    steps[0] = 0;
    steps[1] = 1;
    steps[2] = 2;
    steps[3] = 4;
    int currentStep = 3;

    if (n <= 3) {
      System.out.println("There are " + steps[n] + " ways to jump to " + n + " staircases." );
      return steps[n];
    }

    while(n > currentStep)
    {
      steps[++currentStep] = steps[currentStep - 1] + steps[currentStep - 2] + steps[currentStep - 3];
    }

    System.out.println("There are " + steps[currentStep] + " ways to jump to " + n + " staircases." );
    return steps[currentStep];
  }

  public static void main(String[] args)
  {
    ChildJump child = new ChildJump();

    child.jump(1);
    child.jump(2);
    child.jump(3);
    child.jump(5);
    child.jump(10);
    child.jump(50);
    child.jump(100);
  }

}
