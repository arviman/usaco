//package usaco;

/*
ID: arviman1
LANG: JAVA
TASK: transform
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class transform {

	private static String[] get90(String[] src)
	{
		int n = src.length;
		String[] ret = new String[n];
		for(int i = 0 ; i < n; ++i)
			ret[i]="";
		for(int i = 0 ; i < n; ++i)
		{			
			for(int j =n-1 ;j >=0; --j)
			{
				ret[i] += src[n-j-1].charAt(i);	
			}			
		}
		
		for(int i = 0 ;i <n; ++i)
			ret[i] = strrev(ret[i]);		
		
		return ret;
		
	}
	private static String[] get180(String[] src)
	{		
		return get90(get90(src));
	}
	private static String[] get270(String[] src)
	{
		return get90(get180(src));
	}
	private static String strrev(String s)
	{
		String ret=""; int n = s.length();
		for(int i = n-1 ;i >=0; --i)
			ret+=s.charAt(i);
		return ret;
			
	}
	private static String[] getReflection(String[] src)
	{
		int n = src.length;
		String[] ret = new String[n];
		//0,1,2,3
		for(int i =0 ;i < n; ++i)
		{
			ret[i] = strrev(src[i]);
			
		}
		return ret;
	}
	
	private static boolean match(String[] src, String[] dest)
	{
		int n1 = src.length, n2 = dest.length;
		if(n1!=n2) return false;
		for(int i = 0; i < n1; ++i)
			if(!src[i].equals(dest[i]))return false;
		return true;
	}
	public static int go(String[] src, String[] dest)
	{
		
		if(match(get90(src),dest))
			return 1;		
		if(match(get180(src),dest))
			return 2;
		if(match(get270(src),dest))
			return 3;
		String[] refl = getReflection(src); 
		if(match(refl,dest))
			return 4;
		
		if(match(get90(refl),dest) || match(get180(refl),dest) || match(get270(refl),dest))
			return 5;
		if(match(src,dest))
			return 6;
		return 7;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String str = System.getProperty("user.dir")+"\\"+"transform.in";		
		File file = new File(str);		
		if(!file.canRead())
			file = new File("transform.in");
		BufferedReader bin = new BufferedReader(new FileReader(file));
		
		int N = Integer.parseInt(bin.readLine());
	    
		String[] arr = new String[N];
		String[] dest = new String[N];
	    for(int i = 0 ;i < N; ++i)
	    {
	    	arr[i] = bin.readLine();	    	
	    }
	    for(int i = 0 ;i < N; ++i)
	    {
	    	dest[i] = bin.readLine();	    	
	    }
	    
	    int ans = go(arr, dest);
	    String res = ""+ ans;
	    System.out.println(res);
	    bin.close();
	   
		
	    PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
	    pout.println(res);
	    pout.close();
	}

}
