
/*
ID: arviman1
LANG: JAVA
TASK: frac1
 */

import java.io.*;
import java.util.*;
public class frac1 {	
	static String taskname = "frac1";
	static int n;
	class Fraction implements Comparable<Fraction>
	{
		int num;
		int den;
		public Fraction(int num, int den)
		{
			this.num = num; this.den = den;			
		}
		@Override
		public int compareTo(Fraction o) {
			if(o.den == 0)
				 if(this.den == 0)return 0;
				 else return Integer.MAX_VALUE;
			else if(this.den == 0)
				return Integer.MIN_VALUE;
				
			int val1 = this.num * o.den;
			int val2 = o.num * this.den;
			return val1-val2;			
			
		}
		@Override
		public String toString() {		   
		    return this.num + "/" + this.den;
		  }
	}
	private static void go()  throws IOException
	{	
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		frac1 f1 = new frac1();
		Set<Fraction> set = new TreeSet<Fraction>();
		for(int den = 1; den <= n; ++den)
		{
			for(int num = 0; num <= den; ++num )
			{
				set.add(f1.new Fraction(num, den));
			}
		}
		
		Iterator<Fraction> it = set.iterator();
		while(it.hasNext())
		{
			Fraction f = it.next();			
			System.out.println(f);
			pout.println(f);
		}
		
		pout.close();
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		n = scr.nextInt();
		
		go();		//Call main
		
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
