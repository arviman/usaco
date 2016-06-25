/*
ID: arviman1
LANG: JAVA
TASK: stamps
 */
import java.io.*;
import java.util.*;


public class stamps {
  int[] stamps;
  int n;
  int limit;
  boolean[][] mem ;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
      limit = in.nextInt();      n = in.nextInt();
      stamps = new int[n];
      for(int i = 0 ; i < n; ++i)
        stamps[i] = in.nextInt();
      Arrays.sort(stamps);
      int res = 0;
      res = dpSolve();
      out.println(res);
      out.close();
    }

  private int dpSolve(){
    int res=0;
    int maxPostageValue=10000*200+1;//max stamp val * max limit
    int[] minStamps = new int[maxPostageValue]; //contains...what's the miminum limit with which we can do i-th value
    Arrays.fill(minStamps, Integer.MAX_VALUE/2 - 1); //could be int.max -1 as well..just wtvr..so long as it's greater than max limit
    for(int stamp = 0 ; stamp < n; ++stamp){
      minStamps[stamps[stamp]] = 1;
    }
    out:
    for (int val = 0; val < maxPostageValue; ++val) {
      //#1.for(int numStamps = 1 ; numStamps  < limit; ++numStamps){
      for (int stamp = 0; stamp < n; ++stamp) { //for each stamp see if we can complete in limit
        int oldval = val - stamps[stamp];
        if (oldval >= 0) {
          int oldlimit = minStamps[oldval];
          //#2.if(limit - oldlimit >= 0){ //#1.(numStamps - oldlimit >= 0)
          if(oldlimit+1 < minStamps[val]) {
            minStamps[val] = oldlimit+1;
          }
          //#2.continue out;
        }
        else break;
      }

    }
    for(int i = 1 ; i < maxPostageValue; ++i){
      if(minStamps[i] > limit || minStamps[i] == -1){
        res = i-1; break;
      }
    }
    return res;
  }
}
