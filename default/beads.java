//package usaco;
/*
ID: arviman1
LANG: JAVA
TASK: beads
 */

import java.io.*;
import java.util.regex.Pattern;
public class beads {
	private static String strrev(String str)
	{
		int n = str.length();
		String ret = "";
		for(int i = n-1; i>=0; --i)
			ret += str.charAt(i);
		return ret;		
	}	
	
	private static int get(String str)
	{
		int ret = 0;
		String[] spl = str.split("b+");
		if(spl.length > 0)
			ret = spl[0].length();
		spl = str.split("r+");
		if(spl.length > 0)
			ret = Math.max(spl[0].length(),ret);
		return ret;
		
	}
	private static int compute(String str)
	{
		int ret = 0;	
		int n = str.length();
		for(int i = 0 ;i < n; ++i)
		{
			String s = strrev(str.substring(0,i)) + strrev(str.substring(i));
			String s1r = str.substring(i) + str.substring(0,i);
			int tres = get(s) + get(s1r);
			if(tres > ret)
				System.out.println(s + " " + s1r);
			ret = Math.max(ret, tres );			
		}
		return ret;
		
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub    
		String str = System.getProperty("user.dir")+"\\"+"beads.in";		
		File file = new File(str);		
		if(!file.canRead())
			file = new File("beads.in");
		BufferedReader bin = new BufferedReader(new FileReader(file));
	    PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

	    int count = Integer.parseInt(bin.readLine());
	    String beads = bin.readLine();
	    int ans = Math.min(count, compute(beads));	 
	    System.out.println(ans);
	    bin.close();
	    pout.println(ans);
	    pout.close();
		
	}

}
