
/*
ID: arviman1
LANG: JAVA
TASK: hamming
 */

import java.io.*;
import java.util.*;
public class hamming {	
	static String taskname = "hamming";
	static int n, b, d, max;
	static boolean[][] adj;
	static boolean[] isok; //hamming
	static boolean[] visited;
	private static List<Integer> dfs(int last, List<Integer> list)
	{
		visited[last] = true;
		
		boolean[] tisok = Arrays.copyOf(isok, max);
		int cnt = 1;		
		
		for(int i = 0 ; i < max; ++i)if(i!=last)
		{
			if(isok[i])
			{
				if(!adj[i][last])
				  isok[i] = false;
				else
					cnt++;
			}			
		}

		if(list.size() == n)
		{	
			if(cnt >= n)							
				return list;
			
			else return new ArrayList<Integer>();
		}
		
		for(int i = 0; i < max; ++i)
		{
			if(isok[i] && !visited[i])
			{
				List<Integer> newlist = new ArrayList<Integer>(list);
				newlist.add(i);
				List<Integer> res = dfs(i,newlist);
				if(!res.isEmpty())
					return res;
			}
		}
		
		isok = Arrays.copyOf(tisok, max);
		return new ArrayList<Integer>();
	}
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		visited = new boolean[max];
		isok =  new boolean[max];
		for(int i = 0; i < max ;++i)
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(i);
			Arrays.fill(visited, false);
			Arrays.fill(isok, false);
			isok[i] = true;
			for(int j = 0; j < max; ++j)
				if(adj[i][j])
					isok[j] = true;
			list = dfs(i, list);
			if(!list.isEmpty()){
				
				int listn = list.size();
				for(int j = 0; j < listn; ++j)
				{
					if(j%10 != 0)
					{
						System.out.print(" ");
						pout.print(" ");
					}
					
					if(j > 0 && j%10 == 0)
					{
						pout.println();
						System.out.println();
					}
					pout.print(list.get(j));
					System.out.print(list.get(j));
					
				}
				pout.println();
				System.out.println();
				
				break;
			}
		}
		
		
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		n = scr.nextInt(); b = scr.nextInt(); d = scr.nextInt();
		int twopb = 1<<b;
		max = twopb;
		adj = new boolean[twopb][twopb];
		for(int i = 0 ; i < twopb; ++i)
		{
			for(int j = i + 1; j < twopb; ++j )
			{
				int cnt = 0;
				for(int k = 0; k < b; ++k)
				{
					if((i & (1<<k)) != (j & (1<<k)))
						cnt++;
				}
				
				if(cnt >= d)
					adj[i][j] = adj[j][i] = true;
			}
		}
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
