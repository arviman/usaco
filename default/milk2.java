//package usaco; //todo remove this while submitting
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 
 */
/*
ID: arviman1
LANG: JAVA
TASK: milk2
 */

public class milk2 {

	/**
	 * @param args
	 */
	private static int[] go(int[][] arr, int N) {
		int[] res = new int[2];
		
		int max = -1, min = 999999;
		boolean[] cnt = new boolean[1000000];
		for(int[] a : arr)
		{
			int a1 = Math.min(a[1],a[0]), a2 =  Math.max(a[1],a[0]);
			for(int i = a1; i < a2; ++i)
				cnt[i] = true;
			
			max = Math.max(a2, max);
			min = Math.min(a1, min);
		}
		
		
		int cntr1=0,cntr2 = 0;
		for(int i = min; i < max; ++i)
		{
			if(cnt[i])
			{
				cntr1++;
				cntr2=0;
			}
			else
			{
				cntr2++;
				cntr1=0;
			}
			if(res[0] < cntr1)
			{				
				res[0] = cntr1;
			}
			if(res[1] < cntr2)
			{				
				res[1] = cntr2;
			}
		}		
		
		
		return res;

	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String str = System.getProperty("user.dir")+"\\"+"milk2.in";		
		File file = new File(str);		
		if(!file.canRead())
			file = new File("milk2.in");
		BufferedReader bin = new BufferedReader(new FileReader(file));
		
		int N = Integer.parseInt(bin.readLine());
	    int[][] arr = new int[N][2];
	    for(int i = 0 ;i < N; ++i)
	    {
	    	String line = bin.readLine();
	    	arr[i] = new int[2];
	    	String[] spl = line.split(" ");
	    	arr[i][0] = Integer.parseInt(spl[0]);
	    	arr[i][1] = Integer.parseInt(spl[1]);
	    }
	    
	    int[] ans = go(arr, N);
	    String res = ans[0] + " " + ans[1];
	    System.out.println(res);
	    bin.close();
	   
		
	    PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
	    pout.println(res);
	    pout.close();
	}

}
