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
TASK: crypt1
 */

public class crypt1 {
/*t3 t2 t1
     b2 b1
----------
 p2  p1  p0
      
          */
    private static boolean isOk(int[] arr, Set<Integer> set)
    {    	
    	for(int a:arr)
    	{
    		if(!set.contains(a))
    			return false;
    	}
    	return true;
    }
	private static int go(List<Integer> nums, int n)
	{
		int res = 0;
		Set<Integer> hs = new HashSet<Integer>(nums);		
		int s = 0;
		for(int h1: nums)
		{
			for(int h2: nums)
			{
				for(int h3:nums)
				{
					for(int b1 : nums)
					{
						for(int b2 : nums)
						{
							int[] p = new int[3];				 
							p[0] = h1*b1;				
							p[1] = p[0] < 10 ? 0 : p[0]/10;
							p[0] %= 10;
							p[1] += b1*h2;
							p[2] = p[1] < 10 ? 0 : p[1]/10;
							p[1] %= 10;
							p[2] += b1*h3;
							if(!isOk(p,hs))
								continue;
							int[] pp = new int[3];
							pp[0] = b2*h1;				
							pp[1] = pp[0] < 10 ? 0 : pp[0]/10;
							pp[0] %= 10;
							pp[1] += b2*h2;
							pp[2] = pp[1] < 10 ? 0 : pp[1]/10;
							pp[1] %= 10;
							pp[2] += b2*h3;
							if(!isOk(pp,hs))
								continue;
							int[] f = new int[4];
							f[0] = p[0];
							f[1] = p[1] + pp[0];
							f[2] = f[1] < 10 ? 0 : f[1]/10;
							f[1] %= 10;
							f[2] += p[2] + pp[1];
							f[3] = f[2] < 10 ? 0 : f[2]/10;
							f[2] %= 10;
							f[3] += pp[2];
							if(isOk(f,hs))
								res++;
						}
					}
				}
				
				}
			}		
				
		
		
		
		
		return res;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String taskname = "crypt1";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		int N = scr.nextInt();
		List<Integer> nums = new ArrayList<Integer>();
		for(int i = 0 ;i < N; ++i)
			nums.add(scr.nextInt());	
		int res = go(nums, N);
		
		scr.close();		
		
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		pout.println(res);
		
		System.out.println(res);
		
		pout.close();
	}

}
