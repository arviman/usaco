
/*
ID: arviman1
LANG: JAVA
TASK: sort3
 */

import java.io.*;
import java.util.*;
public class sort3 {	
	static String taskname = "sort3";
	static int n;
	static int[] num;
	static int[] cnt;
	static int ret = 0;
	private static boolean isOneZone(int i)
	{
		return i < cnt[0];
	}
	private static boolean isTwoZone(int i)
	{
		return i >= cnt[0] && i < (cnt[0]+cnt[1]);
	}
	private static boolean isThreeZone(int i)
	{
		return i >= (cnt[0]+cnt[1]);
	}
	
	private static void swap(int i, int j)
	{
		int t;
		t = num[i]; num[i] = num[j]; num[j] = t;
		System.out.println((i+1) + " " + (j+1));
		ret++;
	}
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		
		
		int treStart = cnt[0] + cnt[1], dueStart = cnt[0];
		System.out.println("Move threes to twos or ones");
		for(int tre = treStart; tre < n; ++tre)
		{
			if(num[tre] != 3)
			{
				if(num[tre] == 2)
				for(int dues = dueStart; dues < treStart; ++dues )
				{
					if(num[dues] == 3)
					{
						swap(dues, tre);break;
					}
				}
				else //1
				{
					for(int ones = 0; ones < dueStart; ++ones )
					{
						if(num[ones] == 3)
						{
							swap(ones, tre);	break;						
						}
					}
				}
			}
		}
		System.out.println("move any 3 in zone 2 to zone 3");
		if(ret < cnt[2]) //move any 3 in zone 2 to zone 3
		{
			for(int tre = treStart; tre < n; ++tre)
			{
				if(num[tre] != 3)
				{
					for(int dues = dueStart; dues < treStart; ++dues )
					{
						if(num[dues] == 3)
						{
							swap(dues, tre); break;
						}
					}
				}
			}
		}
		System.out.println("move any 3 in zone 1 to zone 3");
		if(ret < cnt[1]) //move any 3 in zone 1 to zone 3
		{
			for(int tre = treStart; tre < n; ++tre)
			{
				if(num[tre] != 3)
				{
					for(int ones = 0; ones < dueStart; ++ones )
					{
						if(num[ones] == 3)
						{
							swap(ones, tre); break;
						}
					}
				}
			}
		}
		
		System.out.println("Finally exchange remaining ones and twos");
		for(int ones = 0; ones < dueStart; ++ones)
		{
			if(num[ones] != 1)
			{
				for(int dues = dueStart; dues < treStart; ++dues )
				{
					if(num[dues] == 1)
					{
						swap(ones, dues); break;
					}
				}
			}
		}
		System.out.println(ret);
		pout.println(ret);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		n = scr.nextInt();
		num = new int[n];
		cnt = new int[3];
		int i = 0;
		while(scr.hasNext())
		{
			num[i] = scr.nextInt();
			cnt[num[i] - 1]++;
			i++;
		}
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
