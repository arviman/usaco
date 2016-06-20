//package usaco;

/*
ID: arviman1
LANG: JAVA
TASK: namenum
 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class namenum {
	static Map<Short, List<String>> map = new HashMap<Short, List<String>>();
	private static void initDial()
	{
		/*
		 *  2: A,B,C     5: J,K,L    8: T,U,V
            3: D,E,F     6: M,N,O    9: W,X,Y
            4: G,H,I     7: P,R,S 
		 */
		char a = 'A';
		for(short i = 0, key = 1 ; a < 'Z' ; ++i, ++a)
		{
			if(a == 'Q')
			{
				--i;
				continue;
			}
			if(i%3==0)key++;
			if(map.containsKey(key))
				map.get(key).add("" + a);
			else
			{
				List<String> lst = new ArrayList<String>(); lst.add("" + a);				
				map.put(key, lst);
			}
		}		
		
	}
		

	private static boolean matches(String str, long input)
	{
		int n = str.length();
		int i = n-1;
		while(input > 0 && i >= 0)
		{
			long last = input % 10;
			input/=10;
			if(!map.get((short)last).contains(str.substring(i,i+1)))
			{
				return false;
			}
			i--;
		}
		return true;
	}
	private static List<String> go(Set<String> dict, long input)
	{
		initDial();
		//List<String> getPerm = getPerm(input);
		List<String> ret = new ArrayList<String>();
		int N = String.valueOf(input).length();
		for(String d : dict)
		{				
			if(d.length() == N && matches(d, input))
				ret.add(d);
		}
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new FileReader("dict.txt"));
		Scanner scr = new Scanner(bfr);
		Set<String> dict = new HashSet<String>();
		while(scr.hasNext())
		{
			dict.add(scr.next());
		}
		
		
		File file = new File("namenum.in");
		BufferedReader inp = new BufferedReader(new FileReader(file));
		long input = Long.parseLong(inp.readLine());
		inp.close();
		
		List<String> ret = go(dict, input);
		Collections.sort(ret);
		bfr.close();
		
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		if(ret == null || ret.size() == 0)
		{
			pout.println("NONE");
		}
			
		for(String r : ret)
		{
			System.out.println(r);
			pout.println(r);
		}
	    pout.close();

	}

}
