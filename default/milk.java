//package usaco; //todo remove this while submitting
import java.io.*;
import java.util.*;
/**
 * 
 */
/*
ID: arviman1
LANG: JAVA
TASK: milk
 */
class Vendor implements Comparable<Vendor>
{
	int price;
	int amount;
	public Vendor(int price, int amount)
	{
		this.price = price; this.amount = amount;
	}
	@Override
	public int compareTo(Vendor b) {
		if(this.price!=b.price)
			return this.price - b.price;
		
		return b.amount-this.amount;
	}
}
public class milk {
	
		
	private static int go(int N, int M, List<Vendor> vendors) {
		Collections.sort(vendors);
		/*for(Vendor v : vendors)
		{
			System.out.println(v.price + " " + v.amount);
		}*/
		int amt = 0;
		for(int i = 0 ; N > 0 && i < M; ++i)
		{
			int qt = Math.min(N, vendors.get(i).amount);
			amt += qt * vendors.get(i).price;
			N-=qt;
		}
		return amt;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scr = new Scanner(new BufferedReader(new FileReader("milk.in")));
		int N = scr.nextInt();
		int M = scr.nextInt();		
		List<Vendor> list = new ArrayList<Vendor>();
		while(scr.hasNext())
		{			
			Vendor v = new Vendor(scr.nextInt(), scr.nextInt());
			list.add(v);
		}
		
		scr.close();
		int res = go(N,M, list);
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		pout.println(res);
		System.out.println(res);
		pout.close();
	}

}
