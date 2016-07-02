/*
ID: arviman1
LANG: JAVA
TASK: spin
*/

import java.util.*;
import java.io.*;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Arvind
 */
public class spin {
	public static void main(String[] args) {
		
		File inf=null;
		File outf=null;
		Scanner in=null;
		PrintWriter out=null;
		try{
		inf= new File("spin.in"); outf = new File("spin.out");
		in = new Scanner(inf);
        out = new PrintWriter(outf);
		}catch(Exception e){
		}
		
		spin solver = new spin();
		solver.solve(1, in, out);
		out.close();
		
	}
    public void solve(int testNumber, Scanner in, PrintWriter out) {
		List<Wheel> wheels = new LinkedList<Wheel>();
		for(int i = 0 ; i < 5; ++i)
		{
			Wheel wheel = new Wheel(in.nextInt());
			int wedgeCnt = in.nextInt();
			for(int j=0; j < wedgeCnt; ++j){
			  wheel.AddWedge(in.nextInt(), in.nextInt());
			}
			wheels.add(wheel);
		}		
	  int times = 0;
	  times:for( ; times < 360; ++times){	  
		  out:for(int angle = 0 ; angle < 360; ++angle){
			boolean openAt = true;
			for(int i = 0 ; i < 5; ++i){   
				if(!wheels.get(i).OpenAt(angle)){
					openAt = false; break;
				}	  
			}		
			if(openAt){
			for(int i = 0 ; i < 5; ++i){   
				wheels.get(i).Print();
			}
			System.out.println("times : " + times + " angle: " + angle);
			
			  out.println(times); break times;
			}	  
		  }		  
		  for(int i = 0 ; i < 5; ++i)
			wheels.get(i).Move();	  
	  }          
      if(times==360) out.println("none");
    }
class Wedge
{
  public int start; public int end;
  Wedge(int Start, int width){
	start = Start; end = (Start+width)%360;	
	
  }
  
  void Increment(int amt){
	start += amt;
	end += amt;	
	start %= 360; end %= 360;
  }
}

class Wheel{
    public int speed;
	public List<Wedge> wedges;
	Wheel(int Speed){
	    this.speed=Speed;
		this.wedges = new LinkedList<Wedge>();		
		
	}	
	void Print(){
	  StringBuilder str = new StringBuilder();
	  for(int i = 0 ; i < wedges.size(); ++i){
		Wedge wedge=wedges.get(i);
		System.out.print(wedge.start+"-"+wedge.end + " ");
	  }
	  System.out.println();
	}
	void AddWedge(int start, int end){
	  wedges.add(new Wedge(start,end));
	}
	void Move(){
	  for(int i = 0 ; i < wedges.size(); ++i){
		wedges.get(i).Increment(speed);
	  }
	}	
	public boolean OpenAt(int angle){
		for(int i = 0 ; i < wedges.size(); ++i){
			Wedge w = wedges.get(i);
			
			if(w.start > w.end){
			  if( (angle >= w.start && angle <= 359) || (angle >=0 && angle <= w.end))
			    return true;				
			}			
			else{
			  if( angle >= w.start && angle <= w.end) 
			    return true;
			}
		}
		return false;
	}	
	public boolean IntersectsWith(Wheel o){
		for(int i = 0 ; i < o.wedges.size(); ++i){
			if(IntersectsWith(o.wedges.get(i)))
				return true;
		}
		return false;
	}	
	public boolean IntersectsWith(Wedge o){		
		for(int i = 0 ; i < wedges.size(); ++i){
			Wedge w = wedges.get(i);
			if(w.start <= o.end && w.end >= o.start)
			  return true;
		}
		return false;
	}
}
}