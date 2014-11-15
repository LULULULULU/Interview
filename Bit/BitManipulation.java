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

/*
10 15
25
int 4 bytes 32 bits

    0000 1010
    0000 1111
    0001 1001

add:0000 0101 (XOR
cry:0001 0100 (&<<1

add:0001 0001
cry:0000 1000

add:0001 1001
cry:0000 0000

*/

/*
diamond club heart spade
0-12 13-25 26-38 39-51

*/


/*
0-9      1
10-99:   9 + 10 = 19
    12 2X 32 ... 92
 20 21 22 ... 29

100-999:
  2 - 10 - 99

23895
^   ^
12345

    5th 2: 1-4: 0000-2389   2390
    4th 2: 1-3: 000-238     239 * 10
    3rd 2: 1-2: 00-23       24 * 100
    2nd 2: 1-1: 0-2         3 * 1000
    1st 2:    : 2           1


182
XX2 002 012 022 032 .. 112 122 132 .. 182 = 19
X2X 020 021 022 023 024 025 029 10
X2X 120 121 122 123 124
19 + 2 = 20


*/

}
