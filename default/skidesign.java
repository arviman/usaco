
/*
ID: arviman1
LANG: JAVA
TASK: skidesign
 */
import java.io.*;
import java.util.*;


public class skidesign {
  static int N;
  int[] arr;
  public void solve(int testNumber, Scanner in, PrintWriter out) {
    N = in.nextInt();
    arr = new int[N];
    for(int i = 0 ; i < N; ++i)
    {
      arr[i] = in.nextInt();
    }
    int res = go();
    out.println(res);
    out.close();
  }


  private int go() {
    int cost = Integer.MAX_VALUE ;
    int[] mm = getMinMax(); //0-1st min, 1- 2nd min, 2-1st max, 3-2nd max

    int low = arr[mm[0]];int up = arr[mm[2]];
    for(int m  = low; m <= up; ++m)
    {
      int c = 0;
      for(int i = 0 ; i < N; ++i)
      {
        if(arr[i] < (m-17))        {
          c += (arr[i]-m+17) * (arr[i]-m+17);
        }
        else if (arr[i] > m){
          c += (arr[i] - m) * (arr[i]-m);
        }
      }
      if(c < cost)
        cost = c;
    }
    return cost;
  }

  private int[] getMinMax() {
    int min1 = Integer.MAX_VALUE;int min2 = Integer.MAX_VALUE;
    int max1 = Integer.MIN_VALUE;int max2 = Integer.MAX_VALUE;
    int min1i=0, min2i=0, max1i=0, max2i=0;
    for(int i = 0 ; i < N; ++i)
    {
      if(min1 > arr[i]) {
        min2i = min1i;
        min2 = min1;
        min1 = arr[i];
        min1i = i;
      }
      else if(min2 > arr[i])
      {
        min2i = i;
        min2 = arr[i];
      }
      if(max1 < arr[i]){
        max2i = max1i;
        max2 = max1;
        max1 = arr[i];
        max1i = i;
      }
      else if(max2 < arr[i])
      {
        max2i = i;
        max2 = arr[i];
      }
    }
    return new int[]{min1i,min2i,max1i,max2i};
  }
}
