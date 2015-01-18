public class TwoDimensionLPS {
/*
  N*N matrix
  Largest possible sum


*/
  public static class Result {
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int sum;
    public Result() {
      startX = -1;
      startY = -1;
      endX = -1;
      endY = -1;
      sum = -1;
    }

    public Result(int x, int y, int ex, int ey, int s) {
      startX = x;
      startY = y;
      endX = ex;
      endY = ey;
      sum = s;
    }
  }

  public static Result getLPS(int[][] matrix) {
    if (matrix.length <= 0 || matrix[0].length <= 0) {
      return new Result();
    }

    Result maxResult = null;
    boolean started = false;
    int height = matrix.length;
    int width = matrix[0].length;



    for (int i = 0; i < height; i++) {
      for (int ei = i; ei < height ; ei++) {
        Result r = getSubLPS(matrix, i, ei);

        if (!started) {
          started = true;
          maxResult = r;
        }

        if (r.sum > maxResult.sum) {
          maxResult = r;
        }
      }
    }

    return maxResult;
  }

  private static Result getSubLPS(int[][] matrix, int x, int ex) {
    if (x == ex) {
      return getLinearLPS(matrix[x], x, ex);
    }
    else {
      int[] array = new int[matrix[x].length];
      for (int i = 0; i < matrix[x].length; i++ ) {
        int subSum = 0;
        for (int j = x; j <= ex; j++) {
          subSum = matrix[j][i];
        }
        array[i] = subSum;
      }
      return getLinearLPS(array, x, ex);
    }
  }

  public static Result getLinearLPS(int[] array, int x, int ex) {
    int sum = 0;
    int start = -1;
    int end = -1;
    for (int i = 0; i < array.length; i++) {
      if (start < 0) {
        start = i;
      }
      sum = sum + array[i];
      end = i;
      if (sum < 0) {
        sum = 0;
        start = -1;
        end = -1;
      }
    }
    return new Result(x,start,ex,end,sum);
  }


  public static void printResult(Result r) {
    System.out.println("start: (" + r.startX + ", " + r.startY + ") end: (" + r.endX + ", " + r.endY + "), SUM: " + r.sum);
  }

  public static void main(String[] args) {
    int m[][] = new int[50][50];
    int matrix[][] = {
      {11, -2, 13, -4, 5, -1, -16},
      {-21, 13, 4, 15, 15, -14, -1},
      {-1, 12, 41, 12, -2, -81, 76},
      {1, 24, 53, 53, -45, -67, 90},
      {43, -5, -6, -7, -8, -9, -10},
      {-3, -4, -6, 15, -7, 99, -10}
    };

    Result r = getLPS(matrix);
    printResult(r);

  }
}
