
/*
ID: arviman1
LANG: JAVA
TASK: clocks
 */

import java.io.*;
import java.util.*;

class Node
{
	int state;//state
	
	byte[] moves;//past moves done
	
	//int maxDepth;
	public Node(int state, byte[] oldMoves, Byte lastMove)
	{
		this.state = state;		
		moves = oldMoves == null ? new byte[0] : 
			Arrays.copyOf(oldMoves, oldMoves.length+1);
		
		if(lastMove!=null)
			moves[oldMoves.length] = lastMove;
	}	
}
public class clocks {	
	static boolean[] vis = new boolean[1 << 18];
	static String[] chars = {"ABDE", "ABC", "BCEF", "ADG", "BDEFH", "CFI", "DEGH", "GHI", "EFHI"};
	static char[][] moves;
	
	//given clock state and key, get new clock state
	private static int doChange(int state, int m)
	{			
		m--;		
		int n = moves[m].length;	
		
		for(int i = 0; i < n; ++i)
		{			
			int maskPos = ((moves[m][i] - 'A')<<1);			
			int newstate = ((state >> maskPos) + 1) & 3; //get the two bits that are masked into least significant bits and increment 
			state &= ~(3 << maskPos); //reset the 2 bits in old state
			state |= (newstate << maskPos);		//add the two bits */			
		}
		
		return state;		
	}
		
	
	private static byte[] go(int arr)
	{		
		
		Queue<Node> q = new ArrayDeque<Node>();
		vis[arr] = true;
		q.add(new Node(arr, null, null));		
		while(!q.isEmpty())
		{
			Node s = q.poll();
		
			if(s.state == 0)//finish
				return s.moves;			
			
			byte[] cnt = new byte[10];
			
			for(byte m : s.moves)			
				++cnt[m];			
			
			for(byte i = 1; i <= 9; ++i)
			{
				if(cnt[i] >= 3)continue;				
				int changed = doChange(s.state, i);
				if(vis[changed])
					continue;
				Node ns =new Node(changed, s.moves, i);
				vis[ns.state] = true;				
				q.add(ns);
			}		
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "clocks";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		/*
		 * Use 2 bits to represent clocks. 12'0 by 0, 3'0clock by 01, 6'0 clock by 10 and 9'0 by 11		  
		 */
		moves = new char[chars.length][];
		for(int i = 0;i < chars.length; ++i)
			moves[i] = chars[i].toCharArray();
		int state = 0;
		int i = 0;
		
		while(i < 9)
		{
			int val = (scr.nextInt()/3)%4; //12->0, 3->1
			int maskPos = (i<<1); //top left clock in last two bits...bottom right in left two bits
			state |= ((3&val) << maskPos);
			i++;
		}		
		
		scr.close();

		byte[] res = go(state);		//Call main		
		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));	
		
		String line = "";
		if(res.length != 0)
		  line += res[0];
		int nres = res.length;
		System.out.println(nres);
		for(i = 1;i < nres; ++i)
		{						
			line += " " + res[i];
		}
		pout.println(line);
		System.out.println(line);
		System.out.println(System.currentTimeMillis()-startTime);
		pout.close();
	}

}
