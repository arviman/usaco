
/*
ID: arviman1
LANG: JAVA
TASK: lamps
 */

import java.io.*;
import java.util.*;
public class lamps {	
	static List<Integer> finalOn;
	static List<Integer> finalOff;
	static List<BitSet> bsList;
	static int c;
	static int n;
	static String taskname = "lamps";
	class BitComparator implements Comparator<BitSet>
	{

		@Override
		public int compare(BitSet a, BitSet b) {
			for(int i = 1; i <= n; ++i) 
				if(a.get(i) != b.get(i) )
					return (a.get(i)?1:0) - (b.get(i)?1:0);
			return 0;
		}
		
	}
	
	private static boolean isOk(BitSet bs)
	{
		for(Integer on : finalOn)
			if(!bs.get(on))return false;
		for(Integer off : finalOff)
			if(bs.get(off))return false;
		return true;
	}
		
	private static void go()
	{
		int odds;
		int evens;
		int all;
		int threes;
		bsList = new ArrayList<BitSet>();
		
		
		for(int a = 0; a <= 1; ++a)
		  for(int e = 0; e <= 1; ++e)if((a+e) > c)continue;else
		    for(int o = 0; o <= 1; ++o)if((a+e+o) > c)continue;else
		      for(int th = 0; th <= 1; ++th)if((a+e+o+th) > c)continue;else
		      {
		    	  int total = a+e+o+th;
		    	  if(total%2 != c%2)
		    		  continue;
		    	  boolean non3odd = true, non3even = true, odd3 = true, even3 = true;
		    	  if(a == 1)
		    	  {
		    		  non3odd = !non3odd; non3even = !non3even; odd3 = !odd3; even3 = !even3;		    		  
		    	  }
		    	  if(e == 1)
		    	  {
		    		  non3even = !non3even; even3 = !even3;
		    	  }
		    	  if(o == 1)
		    	  {
		    		  non3odd = !non3odd; odd3 = !odd3;
		    	  }
		    	  if(th == 1)
		    	  {
		    		  odd3 = !odd3; even3 = !even3;
		    	  }
		    	  BitSet bs = new BitSet(n+1);
		    	  
		    	  for(int i = 1; i <= n; ++i)
		    	  {
		    		  boolean isEven = i%2==0;
		    		  boolean isThreeMult = (i-1)%3 == 0;
		    		  if(isEven)
		    		  {
		    			if(isThreeMult)
		    			{
		    				if(even3)bs.set(i);
		    			}
		    			else
		    			{
		    				if(non3even)bs.set(i);
		    			}
		    		  }
		    		  else
		    		  {
		    			  if(isThreeMult)
			    			{
			    				if(odd3)bs.set(i);
			    			}
			    			else
			    			{
			    				if(non3odd)bs.set(i);
			    			}
		    		  }
		    		  
		    	  }
		    	  
		    	  
		    	  if(isOk(bs))
		    		  bsList.add(bs);
		      }
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		
		n = scr.nextInt();
		c = scr.nextInt();
		finalOn = new ArrayList<Integer>();
		finalOff = new ArrayList<Integer>();
		int val;
		while(true)
		{
			val = scr.nextInt();
			if(val == -1)break;
			finalOn.add(val);			
		}
		
		while(true)
		{
			val = scr.nextInt();
			if(val == -1)break;
			finalOff.add(val);			
		}
		go();	
		
		Collections.sort(bsList, new lamps().new BitComparator());
		
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		StringBuilder sb;
		for(BitSet bs : bsList)
		{		
			sb = new StringBuilder();
			for(int i = 1; i <= n; ++i)
			{
				sb.append(bs.get(i) ? "1":"0");
			}
			String str = sb.toString();
			System.out.println(str + " " +  bs.toString());
			pout.println(str);
		}
		if(bsList.isEmpty())
		{
			System.out.println("IMPOSSIBLE");
			pout.println("IMPOSSIBLE");
		}
		pout.close();
		//OUTPUT	
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		
	}

}
