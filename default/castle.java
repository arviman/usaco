
/*
ID: arviman1
LANG: JAVA
TASK: castle
 */

import java.io.*;
import java.util.*;
public class castle {	
	static String taskname = "castle";
	static boolean[][][] mod; //r, c, (0-n 1-e 2-s 3-w)
	static boolean[][] marked;
	static int[][] id;
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int n,m;
	private static int dfs(int i, int j, int ccId)
	{
		id[i][j] = ccId;
		marked[i][j] = true;
		int area = 1;
		
			
		for(int d = 0; d<4; ++d)
		{
			int ni = dy[d] + i;
			int nj = dx[d] + j;
			if(ni >= 0 && ni < n && nj >= 0 && nj < m)
			{				
				 if(!marked[ni][nj] && mod[i][j][d])
				 {
					 area += dfs(ni,nj,ccId);
				 }
			}
		}
		return area;
	}
	
	private static void go(int row, int col)  throws IOException
	{
		
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		marked = new boolean[row][col];
		id = new int[row][col];
		Map<Integer, Integer> map = new HashMap<Integer,Integer>(); //room, area
		int cnt = 1;
		int bestArea = -1;
		int bestcnt = -1;
		for(int i = 0 ;i < row; ++i)
			for(int j = 0; j < col ; ++j)
			{
				if(!marked[i][j])
				{
					int area = dfs(i,j, cnt);
					if(area > bestArea)
					{
						bestArea = area;
						bestcnt = cnt;
					}
					map.put(cnt, area);					
					cnt++;
				}
				
			}
		
		boolean[][] adjC = new boolean[cnt+1][cnt+1]; //mark adjacent rooms
		for(int i = 0 ;i < row; ++i)
		for(int j = 0; j < col ; ++j)
		{
			int ci = id[i][j];
			for(int d = 0; d<2; ++d)
			{
				int ni = dy[d] + i;
				int nj = dx[d] + j;
				if(ni >= 0 && ni < n && nj >= 0 && nj < m)
				{			
					int cj = id[ni][nj];
					adjC[ci][cj] = adjC[cj][ci] = true;
				}
			}
		}
		
		int bestAreas = -1;
		int besti = -1,bestj=-1, bestd=-1;
		for(int j = 0; j < col; ++j)
		for(int i = row-1; i >= 0; --i)
		{
			int ci = id[i][j];
			int areai = map.get(ci);
			for(int d = 0; d<4; ++d)
			{
				int ni = dy[d] + i;
				int nj = dx[d] + j;
				if(ni >= 0 && ni < n && nj >= 0 && nj < m && !mod[i][j][d])
				{
					int cj = id[ni][nj];
					if(cj == ci)continue;
					int curArea = areai + map.get(cj);
					if(curArea > bestAreas)
					{
						bestAreas = curArea;
						besti = i; bestj = j; bestd = d;
					}					
				}	
			}
		}
		
		for(int i = 0 ;i < row; ++i)
		{
			for(int j = 0; j < col ; ++j)
			{				
				System.out.print(id[i][j] + " " );
			}
			System.out.println();
		}
		
		char[] dtext = new char[] { 'N','E','S','W' };
		
		--cnt;
		System.out.println(cnt);
		System.out.println(bestArea);
		System.out.println(bestAreas);
		System.out.println((besti + 1) + " " + (bestj + 1) +  " " + dtext[bestd]);
		pout.println(cnt);
		pout.println(bestArea);
		pout.println(bestAreas);
		pout.println((besti + 1) + " " + (bestj + 1) +  " " + dtext[bestd]);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		m = scr.nextInt(); n = scr.nextInt();
		mod = new boolean[n][m][4];
		for(int i = 0; i < n; ++i)
			for(int j = 0;j < m; ++j)
			{
				int val = scr.nextInt();
				mod[i][j][0] = (val & 2) == 0;
				mod[i][j][1] = (val & 4) == 0;
				mod[i][j][2] = (val & 8) == 0;
				mod[i][j][3] = (val & 1) == 0;				
			}
		
		scr.close();

		go(n, m);		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
