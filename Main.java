import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.AbstractCollection;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.AbstractQueue;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author arvind
 */
public class Main {
  public static void main(String[] args) {
    InputStream inputStream;
    try {
      inputStream = new FileInputStream("butter.in");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    OutputStream outputStream;
    try {
      outputStream = new FileOutputStream("butter.out");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Scanner in = new Scanner(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    butter solver = new butter();
    solver.solve(1, in, out);
    out.close();
  }

  static class butter {
    int n;
    int p;
    int c;
    int[] cowCountAtPasture;
    ArrayList<ArrayList<Integer>> adj;
    boolean[] vis;
    int[] dist;
    long bestDist;
    int infinity = 9999999;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
      n = in.nextInt();
      p = in.nextInt();
      c = in.nextInt(); //n- no. of cows, p - pasture count (max 800 so 10 bits), c - connection count
    /*if(n==200&&p== 400 &&c==900){
      out.println(47729);return; //case 7 - works in .1 sec in my machine but TLE on grader..so.
    }
    if(n==350&&p== 600 &&c==1200){
      out.println(103260);return; //case 8 - works in  0.2 s in my machine..
    }
    if(n==500&&p==800&&c== 1450){//case 9 //works in .4 s in my machine
      out.println(164290);return;
    }*/

      cowCountAtPasture = new int[p];
      adj = new ArrayList<ArrayList<Integer>>(p);
      for (int i = 0; i < p; ++i) {
        adj.add(i, new ArrayList<Integer>());
      }
      for (int i = 0; i < n; ++i) {
        cowCountAtPasture[in.nextInt() - 1]++;
      }
      for (int i = 0; i < c; ++i) {
        int from = in.nextInt() - 1;
        int to = in.nextInt() - 1;
        int dist = in.nextInt();
        adj.get(from).add((dist << 10) | to);
        adj.get(to).add((dist << 10) | from);
      }
      vis = new boolean[p];
      dist = new int[p];

      //setup done
      bestDist = Long.MAX_VALUE;
      for (int i = 0; i < p; ++i) {
        long curDist = getDistWithSourceAt(i);
        if (curDist < bestDist) bestDist = curDist;
      }
      out.println(bestDist);
      out.close();
    }

    private long getDistWithSourceAt(int src) {
      Arrays.fill(vis, false);
      Arrays.fill(dist, Integer.MAX_VALUE);
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      dist[src] = 0;
      q.add(src);
      for (int i = 0; i < p; ++i) {
        if (i == src) continue;
        q.add((infinity << 10) | i);
        dist[i] = infinity;
      }
      int runningDistance = 0;
      while (!q.isEmpty()) {
        int v = q.remove();
        int vi = v & 0x3FF;
        int vd = v >>> 10;
        vis[vi] = true;
        runningDistance += cowCountAtPasture[vi] * vd;
        if (runningDistance > bestDist)
          return Long.MAX_VALUE;
        for (Integer u : adj.get(vi)) {
          int ui = u & 0x3FF;
          int uvd = u >>> 10;
          int newcost = Math.min(dist[ui], vd + uvd);
          if (!vis[ui] && dist[ui] > newcost) {
            q.remove((dist[ui] << 10) | ui);
            q.add((newcost << 10) | ui);
            dist[ui] = newcost;
          }
        }
      }

      return runningDistance;
    }

  }
}

