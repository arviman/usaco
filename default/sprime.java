
/*
ID: arviman1
LANG: JAVA
TASK: sprime
 */

import java.io.*;
import java.util.*;
public class sprime {	
	static int A; static int B;
	static List<Integer> res = new ArrayList<Integer>();
	
	private static boolean isPrime(int a)
	{	
		if(a == 1)return false;
		if(a == 2) return true;
		if(a % 2 ==0)return false;
		int sqrt = (int) Math.sqrt(a);
		for(int i = 3; i <= sqrt; i+=2)
		{
			if(a % i == 0)
				return false;
		}
		return true;
	}	
		
	private static List<Integer> go(int n)	
	{			
		List<Integer> ret = new ArrayList<Integer>();
		Stack<String> stack = new Stack<String>();		
		stack.push("");
		while(!stack.isEmpty())
		{
			String cur = stack.pop();			
			for(int i = 1; i <= 9; ++i)
			{
				String ns = cur + i;
				int nsi = Integer.parseInt(ns);
				if(isPrime(nsi))
				{
					if(ns.length() == n)
						ret.add(nsi);
					else
					  stack.push(ns);
				}
			}
		}
		return ret;
	}	
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "sprime";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));	
		int a = scr.nextInt();
		
		scr.close();

		List<Integer> res = go(a);		//Call main
		Collections.sort(res);
		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));		
		for(int r : res)
		{
		  String line = ""+r;
		  pout.println(line);
		  //System.out.println(line); 
		}

		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
