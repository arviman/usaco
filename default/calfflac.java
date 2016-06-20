//package usaco;
//package usaco; //todo remove this while submitting
import java.io.*;
import java.util.*;
/**
 * 
 */
/*
ID: arviman1
LANG: JAVA
TASK: calfflac
 */

enum palResult
{
	good, //letters are equal
	skipleft,
	skipright,
	skipleftright,
	bad//letters not equal
}

class result
{
	int val;
	String string;
	public result(int val, String str)
	{
		this.val = val; this.string = str;
	}
}
public class calfflac {
	private static palResult check(char a, char b)
	{
		boolean aok = Character.isLetter(a);
		boolean bok = Character.isLetter(b);
		if(aok && bok)
		{
			return Character.toLowerCase(a) == Character.toLowerCase(b) ? palResult.good : palResult.bad;			
		}
		else if(!aok && !bok)
			return palResult.skipleftright;
		else if(!aok)
			return palResult.skipleft;
		else
			return palResult.skipright;
		
	}
	
	private static int countLetters(String str)
	{
		int n = str.length();
		int res = 0;
		for(int i = 0; i < n; ++i)
			if(Character.isLetter(str.charAt(i)))
			  res++;
		return res;
	}
	private static result go(String str) {
		
		int n = str.length();
		int N = 20000;
		int max = 1;
		
		int maxi = 0, maxj = 0;
				
			//System.out.println("doing " +len);
		for(int i = 0; i < n; ++i )
		{	
			//hold center;
			
			int cnt = 1;
			int left = i-1, right = i+1;
			while(left >= 0 && right < n)
			{
				int tres = right-left + 1;
				boolean ok = true;
				char a = str.charAt(left);
				char b = str.charAt(right);
				palResult result = check(a,b);
				
				if(result==palResult.bad)
					break;
				if(result == palResult.skipleft)
				{
					left--;continue;
				}
				else if(result == palResult.skipright)
				{
					right++; continue;
				}		
				if(result == palResult.good && tres>max)
				{
					max = tres; maxi = left; maxj = right;
					//System.out.println("Center of " + i);
				}				
				left--;right++;
			}
			//hold left;
			left = i; right = i+1;
			while(left >= 0 && right < n)
			{
				char a = str.charAt(left);
				char b = str.charAt(right);
				int tres = right-left+1;
				palResult result = check(a,b);
				if(result==palResult.bad)
					break;
				if(result == palResult.skipleft)
				{
					left--;continue;
				}
				else if(result == palResult.skipright)
				{
					right++; continue;
				}				
				else if(result == palResult.good && tres>max)				
				{
					max = tres; maxi = left; maxj = right;
				}		
				left--; right++;
			}
			
		}
		String resString = str.substring(maxi, maxj+1);
		return new result(countLetters(resString), resString );			
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String taskname = "calfflac";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		String str = scr.useDelimiter("\\A").next();
		
		System.out.println(str);
		scr.close();
		result res = go(str);
		//int res = go(M, S, C, list);
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		pout.println(res.val);
		pout.println(res.string);
		System.out.println(res.val);
		System.out.println(res.string);
		pout.close();
	}

}
