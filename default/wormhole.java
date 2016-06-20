/*
ID: arviman1
LANG: JAVA
TASK: wormhole
 */
import java.io.*;
import java.util.*;

public class wormhole {
  static String taskname = "wormhole";
  static int N;

  static int[] x;
  static int[] y;
  static int[] taken;

  static int[] next; //-1 if none

  public void solve(int testNumber, Scanner in, PrintWriter out) {

    N = in.nextInt();
    x = new int[N];
    y = new int[N];
    next = new int[N];
    for(int i = 0; i < N; ++i){
      x[i] = in.nextInt();
      y[i] = in.nextInt();
    }
    generateNext();
    taken = new int[N]; Arrays.fill(taken, -1);

    int res = recur(0);

    out.println(res);
    out.close();
  }
  private static void generateNext()
  {
    Arrays.fill(next, -1);
    for(int i = 0; i < N; ++i){
      int minx = Integer.MAX_VALUE;
      for(int j = 0; j < N; ++j) {
        if(i!=j && y[j]==y[i] && x[j] > x[i] && x[j] < minx)
        {
          minx = x[j];
          next[i] = j;
        }
      }
    }
  }



  private static boolean loopExists(int i)
  {
    int curi = i;
    int startx = x[i];
    int starty = y[i];
    boolean justTeleported = false;

    for(;;){
      if (!justTeleported) {
        curi = taken[curi];
        justTeleported = true;
      } else {
        justTeleported = false;
        curi = next[curi]; //otherwise go to next wormhole in line..if no wormhole, then return false
        if (curi == -1)
          return false;
        if ( x[curi] == startx && y[curi] == starty) {

          //System.out.println(curi + Arrays.toString(taken));
          return true;
        }
      }
    }
  }

  private static boolean loopExists() {
    for(int i = 0 ; i < N; ++i)
      if(taken[i] == -1)
        return false;

    for(int i = 0 ; i < N; ++i)    {
      if(loopExists(i)) //starting from ith wormhole
        return true;
    }
    return false;
  }

  private static int recur(int pos)
  {
    int res = 0;
    if(pos == N - 1)
    { //final check
      return loopExists() ?  1 : 0;
    }
    if(taken[pos] != -1) //has a teleport..nothing to set..go next
      return recur(pos+1);

    for(int i = pos+1; i < N; ++i)
    {
      if(taken[i] != -1)continue;
      taken[pos] = i; taken[i] = pos;
      res += recur(pos+1);
      taken[pos] = taken[i] = -1;
    }

    return res;
  }


  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();
    Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));
    //OUTPUT
    System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");

  }
}
