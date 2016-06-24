/*
ID: arviman1
LANG: JAVA
TASK: humble
 */
import java.io.*;
import java.util.*;


public class humble {
    int K;
    int n;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
      K = in.nextInt();
      n = in.nextInt();
      long[] arr = new long[K];
      for(int i = 0 ;i<K;++i){
        arr[i] = in.nextInt();
      }
      Arrays.sort(arr);
      //long val = treesetMethod(arr);
      long val = humbleSeedStockingMethodOptimized(arr);
      out.println(val);
    }

  private long humbleSeedStockingMethodOptimized(long[] ladies) {
    int l = K;
    long[] nobles = new long[n+1];
    int[] lastBlessed = new int[l]; //which was the last noble blessed by the l-th lady
    nobles[0] = 1L;
    //fill all seeds here
    for(int i = 1 ; i <= n; ++i) {
      long previousNoble = nobles[i-1];
      long bestNoble = Long.MAX_VALUE;

      for(int ladyi = 0; ladyi < l; ++ladyi)
      for(int nobleToTry = lastBlessed[ladyi]; nobleToTry < i; ++nobleToTry){
        long newNoble = nobles[nobleToTry] * ladies[ladyi];
        if(newNoble > previousNoble) {
          if (newNoble < bestNoble) {
            bestNoble = newNoble;
          }
          break;
        }
        else{
          lastBlessed[ladyi] = nobleToTry;
        }
      }

      nobles[i] = bestNoble;
    }

    return nobles[n];
  }
/*
  private long humbleSeedStockingMethod(long[] arr) {
    long[] seeds = new long[n+1];
    long[] ptrs = new long[K];
    int base = 0;
    long highest = 1; //highest seed so far
    seeds[0] = 1L;
    //fill all seeds here
    for(int i = 1 ; i <= n; ++i){
      long val = Long.MAX_VALUE;

      //from base to i-1, try to go through all seeds that have been filled and calculate val
      for(int j = base ;j < i; ++j){
        long seed = seeds[j];
        long smallest = Long.MAX_VALUE;
        //multiple seed with primes and choose smallest product
        for(int k =K-1 ; k >= 0; --k){
          long tval = arr[k] * seed;
          if(tval <= highest)
            break;
          if(tval < smallest){
            smallest = tval;
          }
        }

        //if smallest of this is valid and best, mark it as best
        if(smallest == Long.MAX_VALUE){
          base = j+1;
        }
        else if(smallest < val)
          val = smallest;

      }
      if(val > highest) highest = val;
      seeds[i] = val;
    }

    return seeds[n];
  }

  private long treesetMethod(long[] arr){
    TreeSet<Long> set = new TreeSet<Long>();

    for(int i = 0 ;i<K;++i){
      set.add(arr[i]);
    }

    long higher = 0;
    long higherval = 0;

    int ctr = 0 ;
    long val = 0;
    while(ctr++ < n){
      set.remove(val = set.first());
      if(val > higherval){
        higherval = val;
        ++higher;
      }
      if(higher <= n)
        for(int i = 0 ; i<K; ++i){
          set.add(val*arr[i]);
        }
    }
    return val;
  }*/
}
