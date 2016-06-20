
/*
ID: arviman1
LANG: JAVA
TASK: checker
 */


import java.io.*;
class checker
{
    static int n;
    static boolean ifCol[];
    static boolean rDiag[] = new boolean [26];
    static boolean lDiag[] = new boolean [26];
    static int col[];
    static int print;
    static int counter;
    static PrintWriter out;
    private static void search (int row)
    {
        if (row == n)
        {
            if (print < 3)
            {
            	System.out.print (col [0] + 1);
                out.print (col [0] + 1);
                for (int i = 1 ; i < n ; i++)
                {
                	System.out.print (" " + (col [i] + 1));
                    out.print (" " + (col [i] + 1));
                }
                System.out.println();
                out.println ();
                print++;
            }
            counter++;
            return;
        }
        for (int i = 0 ; i < n ; i++)
        {
            if (!ifCol [i] && !rDiag [i - row + 13] && !lDiag [i + row])
            {
                ifCol [i] = rDiag [i - row + 13] = lDiag [i + row] = true;
                col [row] = i;
                search (row + 1);
                ifCol [i] = rDiag [i - row + 13] = lDiag [i + row] = false;
            }

        }
        return;
    }


    public static void main (String[] args) throws IOException
    {
        long start = System.currentTimeMillis ();
        BufferedReader in = new BufferedReader (new FileReader ("checker.in"));
        out = new PrintWriter (new BufferedWriter (new FileWriter ("checker.out")));
        n = Integer.parseInt (in.readLine ());
        col = new int [n];
        ifCol = new boolean [n];
        search (0);
        out.println (counter);
        System.out.println (counter);
        System.out.println (System.currentTimeMillis()-start);
        out.close ();
        System.exit (0);
    }
}

/*

import java.io.*;
import java.util.*;
class State
{
	long has;
	static int N;
	char[] seq;	
	int rows;a
	boolean[] diagFtaken=null;
	boolean[] diagBtaken=null;
	public State(long has, char[] seq, boolean[] dft, boolean[] dbt, Integer lastcol )
	{
		this.has = has; //to represent if a column has already been filled
		this.seq = seq;	
		
		diagFtaken = dft == null ? new boolean[2*N+1] :  Arrays.copyOf(dft, 2*N+1);		
		diagBtaken = dbt == null ? new boolean[2*N+1] : Arrays.copyOf(dbt, 2*N+1);
		
		if(lastcol != null)
		{
			int lastrow = seq.length;
			int diagf = lastcol - lastrow + N; // f -> \
			diagBtaken[diagf] = true;
			int diagb = lastcol + lastrow; // b -> /
			diagBtaken[diagb] = true;
		}
		
	}
	public boolean isSafeDiaganol(int row, int col)
	{
		int diagf = col - row + N; // f -> \
		
		int diagb = col + row; // b -> /
		
		return !diagBtaken[diagb] && !diagFtaken[diagf];			
	}
}
public class checker {	
	static int cnt = 0;
	static List<Integer> res = new ArrayList<Integer>();
	
	private static List<String> go(int n)	
	{			
		List<String> ret = new ArrayList<String>();
		Stack<State> stack = new Stack<State>();		
		
		State.N = n;
		
		stack.push(new State(0L, new char[0], null, null, null));
		
		
		while(!stack.isEmpty())
		{
			State cur = stack.pop();	
			int curn = cur.seq.length;
			
			int lastChar = curn > 0 ? (cur.seq[curn-1] - '0') : -1;
			for(int i = 1; i <= n; ++i)//column
			{
				//has column already been taken for this state
				if((cur.has & (1L << i)) != 0) continue;
				//optimize...make sure adj. chars of last char in seq are not taken
				if((i+1) == lastChar || (i-1) == lastChar)continue;
				char[] nsseq = Arrays.copyOf(cur.seq, curn+1);
				nsseq[curn] = (char)('0' + i);				
				
				//boolean isSafeDiag = !cur.baddiag[curn+1][i]; //curn is the row, i is the column
							  	
			  
				
				//if(isSafeDiag)
				 				
				if(cur.isSafeDiaganol(curn, i))
				{					
					long nshas = cur.has | (1L << i) ;
					if((curn+1) == n) //have reached final depth
					{						
						cnt++;						
						ret.add(new String(nsseq));
						int rets = ret.size();
						if(rets == 4)
						{
						  Collections.sort(ret);						
						  ret.remove(3);//remove last
						}
					}
					else
					{									  
					  State ns = new State(nshas, nsseq, cur.diagFtaken, cur.diagBtaken, i );
					  stack.push(ns);
					}
				}
			}
		}
		return ret;
	}	
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "checker";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));	
		int a = scr.nextInt();		
		scr.close();

		List<String> res = go(a);		//Call main
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");

		//OUTPUT
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));		
		int n = res.size();
		int till = Math.min(3, n);
		for(int i = 0; i < till ; ++i)
		{
		  int strL = res.get(i).length();
		  String line = "" + (res.get(i).charAt(0) - '0');
		  for(int j = 1; j < strL ; ++j)
		  {
			line += " " + (res.get(i).charAt(j) - '0');
		  }
		  
		  pout.println(line);
		  System.out.println(line); 
		}
		
		pout.println(cnt);
		System.out.println(cnt);

		System.out.println(" Total time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
*/