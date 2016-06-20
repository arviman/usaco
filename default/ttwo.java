
/*
ID: arviman1
LANG: JAVA
TASK: ttwo
 */

import java.io.*;
import java.util.*;

import javax.sound.midi.Soundbank;
public class ttwo {	
	
	class Pos
	{
		public int x; public int y; public int time;
		public Pos(int _x, int _y, int _time)
		{
			x = _x; y = _y; time = _time;
		}
	}
	static String taskname = "ttwo";	
	
	static char[][] map = new char[10][10];
	//to show the first moment that a cow is reached. 
	//
	
	static int[][] cost = new int[10][10]; //x,y | Distances for farmer
	static int[] dx = new int[] {0, 1, 0, -1 };
	static int[] dy = new int[] {-1, 0, 1, 0 };
	class Node
	{		
		public Pos pos;
		int dir;
		public Node(Pos p, int dir)
		{
			this.pos = p;this.dir = dir;
		}
	}
	
	private static List<Pos> getPositions(int startx, int starty)
	{
		int time = 0;
		ttwo _this = new ttwo();
		int cd = 0;//cow direction
		List<Pos> cowPositions = new ArrayList<Pos>();
		cowPositions.add(_this.new Pos(startx, starty, 0));
		int nx = startx, ny = starty;
		for(int i = 0; i < 500; ++i)
		{			
			time++;			
			int ox = nx, oy = ny;
			switch(cd)			
			{
				case 0: //North
					ny--;break;
				case 1: //East
					nx++; break;
				case 2: //South
					ny++;break;
				case 3: //West
					nx--; break;				
			}
			if(nx >= 0 && nx < 10 && ny >= 0 && ny < 10 && map[ny][nx] != '*')
				cowPositions.add(_this.new Pos(nx, ny, time));
			else
			{
				nx = ox; ny = oy;
				cd = (cd+1)%4;	
				cowPositions.add(_this.new Pos(nx, ny, time));	
			}			
		}
		return cowPositions;
	}
	
	private static void go(Scanner in, PrintWriter out)
	{
		int cx=0,cy=0,fx=0,fy=0;
		ttwo _this = new ttwo();
		for(int i = 0; i < 10; ++i)
		{
			String line = in.next();
			for(int j = 0; j < 10; ++j)
			{
				char ch = line.charAt(j);
				map[i][j] = ch;
				if(map[i][j] == 'C')
				{
					cy = i; cx = j; 
				}
				else if(map[i][j] == 'F')
				{
					fy = i; fx = j; 
				}
			}			
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		//simulate cow distances
		
		
		//calculateFarmerDistances(fx,fy);
		List<Pos> cowPosition = getPositions(cx, cy);
		List<Pos> farmerPositions = getPositions(fx, fy);
		
		boolean found = false;
		int szi = cowPosition.size(), szj = farmerPositions.size();
		outer: for (int i = 0 ; i < szi; ++i)
		{
			Pos cp = cowPosition.get(i);
			for(int j = 0; j < szj; ++j)
			{
				Pos fp = farmerPositions.get(j);
				if(cp.time == fp.time && cp.x == fp.x && cp.y == fp.y)
				{
					found = true;
					out.println(cp.time);
					System.out.println(cp.time); break outer;
				}
			}
				
		}
		/*for(Pos cowpos : cowPositions)
		{			//optimum calculation
			if(cost[cowpos.y][cowpos.x] <= cowpos.time)
			{
				found = true;
				//System.out.println("farmer gets to " + cowpos.x + "," + cowpos.y + " at " + cost[cowpos.x][cowpos.y]);
				out.println(cowpos.time);
				System.out.println(cowpos.time); break;
			}
		}*/
		if(!found)
		{
			out.println("0");
			System.out.println("0");
		}
	}
	
	/*private static void calculateFarmerDistances(int fx, int fy) {
		//OPTIMAL WAY OF GETTING THE COW CUZ THE FARMER IN THE PROBLEM IS A DUMBASS
		Queue<Node> q = new ArrayDeque<Node>();
		ttwo _this = new ttwo();
		q.add(_this.new Node(_this.new Pos(fy, fx, 0), 0));
		while(!q.isEmpty())
		{
			Node cur = q.remove();
			if(cur.pos.time < cost[cur.pos.y][cur.pos.x])
				cost[cur.pos.y][cur.pos.x] = cur.pos.time;
			for(int i = 0 ; i < 4; ++i)
			{
				int nx = cur.pos.x + dx[i];
				int ny = cur.pos.y + dy[i];				
				int rotateCost = (cur.dir <= i ? 0 : 4) + i - cur.dir;
				
				int newcost = rotateCost + cur.pos.time + 1;
				
				if(nx < 10 && nx >= 0 && ny < 10 && ny >= 0 && map[ny][nx] != '*' && cost[ny][nx] > newcost )
				{					
					cost[ny][nx] = newcost;
					q.add(_this.new Node(_this.new Pos(nx, ny, newcost), i));
				}
			}		
		}
		
	}*/
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
