import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
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
    ArrayList<ArrayList<Node>> adj;
    boolean[] vis;
    int[] dist;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
      n = in.nextInt();
      p = in.nextInt();
      c = in.nextInt();
      cowCountAtPasture = new int[p];
      int[][] edges = new int[p][p];
      adj = new ArrayList<ArrayList<Node>>(p);
      for (int i = 0; i < p; ++i) {
        adj.add(i, new ArrayList<Node>());
      }
      for (int i = 0; i < n; ++i) {
        cowCountAtPasture[in.nextInt() - 1]++;
      }
      for (int i = 0; i < c; ++i) {
        int from = in.nextInt() - 1;
        int to = in.nextInt() - 1;
        int dist = in.nextInt();
        adj.get(from).add(new Node(to, dist));
        adj.get(to).add(new Node(from, dist));
      }
      vis = new boolean[p];
      dist = new int[p];
      //setup done
      long bestDist = Long.MAX_VALUE;
      for (int i = 0; i < p; ++i) {
        long curDist = getDistWithSourceAt(i);
        if (curDist < bestDist) bestDist = curDist;
      }
      out.println(bestDist);
      out.close();
      return;
    }

    private long getDistWithSourceAt(int src) {

      Arrays.fill(vis, false);
      Arrays.fill(dist, Integer.MAX_VALUE);
      ArrayList<Integer> q = new ArrayList<Integer>();
      dist[src] = 0;
      for (int i = 0; i < p; ++i) {
        q.add(i);
      }
      while (!q.isEmpty()) {
        int qs = q.size();
        int leastUnvisited = -1;
        int leastDist = Integer.MAX_VALUE;
        for (int i = 0; i < qs; ++i) {
          int indx = q.get(i);
          if (dist[indx] < leastDist) {
            leastUnvisited = i;
            leastDist = dist[indx];
          }
        }
        if (leastUnvisited == -1)
          break;
        int t = q.remove(leastUnvisited);
        vis[t] = true;
        for (Node nd : adj.get(t))
          if (!vis[nd.to])
            dist[nd.to] = Math.min(dist[nd.to], dist[t] + nd.distance);
      }
      long totalDist = 0;
      for (int i = 0; i < p; ++i) {
        if (cowCountAtPasture[i] == 0) continue;
        totalDist += cowCountAtPasture[i] * dist[i];
      }
      return totalDist;
    }

    class Node implements Comparable<Node> {
      int distance;
      int to;

      Node(int To, int Dist) {
        to = To;
        distance = Dist;
      }


      public int compareTo(Node o) {
        if (this.distance < o.distance) return -1;
        if (this.distance > o.distance) return 1;
        return 0;
      }

    }

  }
}

