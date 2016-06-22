import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

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
      inputStream = new FileInputStream("inflate.in");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    OutputStream outputStream;
    try {
      outputStream = new FileOutputStream("inflate.out");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Scanner in = new Scanner(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    inflate solver = new inflate();
    solver.solve(1, in, out);
    out.close();
  }

  static class inflate {
    int[] pts;
    int[] min;
    int[] mem;
    int n;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
      int m = in.nextInt();
      n = in.nextInt();
      pts = new int[n];
      min = new int[n];
      mem = new int[m + 1];
      Arrays.fill(mem, -1);
      for (int i = 0; i < n; ++i) {
        pts[i] = in.nextInt();
        min[i] = in.nextInt();
      }
      //int res = go(m);
      int res = dp(m);
      out.println(res);
      out.close();
    }

    private int dp(int rem) {
      int[] res = new int[rem + 1];
      for (int i = 1; i <= rem; ++i) {
        for (int j = 0; j < n; ++j) {
          if (i - min[j] >= 0) {
            int val = res[i - min[j]] + pts[j];
            if (val > res[i])
              res[i] = val;
          }
        }
      }

      return res[rem];
    }

  }
}

