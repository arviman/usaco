
/*
ID: arviman1
LANG: JAVA
TASK: ariprog
 */

import java.io.*;
import java.util.*;
class Result implements Comparable<Result>
{	
	int sequenceDiff;
	int startSequence;
	public Result(int seqDiff, int startSequence)
	{
		this.sequenceDiff = seqDiff;
		this.startSequence = startSequence;
	}
	@Override
	public int compareTo(Result b) {
		if(this.sequenceDiff != b.sequenceDiff)
		  return this.sequenceDiff - b.sequenceDiff;
		if(this.startSequence != b.startSequence)
		  return this.startSequence - b.startSequence;
		  
		return 0;
	}
}
public class ariprog {	
	static boolean[] ok = new boolean[250*250*2 + 1];
	
	private static List<Result> go(int N, int m)
	{
		List<Integer> sq = new ArrayList<Integer>();		
		List<Result> res = new ArrayList<Result>();
		int max = -1;
		for(int a = 0; a <= m; ++a)
			for(int b = a; b <= m; ++b)
			{
				int val = a*a + b*b; 
				if(ok[val])continue;
				sq.add(val);
				ok[val] = true;
				if(val > max) max = val;
			}		
		N--;
		
		for(int i : sq)
		{					
			
			for(int b = 1; (i+N*b) <= max ;++b)
			{
				int val = i;
				for(int n = 1; n <= N; ++n )
				{					
					val += b;
					
					if(val <= max && ok[val])
					{
						if(n==N)
							res.add(new Result(b, i));					
					}
					else
						break;
				}
			}
		}
		return res;
			
	}
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "ariprog";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
	
		int n = scr.nextInt(); int m = scr.nextInt();
		scr.close();
		//m=250;n=25;
		List<Result> res = go(n,m);		//Call main
		Collections.sort(res);
		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));	
		
		if(res == null || res.isEmpty())
		{
			pout.println("NONE");
			//System.out.println("NONE");
		}
		else
		for(Result r : res)
		{
			String line = r.startSequence + " " + r.sequenceDiff;
			pout.println(line);
			System.out.println(line); 
		}
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
