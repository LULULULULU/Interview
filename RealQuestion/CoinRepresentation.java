public class CoinRepresentation {
  public int[] coins;
  public int types;

  public CoinRepresentation(int[] coins) {
    this.coins = coins;
    /* -----------

    array.length

    ------------- */
    this.types = coins.length;
  }


  public int getNumberRecursive(int moneyLeft, int currentCoinType) {
    int currentCoin, currentCoinMax;
    // if money is zero, we get a solution
    if (moneyLeft == 0) {
      return 1;
    }
    else if(currentCoinType == this.types - 1) {
      // When this the last type,
      // if moneyLeft can be divided by currentCoin, we have a solution
      currentCoin = this.coins[currentCoinType];
      if (moneyLeft % currentCoin == 0) {
        return 1;
      }
      else {
      // if moneyLeft cannot be divided by cC.
        System.out.println("No solution.");
        return 0;
      }
    }
    else {
      // if there is a type to go
      currentCoin = this.coins[currentCoinType];
      currentCoinMax = moneyLeft / currentCoin;
      int sum = 0;
      for (int i = currentCoinMax; i >= 0; i--) {
        sum += getNumberRecursive(moneyLeft - currentCoin * i, currentCoinType+1);
      }
      return sum;
    }
  }


  public void getCasesRecursive(int moneyLeft, int currentCoinType, int[] solution) {
    int currentCoin, currentCoinMax;
    // When currentCoinType = last type,
    if (currentCoinType == this.types - 1) {
      // If moneyLeft can be divided by current currentCoin, we have a solution
      currentCoin = this.coins[currentCoinType];
      if (moneyLeft % currentCoin == 0) {
        solution[currentCoinType] = moneyLeft / currentCoin;

        System.out.print("~> ");
        for (int i = 0; i < this.types; i++ ) {
          //"~> 25 * n + 25 * n"
          System.out.print(this.coins[i]);
          System.out.print("*");
          System.out.print(solution[i]);
          if (i != this.types-1) {
            System.out.print(" + ");
          }
        }
        System.out.println();
      }
      else {
        // if moneyLeft cannot be divided by cC.
        System.out.println("No solution.");
        return;
      }
    }
    else {
      // if there is a new type to go
      currentCoin = this.coins[currentCoinType];
      currentCoinMax = moneyLeft / currentCoin;
      for (int i = currentCoinMax; i >= 0; i--) {
        int[] newArray = new int[this.types];
        /* -----------

         arraycopy(Object src, int srcPos,
                             Object dest, int destPos, int length)

                             ------------- */
        System.arraycopy(solution, 0, newArray, 0, 4);

        newArray[currentCoinType] = i;
        getCasesRecursive(moneyLeft - currentCoin * i, currentCoinType+1, newArray);
      }

    }

  }

  public static void main(String[] args) {
    int[] array = {25, 10, 5, 1};  // Init new array {}
    CoinRepresentation cr = new CoinRepresentation(array);
    System.out.println(cr.getNumberRecursive(100,0));
    System.out.println(cr.getNumberRecursive(25,0));
    System.out.println(cr.getNumberRecursive(0,0));
    System.out.println(cr.getNumberRecursive(4,0));
    System.out.println(cr.getNumberRecursive(5,0));
    System.out.println(cr.getNumberRecursive(6,0));

    System.out.println("------------ 25 ---------------");
    int[] newArray = {0, 0, 0, 0};
    cr.getCasesRecursive(25,0,newArray);

    System.out.println("------------ 100 ---------------");
    int[] newArray1 = {0, 0, 0, 0};
    cr.getCasesRecursive(100,0,newArray1);
  }
}
