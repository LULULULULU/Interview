public class BitManipulation {

  // Get
  public boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
  }

  // Set
  public int setBit(int num, int i) {
    return num | (1 << i);
  }


  public void clearBit(int num, int i) {
    int mask = ~(1 << i);
    return num & mask;
  }

}
