//package usaco;

/*
ID: arviman1
LANG: JAVA
TASK: dualpal
 */
import java.io.*;
import java.util.*;
public class dualpal {
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
	private static boolean isPalindromeDual(int num)
	{
		int cnt = 0;
		for(int bas = 2; bas <= 10 && cnt < 2; ++bas)
			if(isPalindrome(Integer.toString(num, bas)))
				cnt++;
		return cnt == 2;			
	}
	private static List<Integer> get(int N, int S)
	{
		List<Integer> res = new ArrayList<Integer>();
		int i = S+1;
		while(res.size() < N)
		{
			if(isPalindromeDual(i))
				res.add(i);
			i++;
		}
		return res;
	}
	public static void main(String[] args) throws Exception {		
		Scanner scr = new Scanner(new BufferedReader(new FileReader("dualpal.in")));
		int N = scr.nextInt();
		int S = scr.nextInt();
		scr.close();
		List<Integer> res = get(N, S);
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		for(int r : res)
		{	
			System.out.println(r);
			pout.println(r);
		}
		pout.close();
		
	}
}
