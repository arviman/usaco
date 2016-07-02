import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
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
      inputStream = new FileInputStream("ratios.in");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    OutputStream outputStream;
    try {
      outputStream = new FileOutputStream("ratios.out");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Scanner in = new Scanner(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    ratios solver = new ratios();
    solver.solve(1, in, out);
    out.close();
  }

  static class ratios {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
      int tB = in.nextInt();
      int tO = in.nextInt();
      int tW = in.nextInt();

      int[] b = new int[3];
      int[] o = new int[3];
      int[] w = new int[3];
      int[][] m = new int[3][3];
      for (int i = 0; i < 9; ++i) {
        switch (i % 3) {
          case 0:
            b[i / 3] = in.nextInt();
            break;
          case 1:
            o[i / 3] = in.nextInt();
            break;
          case 2:
            w[i / 3] = in.nextInt();
            break;

        }
      }
      int B, O, W;
      int minSum = Integer.MAX_VALUE;
      int besta1 = 0, besta2 = 0, besta3 = 0, bestMultiple = -1;

      for (int a1 = 0; a1 < 100; ++a1)
        for (int a2 = 0; a2 < 100; ++a2) {
          for (int a3 = 0; a3 < 100; ++a3) {
            B = a1 * b[0] + a2 * b[1] + a3 * b[2];
            O = a1 * o[0] + a2 * o[1] + a3 * o[2];
            W = a1 * w[0] + a2 * w[1] + a3 * w[2];

            int valB = tB == 0 || B == 0 ? (B == 0 && tB == 0) ? 0 : -1 : B % tB == 0 ? B / tB : -1;
            int valO = tO == 0 || O == 0 ? (O == 0 && tB == 0) ? 0 : -1 : O % tO == 0 ? O / tO : -1;
            int valW = tW == 0 || W == 0 ? (W == 0 && tW == 0) ? 0 : -1 : W % tW == 0 ? W / tW : -1;

            if (valB != -1 && valO != -1 && valW != -1) {
              boolean acceptableBO = valB == 0 || valO == 0 || valB == valO;
              boolean acceptableOW = valW == 0 || valO == 0 || valW == valO;
              boolean acceptableBW = valW == 0 || valB == 0 || valW == valB;

              if (acceptableBO && acceptableOW && acceptableBW) {
                int sum = a1 + a1 + a3;
                if (sum < minSum) {
                  minSum = sum;
                  besta1 = a1;
                  besta2 = a2;
                  besta3 = a3;
                  bestMultiple = valB;
                }
              }
            }
          }
        }
      if (bestMultiple == -1) {
        out.println("NONE");
      } else out.println(besta1 + " " + besta2 + " " + besta3 + " " + bestMultiple);
      out.close();
    }

  }
}

