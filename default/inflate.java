/*
ID: arviman1
LANG: JAVA
TASK: inflate
 */
import java.io.*;
import java.util.*;


public class inflate {
  int[] pts;
  int[] min;
  int[] mem;
  int n;
  public void solve(int testNumber, Scanner in, PrintWriter out) {
    int m = in.nextInt();
    n = in.nextInt();
    pts = new int[n];
    min = new int[n];
    mem = new int[m+1];
    Arrays.fill(mem,-1);
    for(int i = 0 ;i  < n; ++i){
      pts[i] = in.nextInt(); min[i] = in.nextInt();
    }
    //int res = go(m);
    int res = dp(m);
    out.println(res);
    out.close();
  }

  private int dp(int rem) {
    int[] res = new int[rem+1];
    for(int i = 1; i <= rem; ++i){
      for(int j = 0 ; j < n; ++j){
        if(i-min[j]>=0){
          int val = res[i-min[j]] + pts[j];
          if(val > res[i])
            res[i] = val;
        }
      }
    }

    return res[rem];
  }

  int go(int rem){
    if(rem<=0)return 0;
    if(mem[rem] != -1)return mem[rem];

    int max = 0;
    for(int i = 0 ; i < n; ++i){
      int remi = rem - min[i];
      if(remi >= 0) {
        int val = pts[i] + go(rem-min[i]);
        if(val > max)
          max = val;
      }
    }
    mem[rem] = max;
    return max;
  }
}
