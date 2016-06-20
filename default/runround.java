
/*
ID: arviman1
LANG: JAVA
TASK: runround
 */

import java.io.*;
import java.util.*;
public class runround {	
	static String taskname = "runround";
	static boolean[] used = new boolean[10];
	static long best = Long.MAX_VALUE;
	static long num;
	static int n;
	private static boolean isok(long addTo)
	{		
		boolean[] vis = new boolean[n];
		char[] digits = (""+addTo).toCharArray();
		int cnt = 0;
		int pos = 0;
		
	//	System.out.println("checking isok " + addTo);
		for(; cnt < n; ++cnt)
		{
			if(vis[pos])return false;
			vis[pos] = true;			
			pos = (pos + digits[pos] - '0') % n;			
		}
		
		return pos == 0 && cnt == n;
		
	}
	private static boolean isUniqueDigits(long num)
	{
		boolean[] dig = new boolean[10];
		while(num>0)
		{
			int last = (int)(num%10);
			num/=10;
			if(dig[last]) return false;
			dig[last] = true;
		}
		return true;
	}
	private static void go(int remaining, int start, long addTo)
	{	
		if(remaining == 0)
		{
			//if(addTo < 84000)
			//System.out.println("-"+addTo);
			//if(addTo == 81362)System.out.println(isok(addTo));
			if(addTo < best && addTo > num && isok(addTo))
			{
				best = addTo;
				System.out.println("updated: " + best);
			}
			return;	
		}	
		//System.out.println(remaining);
		
		addTo *= 10;
		for(int i = start; i <= 9; ++i)
		{
			if(used[i])continue;
			used[i] = true;
			go(remaining-1, 1, addTo + i);
			used[i] = false;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		num = scr.nextInt();
				
		char[] digits = (""+num).toCharArray();
		n = digits.length;
		int firstDigit =digits[0] - '0';		
		
		
		if(firstDigit == 9)
		{
			used[9] = true;			
			go(n-1, 1, 9);
			used[9] = false;
		}
		else
		{
			//used[firstDigit] = true;
			go(n, firstDigit, 0);
		}		
		
		
		while(best == Long.MAX_VALUE)
		{
			System.out.println("Incrementing n to " + (n+1));
			n++;
			Arrays.fill(used, false);
			go(n, 1, 0);
		}
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		System.out.println(best);
		pout.println(best);
		
		pout.close();
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
