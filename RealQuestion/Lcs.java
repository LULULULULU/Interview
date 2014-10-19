public class Lcs {
  public enum Direction {
    NONE, UP, LEFT, UPLEFT
  }

  // LCS problem
  // giving string X[1~n]
  // giving string Y[1~m]
  // get Z[0~k] which is LCS

  // 2-dimensional array m[n+1,m+1]
  // m[i,j] stands for the length of LCS of X[1~i] and Y[1~j]
  // m[i,j] = 0                 if i=0 || j=0
  //        = m[i-1,j-1] + 1    if X[i] == Y[j]
  //        = MAX{ m[i-1,j],    if X[i] != Y[j]
  //               m[i,j-1]
  //             }

  public class Value {
    public Direction direction;
    public int size;

    public Value() {
      this.direction = Direction.NONE;
      this.size = 0;
    }
  }

  public String getLCS(String a, String b) {
    Value[][] m = new Value[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length() ; i++) {
      m[i][0] = new Value();
    }

    for (int j = 0; j <= b.length() ; j++ ) {
      m[0][j] = new Value();
    }

    for (int i = 1; i <= a.length() ; i++ ) {
      for (int j = 1; j <= b.length() ; j++ ) {
        if(a.charAt(i-1) == b.charAt(j-1)) {    // Notice the i-1 j-1 here
          m[i][j] = new Value();
          m[i][j].size = m[i-1][j-1].size + 1;
          m[i][j].direction = Direction.UPLEFT;
        }
        else {
          if (m[i-1][j].size >= m[i][j-1].size) {
            m[i][j] = new Value();
            m[i][j].size = m[i-1][j].size;
            m[i][j].direction = Direction.UP;
          }
          else {
            m[i][j] = new Value();
            m[i][j].size = m[i][j-1].size;
            m[i][j].direction = Direction.LEFT;
          }
        }
      }
    }

    StringBuffer sb = new StringBuffer();
    int i = a.length();
    int j = b.length();
    Value v = m[i][j];
    int count = 0;
    while(v.direction != Direction.NONE)
    {
      switch (v.direction) {
        case UPLEFT:
          sb.append(a.charAt(i-1));   // StringBuffer append(char, boolean, char[], ....)
                                      // Notice the i-1 here.
          v = m[--i][--j];
          count++;
          break;
        case UP:
          v = m[--i][j];
          break;
        case LEFT:
          v = m[i][--j];
          break;
      }
    }

    System.out.println(sb.reverse());  // StringBuffer reverse()
    System.out.println(count);

    return sb.toString();
  }

  public static void main(String[] args) {
    String a = "asjdfkljasl;kdfj;kajgl;ksdjfl;kksdghaoaisucnoi uviopasodiuacopsidfuvnopaiudpo ifcfundafiovaunpodiscufnpoiavdunapoisduvoiauscpoiun vopwiuapoitgiaohfopviehwnpuighpoiajmoidjnopciwutpoiqnckasjcf piogrenptvnuwepoicmoiqunjsdg;lkhasldkf;jl;akjf;lksdjh;a";
    String b = "akljldksjghl;ajdslfk;jl;akjgcoiqjewopicuv npqoiwvcjqmoiewjqvnpoiquwecompiejwqoivnuqowpeicnpiqweumopxiweuinoveuwqcmopiequwoirnuvoiwqeu prowiqucnropqiwecmxiweuinopvqweu cvopiqweurcnoiwquecmoprxiuqwn ioprwueniovwepcimopulskdfj;alksdgja;lskdjf;lkasdhjg;lkasdjflka";
    // String a = "ajdsfasasdf";
    // String b = "glkadsflaksj";

    Lcs lcs = new Lcs();
    lcs.getLCS(a,b);
    lcs.getLCS("","");
  }



}
