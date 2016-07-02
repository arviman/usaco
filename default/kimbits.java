/*
ID: arviman1
LANG: JAVA
TASK: %TaskName%
 */
import java.io.*;
import java.util.*;


public class kimbits {

  PrintWriter out;
  int[][] numdp;
  public void solve(int testNumber, Scanner in, PrintWriter out) {
      this.out = out;
      byte N = in.nextByte(); byte L = in.nextByte(); long I = in.nextLong();
      numdp = new int[N+1][L+1];  //contains what's the number of items that meets N bit-L limit for 1 bits constraint


      for(int i = 0 ; i <= N; ++i){
        numdp[i][0] = 1;
      }
      for(int i = 0 ; i <= L; ++i){
        numdp[0][i] = 1;
      }

      for(int i = 1 ; i <= N; ++i){
        for(int j = 1 ; j <= L; ++j){
          numdp[i][j] = numdp[i-1][j-1] + numdp[i-1][j];
        }
      }

      printoutput(N, L, I-1);
      out.println();

      out.close();

    }

  private void printoutput( int n, int l, long i ) {
    if(n==0)return;
    if(numdp[n-1][l] > i ){
      out.print("0");
      printoutput( n-1, l, i);
    }
    else{
      out.print("1");
      printoutput( n-1, l-1, i-numdp[n-1][l]);
    }
  }

}
