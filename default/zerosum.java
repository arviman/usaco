
/*
ID: arviman1
LANG: JAVA
TASK: zerosum
 */

import java.io.*;
import java.util.*;

import javax.sound.midi.Soundbank;
public class zerosum {	
	static String taskname = "zerosum";
	static int N;
	
	static int[] ops;//0-add 1-sub 2-combine
	static int[][] combine;
	static PrintWriter pout;
	static List<String> ret = new ArrayList<String>();
	static void process() 
	{
		//ops count - n-1
		int sum = 0;
		int val = 0;
		List<Integer> opsList = new ArrayList<Integer>();
		List<Integer> numList = new ArrayList<Integer>();
		
		for(int i = 0; i < N-1; ++i)
		{
			if(ops[i] != 2)
			{				
				numList.add(val + (i+1));				
				opsList.add(ops[i]);
				val = 0; 
			}
			else
			{
				val *= 10;
				val += (i+1)*10;				
			}
		}		
		
		numList.add(val + N);		
		
		int sz = opsList.size();
		sum = numList.get(0);
		/*System.out.println(sz + " " + numList.size());
		System.out.println(Arrays.toString(ops));
		System.out.println(opsList);System.out.println(numList);*/
		int numIndex = 1;
		for(int i = 0; i < sz; ++i)
		{			
			int op = opsList.get(i);
			int num = numList.get(numIndex++);
			if(op == 0)
				sum += num;
			else sum -= num;
		}
		StringBuffer str = new StringBuffer();
		if(sum == 0)
		{
			for(int i = 0; i <= N-2; ++i)
			{
				str.append(i+1);
				str.append(ops[i] == 0 ? "+" : ops[i] == 1 ? "-" : " ");				
			}			
			str.append(N);
			ret.add(str.toString());
		}	
		
	}
	static void recurse(int depth, int val)
	{
		ops[depth] = val;
		if(depth == N-2)
		{			
			process();
		}
		else
		{
			for(int i = 0; i < 3; ++i)
			{
				recurse(depth+1,i);
			}
			
		}
	}
	
	private static void go()  throws IOException
	{	
		int n = N;
		ops = new int[n-1];
		pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		/*for(int i = 1; i <= (n); ++i)
			combine[i][i] = i;
		for(int i = 0; i <= (n); ++i)
		{
			for(int j = i+1; j <= n; ++j)				
			{
				combine[i][j] = combine[i][j-1]*10 + j;
			}
		}*/
		for(int i = 0; i < 3; ++i)
			recurse(0,i);
		Collections.sort(ret);
		for(String r : ret)
		{
			//System.out.println(r);
			pout.println(r);
		}
		//pout.println(res);
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		N = scr.nextInt();
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
