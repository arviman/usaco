
import java.io.*;
import java.util.*;
/**
 * 
 */
/*
ID: arviman1
LANG: JAVA
TASK: packrec
 */

class Rect implements Comparable<Rect>
{
	int h; 
	int w;
	public Rect(int height, int width)
	{
		h=height;
		w=width;
	}
	public Rect getFlip(boolean flip)
	{
		return flip ? new Rect(w, h) : this;
	}
	@Override
	public String toString()
	{
		return "H:"+this.h + " W:" + this.w ;		
	}
	@Override
	public int compareTo(Rect o) {
		int p1 = Math.min(h,w);
		int p2 = Math.min(o.h,o.w);
		if(p1!=p2)
			return p1-p2;
		return Math.max(h,w) - Math.max(o.h,o.w)  ;
	}
	
	public boolean equals(Rect o)
	{
		return Math.min(h,w) == Math.min(o.h,o.w) && Math.max(h,w) == Math.max(o.h,o.w);
	}
}
class MainResult
{
	int area;
	List<Rect> rects;
	public MainResult(int area, List<Rect> rects)
	{
		this.area = area;
		this.rects = rects;
	}
}
public class packrec {
	
	private static void addUnique(List<Rect> rect, Rect r)
	{		
		for(Rect re : rect)
		{
			if(re.equals(r))
			{
				return;
			}
		}
		rect.add(r);
	}
	private static MainResult getSmallestEnclosure(List<Rect> rect)
	{
		
		assert rect.size() == 4;
		Rect[] r = rect.toArray(new Rect[4]);
		int[] W = new int[6];
		//Arrays.fill(W,999);
		int[] H = new int[6];
		//Arrays.fill(H,999);
		for(Rect re : r)
		{
			W[0]+=re.w;
			H[0] = Math.max(H[0], re.h );
		}
		W[1] = Math.max(r[1].w + r[2].w + r[3].w, r[0].w);
		H[1] = Math.max(Math.max(r[1].h,r[2].h),r[3].h) + r[0].h;

		W[2] = r[3].w + Math.max(r[0].h, r[1].w + r[2].w);
		H[2] = Math.max(r[3].h, r[0].w + Math.max(r[1].h, r[2].h));
		
		W[3] = r[0].w + Math.max(r[1].w, r[2].w)+ r[3].w;
		H[3] = Math.max(Math.max(r[0].h, r[1].h + r[2].h), r[3].h);
		
		W[4] = Math.max(r[0].w,  r[1].w) + r[2].w + r[3].w;
		H[4] = Math.max(Math.max(r[0].h + r[1].h, r[2].h), r[3].h);
		
		
		W[5] = r[0].w + r[1].w; //bottom two 
		
		if(r[0].h + r[2].h < r[1].h) //if height of left two is less than height of bottom right
		{			
			W[5] = Math.max(Math.max(W[5], r[3].w), r[1].w + r[2].w);
			//System.out.println("LLLLL " + W[5]);
		}
		else if(r[1].h + r[3].h < r[0].h){ //if height of right two is less than height of bottom left
			//System.out.println("RRRRRRR " + W[5]);
			W[5] = Math.max(Math.max(W[5], r[2].w), r[0].w + r[3].w);
		}
		else if(r[1].h > r[0].h)
		{
			W[5] = Math.max(W[5], Math.max(r[1].w, r[3].w) + r[2].w);
		}
		else if(r[1].h < r[0].h)
		{
			W[5] = Math.max(W[5], Math.max(r[0].w, r[2].w) + r[3].w);
		}
		else
			W[5] = Math.max(W[5], r[2].w + r[3].w);
		
		/*else
		{
			System.out.println("BEFORE " + W[5]);
			W[5] = Math.max(W[5], r[2].w + r[3].w); //top two
			System.out.println("INTERSECT " + W[5]);
		}*/
		H[5] = Math.max(r[0].h + r[2].h, r[1].h + r[3].h);
		
		List<Rect> ret = new ArrayList<Rect>();
		int min = Integer.MAX_VALUE;
		for(int i = 0 ;i < 6; ++i)
		{
			int a = W[i]*H[i];
			if(a == 35 && a < min)
			{
				for(Rect rec : rect)
				{
					System.out.println(rec.toString());
				}
				//System.out.println( " AT FIGURE " + i + " with height of " + H[i] + " and width of " + W[i]);
				//System.out.println("max of " + (r[0].w + r[1].w) + " and " +  (r[2].w + r[3].h));
				//System.out.println("max of " + (r[0].h + r[2].h) + " and " +  ( Math.max(r[0].h,r[1].h) + r[3].w));
			}
				
			if(a < min)
			{
				min = a;
				ret.clear();
				ret.add(new Rect(H[i], W[i]));
			}
			else if(a == min)
			{
				addUnique(ret,new Rect(H[i], W[i]) );				
			}
		}
		return new MainResult(min, ret);
		
	}
	
	private static MainResult go(List<Rect> rects) throws Exception
	{
		List<Rect> resR = null;
		int resArea = Integer.MAX_VALUE;
		for(int a = 0; a < 4; ++a)
			for(int b = 0; b < 4; ++b)if(a!=b)			
				for(int c = 0; c < 4; ++c)if(a!=c && b!= c)
				{
					int d = 6 - (a+b+c);
					List<Rect> input = new ArrayList<Rect>();
					
					for(int r1=0; r1<2; ++r1)
						for(int r2=0; r2<2; ++r2)
							for(int r3=0; r3<2; ++r3)
								for(int r4=0; r4<2; ++r4)
								{
									input.add(rects.get(a).getFlip(r1 == 1) );
									input.add(rects.get(b).getFlip(r2 == 1));
									input.add(rects.get(c).getFlip(r3 == 1));
									input.add(rects.get(d).getFlip(r4 == 1));
									MainResult tres = getSmallestEnclosure(input);
									input.clear();
									if(tres.area < resArea)
									{
										resArea = tres.area;
										resR = new ArrayList<Rect>(tres.rects);
									}
									else if(tres.area == resArea)
									{				
										for(Rect tr: tres.rects)
											addUnique(resR, tr);										
									}
								}										
				}
		return new MainResult(resArea, resR);
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String taskname = "packrec";
		Scanner scr = new Scanner(new BufferedReader(new FileReader(taskname + ".in")));		
		List<Rect> rects = new ArrayList<Rect>();
		while(scr.hasNextInt())
		{
			rects.add(new Rect(scr.nextInt(), scr.nextInt()));
		}
		scr.close();		
		MainResult res = go(rects);
		Collections.sort(res.rects);
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter(taskname + ".out")));
		pout.println(res.area);
		System.out.println(res.area);
		
		for(Rect resRect : res.rects)
		{
			String line = Math.min(resRect.w, resRect.h) + " " + Math.max(resRect.w, resRect.h);
			pout.println(line);
			System.out.println(line);
		}
		
		
		pout.close();
	}

}
