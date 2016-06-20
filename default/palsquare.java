//package usaco;

/*
ID: arviman1
LANG: JAVA
TASK: palsquare
 */
import java.io.*;
import java.util.*;
public class palsquare {
	private static String convert(int num, int bas)
	{
		String tobas = Integer.toString(num, bas);
		return tobas;
		
	}
	private static boolean isPalindrome(String num)
	{
		int n = num.length();
		for(int i = 0; i < n/2; ++i)
			if(num.charAt(i) != num.charAt(n-1-i))
				return false;
		return true;
	}
	private static List<Integer> get(int bas)
	{
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 1; i <= 300; ++i)
		{
			String numInBase = convert(i*i, bas);
			if(isPalindrome(numInBase))
				res.add(i);
		}
		return res;
	}
	public static void main(String[] args) throws Exception {		
		Scanner scr = new Scanner(new BufferedReader(new FileReader("palsquare.in")));
		int input = scr.nextInt();
		scr.close();
		List<Integer> res = get(input);
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		for(int r : res)
		{			
			pout.println((Integer.toString(r, input) + " " + Integer.toString(r*r, input)).toUpperCase());
		}
		pout.close();
		
	}
}
