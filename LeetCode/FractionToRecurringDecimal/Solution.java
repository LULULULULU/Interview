import java.lang.StringBuilder;
import java.lang.Math;
import java.util.HashMap;
public class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    if (denominator == 1) return String.valueOf(numerator);
    if (numerator == 0) return "0";

    StringBuilder res = new StringBuilder();
    int negCnt = 0;
    if (numerator < 0) negCnt++;
    if (denominator < 0) negCnt++;
    if (negCnt == 1) {
      res.append('-');
    }
    long num = Math.abs((long)numerator);
    long den = Math.abs((long)denominator);

    if (num % den == 0) {
      res.append(num / den);
      return res.toString();
    }

    res.append(num/den);
    num = num % den;
    res.append('.');

    HashMap<Long,Integer> modIndex = new HashMap<Long,Integer>();
    modIndex.put(num, res.length());
    while(true) {
      num *= 10;

      if (num % den == 0) {
        if (num / den < 0) {
          System.out.println(num + " / " + den + " = " + num/den );
        }
        res.append(num/den);
        return res.toString();
      }
      else {
        if (num / den < 0) {
          System.out.println(num + " / " + den + " = " + num/den );
        }
        res.append(num/den);
        num = num % den;
      }

            // checkout if this mod showuped before
      if (modIndex.containsKey(num)) {
        res.insert((int)modIndex.get(num),'(');
        res.append(')');
        return res.toString();
      }
      else {
        modIndex.put(num,res.length());
      }
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.fractionToDecimal(-1,-2147483648));
  }
}
