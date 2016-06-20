
/*
ID: arviman1
LANG: JAVA
TASK: combo
 */

/*

Combination Lock

Farmer John's cows keep escaping from his farm and causing mischief. To try and prevent them from leaving, he purchases a fancy combination lock to keep his cows from opening the pasture gate.

Knowing that his cows are quite clever, Farmer John wants to make sure they cannot easily open the lock by simply trying many different combinations. The lock has three dials, each numbered 1..N (1 <= N <= 100),
 where 1 and N are adjacent since the dials are circular. There are two combinations that open the lock, one set by Farmer John, and also a "master" combination set by the lock maker.

The lock has a small tolerance for error, however, so it will open even if the numbers on the dials are each within at most 2 positions of a valid combination.

For example, if Farmer John's combination is (1,2,3) and the master combination is (4,5,6),
 the lock will open if its dials are set to (1,3,5) (since this is close enough to Farmer John's combination) or to (2,4,8) (since this is close enough to the master combination). 

Note that (1,5,6) would not open the lock, since it is not close enough to any one single combination.

Given Farmer John's combination and the master combination, please determine the number of distinct settings for the dials that will open the lock. Order matters, so the setting (1,2,3) is distinct from (3,2,1).
PROGRAM NAME: combo
INPUT FORMAT:
Line 1:	The integer N.
Line 2:	Three space-separated integers, specifying Farmer John's combination.
Line 3:	Three space-separated integers, specifying the master combination (possibly the same as Farmer John's combination).
SAMPLE INPUT (file combo.in):

50
1 2 3
5 6 7

INPUT DETAILS:

Each dial is numbered 1..50. Farmer John's combination is (1,2,3), and the master combination is (5,6,7).
OUTPUT FORMAT:
Line 1:	The number of distinct dial settings that will open the lock.
SAMPLE OUTPUT (file combo.out):

249

*/

import java.io.*;
import java.util.*;
public class combo {	
	static int n;
	private static Boolean IsClose(int i, int j)
	{
		int d1 = j - i;
		Boolean ret= ( (d1 <= 2 && d1 >= -2) || ((j + n  - i) <= 2) || ((i + n - j) <= 2) ) ;
		
		//if(ret) System.out.println(i + " " + j);
		return ret;
		
	}
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		String taskname = "combo";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
	
		n = scr.nextInt();
		int[] com1 = new int[3];
		int[] com2 = new int[3];
		for(int i = 0 ; i < 3; ++i)
		  com1[i] = scr.nextInt();
		for(int i = 0 ; i < 3; ++i)
		  com2[i] = scr.nextInt();
		scr.close();
		int res = 0;
		//main
		Boolean a=false,b=false;
		/*for(int i = 1 ; i <= n; ++i)
		for(int j = 1 ; j <= n; ++j)
		for(int k = 1 ; k <= n; ++k)
		{			
			if(IsClose(i, com1[0]) && IsClose(j, com1[1]) && IsClose(k, com1[2]))
			{
			System.out.println(i + " " + j + " " + k );
				++res;
				}
			else if(IsClose(i, com2[0]) && IsClose(j, com2[1]) && IsClose(k, com2[2]))
			{
			System.out.println(i + " " + j + " " + k );
				++res;
				}
		}*/
		for(int i = 1 ; i <= n; ++i)
		{			
			if((a = IsClose(i, com1[0])) | (b=IsClose(i, com2[0])) )	
			{			
				for(int j = 1 ; j <= n; ++j){
					if( (a &= IsClose(j, com1[1])) | (b &= IsClose(j, com2[1])) ){			
						for(int k = 1 ; k <= n; ++k)
						{			
							if( (a &= IsClose(k, com1[2])) | (b &= IsClose(k, com2[2])) )			
							{
								//System.out.println(i + " " + j + " " + k );
								++res;									
							}
						}
					}
				}						
			}
		}
		
		//OUTPUT
		
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));	
		
		pout.println(res);
		
		
		System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + "ms");
		pout.close();
	}

}
