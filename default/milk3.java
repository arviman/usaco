
/*
ID: arviman1
LANG: JAVA
TASK: milk3
 */

import java.io.*;
import java.util.*;
public class milk3 {	
	static boolean[][][] vis = new boolean[21][21][21];
	//static int[] milks;
	static List<Integer> res = new ArrayList<Integer>();
	static int A=0, B=0, C=0;
	
	private static int[] change(int[] arr, int src, int dest)
	{		
		int scap, dcap;
		
		scap = src == 0 ? A : src == 1 ? B : C;
		dcap = dest == 0 ? A : dest == 1 ? B : C;
		//System.out.println(A + " " + B + " " + C);
		int rem = dcap - arr[dest];
		if(rem < 0) rem = 0;
		int toPour = Math.min(rem, arr[src]);
		//System.out.println(scap + " " + dcap);
		//System.out.println("before" + arr[src] + " " +  arr[dest]);
		arr[src] -= toPour;
		arr[dest] += toPour;
		//System.out.println("after" + arr[src] + " " + arr[dest]);
		return arr;
		
	}
	
	private static void dfs(int a, int b, int c)
	{
		if(vis[a][b][c])
			return;
		if(a == 0 && !res.contains(c))		
			res.add(c);
		
		//a to b
		vis[a][b][c] = true;
		int[] changed = change(new int[]{a,b,c}, 0, 1);
		dfs(changed[0], changed[1], changed[2] );
		changed = change(new int[]{a,b,c}, 0, 2);
		dfs(changed[0], changed[1], changed[2] );
		changed = change(new int[]{a,b,c}, 1, 0);
		dfs(changed[0], changed[1], changed[2] );
		changed = change(new int[]{a,b,c}, 1, 2);
		dfs(changed[0], changed[1], changed[2] );
		changed = change(new int[]{a,b,c}, 2, 0);
		dfs(changed[0], changed[1], changed[2] );
		changed = change(new int[]{a,b,c}, 2, 1);
		dfs(changed[0], changed[1], changed[2] );
		
	}
	
	private static List<Integer> go(int a, int b, int c)
	{
		dfs(0,0, c);
		
		return res;		
	}
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "milk3";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
	
		A = scr.nextInt(); B = scr.nextInt(); C = scr.nextInt();
		
		scr.close();
		//m=250;n=25;
		List<Integer> res = go(A,B,C);		//Call main
		Collections.sort(res);
		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));		
		
		int n = res.size();
		if(n > 0)
		{
			String line = "" + res.get(0);
			//System.out.println(line);
			pout.print(line);
		}
		for(int i = 1; i < res.size(); ++i)
		{
			String line = " " + res.get(i);
			pout.print(line);
			//System.out.println(line); 
		}
		pout.println();
		//System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
