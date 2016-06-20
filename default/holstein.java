
/*
ID: arviman1
LANG: JAVA
TASK: holstein
 */

import java.io.*;
import java.util.*;
public class holstein {	
	static String taskname = "holstein";
	static int v,f;
	static int[] vit;
	static int[][] feed;//feed, vit
	static int ret = 0;
	
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		int[] tvit;
		int res = -1, best = Integer.MAX_VALUE; //best = number of feeds. res = feed selection in bits
		for(int S = 1; S < (1<<f); ++S)
		{
			tvit = Arrays.copyOf(vit, v);
			
			int cnt = Integer.bitCount(S);
			for(int i = 0; i < f; ++i)
			{
				if((S & 1 << i) != 0)
				{
					for(int j = 0; j < v; ++j)
					{
						tvit[j] -= feed[i][j];						
					}						
				}
			}
			boolean ok = true;
			for(int j = 0; j < v; ++j)
				if(tvit[j] > 0)
				{
					ok = false; break;
				}
			if(ok && cnt <= best)
			{
				if(cnt < best)
				{
					best = cnt;
					res = S;
				}
				else
				{//if count is best, get feeds that are less in number
					for(int i = 0; i < f; ++i)
					{
						int sfeed = S & (1<<i);
						int bestfeed = res & (1<<i); 
						if(sfeed == bestfeed)continue;
						else if(sfeed > bestfeed)
						{
							best = S;
						}
						break;
					}
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(best);
		for(int i = 0; i < f; ++i)
		{
			if((res & (1 << i)) != 0)
				sb.append(" " + (i+1));
		}
		System.out.println(sb.toString());
		pout.println(sb.toString());
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		v = scr.nextInt();
		vit = new int[v];
		
		for(int i = 0; i < v; ++i)
		{
			vit[i] = scr.nextInt();			
		}
		f = scr.nextInt();
		feed = new int[f][v];
		for(int i = 0; i < f; ++i)
		{			
			for(int j = 0;j < v; ++j)
				feed[i][j] = scr.nextInt();
		}
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
