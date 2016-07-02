/*
ID: arviman1
LANG: JAVA
TASK: fact4
 */
import java.io.*;
import java.util.*;


public class fact4 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {

      int n = in.nextInt();
      int res = 1;
      for(int i = 2; i <=n ; ++i){
        String sz = i+"";
        res *= i;
        while(res%10 == 0){
          res /= 10;
        }

        int mod = 10;
        int m = sz.length();
        for(int j = 0 ; j < m; ++j)
          mod*=10;

        res = res%mod;
      }
      out.println(res%10);
    }
}
