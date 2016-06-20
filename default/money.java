
/*
ID: arviman1
LANG: JAVA
TASK: money
 */

import java.io.*;
import java.util.*;

import javax.sound.midi.Soundbank;
public class money {	
	static String taskname = "money";
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int v = in.nextInt(), N = in.nextInt();
        int[] coins = new int[v];
        for (int i = 0; i < v; i++) {
            coins[i] = in.nextInt();
        }
        Arrays.sort(coins);
        long[] dp = new long[N+1];
        dp[0] = 1;
        for (int i = 0; i < v; ++i)
        {
        	int coin = coins[i];
        	for(int j = coin; j <= N; ++j)
        	{
        		dp[j] += dp[j-coin];
        	}
        }
        
        //System.out.println("Arrays.deepToString(dp) = " + Arrays.deepToString(dp));
        out.println(dp[N]);
    }
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream("money.out");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		PrintWriter out = new PrintWriter(outputStream);
		money solver = new money();
		solver.solve(1, scr, out);
		out.close();
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
