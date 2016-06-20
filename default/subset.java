
/*
ID: arviman1
LANG: JAVA
TASK: subset
 */

import java.io.*;
import java.util.*;
public class subset {	
	static String taskname = "subset";
		
	
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		int n = scr.nextInt();
		int sum = (n*(n+1)) >> 1, half = sum >> 1;
		
		long[] dp = new long[sum+1];
		dp[0] = 1;
		long res = 0;
		
		if(sum % 2 == 0)
		{
			for(int number = 1; number <= n; number++)
			{
				for(int j = sum; j >= number; --j)
				{
					dp[j] += dp[j-number];
				}
			}
			res = dp[half] >> 1;
		}	
		
				
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		System.out.println(res);
		pout.println(res);
		
		pout.close();
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
