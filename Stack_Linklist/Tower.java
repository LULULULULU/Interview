

public class Tower {

  public Stack<Integer> stack;
  public int index;

  public Tower(int index) {
    this.stack = new Stack<Integer>();
    this.index = index;
  }

  // Add disk to top
  public void add(int disk) {
    if ( stack.peek() == null || disk < stack.peek() ) {
      stack.push(disk);
    }
    else {
      System.out.println("Error placing disk " + disk);
    }
  }

  // Move top disk to tower
  public void moveTopTo(Tower t) {
    int top = stack.pop();
    t.add(top);
    System.out.println("Move disk " + top + " from " + this.index + " to Tower " + t.index);
  }

  public void moveDisk(int n, Tower destination,  Tower buffer) {
    if (n > 0)
    {
      moveDisk(n - 1, buffer, destination);
      moveTopTo(destination);
      buffer.moveDisk(n-1, destination, this);
    }
  }

  public static void main(String[] args)
  {
    int n = 6;
    Tower[] towers = new Tower[3];
    for (int i = 0; i < 3; i++ ) {
      towers[i] = new Tower(i);
    }

    for (int i = n - 1; i >= 0; i-- ) {
      towers[0].add(i);
    }

    towers[0].moveDisk(n, towers[2], towers[1]);
  }
}
