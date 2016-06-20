
/*
ID: arviman1
LANG: JAVA
TASK: pprime
 */

import java.io.*;
import java.util.*;
public class pprime {	
	static int A; static int B;
	static List<Integer> res = new ArrayList<Integer>();
	/*private static boolean isPal(int a)
	{
		String s = ""+a;
		int n = s.length();
		for(int i = 0; i < n/2 ; ++i)
			if(s.charAt(i) != s.charAt(n-1-i))
				return false;
		return true;
	}*/
	private static boolean isPrime(int a)
	{	
		if(a % 2 ==0)return false;
		int sqrt = (int) Math.sqrt(a);
		for(int i = 3; i <= sqrt; i+=2)
		{
			if(a % i == 0)
				return false;
		}
		return true;
	}
	private static String strrev(String str)
	{
		int n = str.length();
		String rev = "";
		
		for(int i = n-1; i >-1; --i)
		{
			rev += str.charAt(i);
		}
		return rev;
	}
	private static void checkPrimeAndAdd(String str)
	{
		long res1 = Long.parseLong(str);
		
		if(res1 <= B && res1 >= A)
		{
			int ires = (int)res1;
			if(isPrime(ires))
				res.add(ires);		
		}
		
	}
	private static List<Integer> genPalindromes(int a, int b)	
	{
		
		
		int N = 1+(""+b).length()/2;
		
		for(int i = 1; i<=b; ++i)
		{
			String str = ""+i;
			if((str.charAt(0)-'0')%2 == 0)
				continue;
			int n = str.length(); 
			if(n > N)break;
			String rev1 = strrev(str);				
			
			if(n % 2 == 1)
			{				
				String rev2 = rev1.substring(1);				
				
				checkPrimeAndAdd(str + rev2);			
				
				
				for(int m = 0; m <= 9; ++m)
				{
					checkPrimeAndAdd(str + m + rev1);										
				}
			}			
			checkPrimeAndAdd(str + rev1);					
			
		}
		return res;
		
		
	}
	/*
	private static List<Integer> go(int a, int b)
	{		
		int LIM = 100000001;
		
		long[] arr = new long[(int)Math.ceil(LIM/64.)];
		
		List<Integer> res = new ArrayList<Integer>();		
		int broot = (int)Math.sqrt(b);
		for(int i = 3; i <= b; i+=2)
		{	
			
			if((arr[(i+1)/64] & (1L<<(i%64))) == 0)//hasn't been seen by sieve
			{				
				if(i >= a && isPal(i))
					res.add(i);
				
				for(int j = 3*i; j <= b; j += i)
					arr[(j+1)/64] |= (1L<<(j%64));//make sieve hole
			}
		}

		
		return res;
		
	}*/
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "pprime";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));	
		int a = scr.nextInt(); int b = scr.nextInt();
		A = a; B= b;
		scr.close();

		List<Integer> res = genPalindromes(a,b);		//Call main
		Collections.sort(res);
		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));		
		for(int r : res)
		{
		  String line = ""+r;
		  pout.println(line);
		  System.out.println(line); 
		}

		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
