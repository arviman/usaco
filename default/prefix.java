
/*
ID: arviman1
LANG: JAVA
TASK: prefix
 */

import java.io.*;
import java.util.*;
public class prefix {	
	static String taskname = "prefix";
	static int n;
	static List<String> primitives;
	static StringBuilder sequence = new StringBuilder();
	
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		n = sequence.length();
		int sz = primitives.size();
		
		int[] dp = new int[n+11];//state where we consider best prefix size for sequence of length i
		int[] pl = new int[sz]; //length of primitives
		for(int i = 0 ; i<sz; ++i)
			pl[i] = primitives.get(i).length();
		int best = 0;
		for(int i = 1; i <= n; ++i)
		{			
			for(int j = 0; j < sz; ++j )
			{
				if(i-pl[j] < 0)continue;
				if(dp[i-pl[j]] == i-pl[j] && primitives.get(j).equals(sequence.substring(i-pl[j], i)))
				{
					best = Math.max(best, dp[i-pl[j]] + pl[j]);
					dp[i] = best;			
				}
			}
			
			
		}
		/*for(int i = 0; i <= n; ++i)
		{
			System.out.print(dp[i] + "(" + i + ") " );
			if(i%15==0)System.out.println();
		}*/
		int res = best;
		
		
		System.out.println(res);
		pout.println(res);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		primitives = new ArrayList<String>();
		while(true)
		{
			String prim = scr.next();
			if(!prim.equals("."))
				primitives.add(prim);
			else break;
		}
		 
		
		while(scr.hasNext())
		{
			sequence.append(scr.next());
		}
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
