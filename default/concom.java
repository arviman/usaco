
/*
ID: arviman1
LANG: JAVA
TASK: concom
 */

import java.io.*;
import java.util.*;

import javax.sound.midi.Soundbank;
public class concom {	
	static String taskname = "concom";	
	
	static int[][] owns;
	static int[][] perc;
	static boolean[][] vis;
	
	private static void go(Scanner in, PrintWriter out)
	{
		int n = in.nextInt();
		
		owns = new int[101][101];
		perc = new int[101][101];
		vis = new boolean[101][101];
		for(int i = 0; i < n; ++i)
		{
			int a = in.nextInt();
			int b = in.nextInt(); 
			owns[a][b] = perc[a][b] = in.nextInt();			
		}
		//System.out.println(perc[1][30]);
		int go = 1;
		while(go-- > 0)
		for(int i = 0; i < 101; ++i)
		{
			for(int j = 0; j < 101; ++j)if(i!=j)
			{
				if(perc[i][j] > 50 && !vis[i][j] && (vis[i][j] = true))
				{
					for(int k = 0; k < 101; ++k)
					{
						if(k == i || k == j )continue;
						if(owns[j][k] > 0)
						{
							perc[i][k] += owns[j][k];
							go = 1; //keep doing outer loop
						}
					}										
				}
			}
		}
		
		for(int i = 0; i < 101; ++i)
		{
			for(int j = 0; j < 101; ++j)if(i!=j && perc[i][j] > 50)
			{
				System.out.println(i + " " + j);
				out.println(i + " " + j);
			}
		}
					
	}
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream(taskname + ".out");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		PrintWriter out = new PrintWriter(outputStream);
		go(scr, out);
		out.close();
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
