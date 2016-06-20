
/*
ID: arviman1
LANG: JAVA
TASK: nocows
 */

import java.io.*;
import java.util.*;

import javax.sound.midi.Soundbank;
public class nocows {	
	static String taskname = "nocows";
	static int N;
	static int K;	
	static int MOD = 9901;
	static int[][] ways;
	
	private static void solveLess(int n, int k)
	{
		for (int depth = 1; depth <= k; depth++)
	    {
	        ways[1][depth] = 1;
	        for (int n1 = 3; n1 <= n; n1 += 2)
	        {
	            for (int i = 1; i <= n1 - 2; i++)
	            {
	                ways[n1][depth] += ways[i][depth-1] * ways[n1-1-i][depth-1];
	                ways[n1][depth] %= MOD;
	            }
	        }
	    }
		
	}
	
	private static int solve(int n, int k)
	{		
		if(ways[n][k] != -1)
			return ways[n][k];
				
		if(k==0)return ways[n][k] = 0;
		if(n%2 == 0)
			return ways[n][k] = 0;
		if(n==1)return ways[n][k] = k == 1?1:0;
		if(k==1)return ways[n][k] = n == 1?1:0;
		if(n == (2<<(k-1))-1)		//full tree	for all k > 0
			return ways[n][k] = 1;
				
		if(n < ((k<<1)-1))//n is unable to reach k depth if n < 2k-1
			return ways[n][k] = 0;
		
		int way=0;
		//System.out.println("solving " + n + " and " + k);
		//int halfn = (n+1)/2; //5->2
		//for(int n1 = 1; n1 < halfn; n1+=2)
		for(int n1 = n-2; n1 >= 1; n1-=2)
		{//1
			int n2 = n-1-n1;//3
			//System.out.println(n1 + " - " + n2 +" out of " + n);
			for(int depth = 1; depth < k-1; ++depth)
			{
				
				way += (solve(n1, k-1)*solve(n2, depth));
				way %= 9901;
				
				way += (solve(n2, k-1)*solve(n1, depth));
				way %= 9901;
			}
			
			way += (solve(n1,k-1)*solve(n2,k-1));			
			way %= 9901;
			
		}
		
		return ways[n][k] = way;
		
	}
	 
	
	private static void go()  throws IOException
	{	
		int n = N, k = K;
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		ways = new int[n+1][k+1]; //depth...number of nodes
		//for(int i = 0 ;i <=n; ++i)
			//Arrays.fill(ways[i],-1);
		//ways[1][1] = 1;
		
		solveLess(n,k);
		int res = (ways[n][k] - ways[n][k-1] + MOD) %MOD;
		/*System.out.println("f(5,3) " + ways[5][3]);
		System.out.println("f(3,2) " + ways[3][2]);
		System.out.println("f(9,3) " + ways[9][4]);
		System.out.println("f(7,3) " + ways[7][3]);
		System.out.println("f(1,1) " + ways[1][1]);*/
		//System.out.println(Arrays.deepToString(ways));
		System.out.println(res);
		pout.println(res);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		N = scr.nextInt();
		K = scr.nextInt();
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
