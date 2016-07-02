/*
ID: arviman1
LANG: JAVA
TASK: msquare
*/

import java.util.*;
import java.io.*;

public class msquare {
	public static void main(String[] args) {
		
		File inf=null;
		File outf=null;
		Scanner in=null;
		PrintWriter out=null;
		try{
		inf= new File("msquare.in"); outf = new File("msquare.out");
		in = new Scanner(inf);
        out = new PrintWriter(outf);
		}catch(Exception e){
		}
		
		msquare solver = new msquare();
		solver.solve(1, in, out);
		out.close();
		
	}
    public void solve(int testNumber, Scanner in, PrintWriter out) {
		
		int target = 0;
		for(int i=0;i<8;++i){
			target*=10;
			target+=in.nextInt();
		}	
		
		String ret = solveme(target);
		out.println(ret.length());
		out.println(ret);
		
    }
	
	public String solveme(int target){		
		
		Node start = new Node();
		Queue<Node> q = new LinkedList<Node>();
		HashSet<Integer> set = new HashSet<Integer>();
		q.add(start);
		set.add(start.val);

		outer:while(!q.isEmpty()){
		  Node p = q.remove();		  
		  if(p.val == target){						
			  return p.seq;
		  }		  
		  for(Node t : p.GetTransforms())   
		  {		  
		    if(!set.contains(t.val)){
			  q.add(t);		  
			  set.add(t.val);
			}
		  }
		}
		return "";
	}
	
	
	
	class Node{
	  public String seq;
	  public Integer val;
	  Node(){
	    seq=""; val = 12345678;
	  }
	  Node(String s, Integer v){
	    val = v; seq = s;
	  }
	  public Node[] GetTransforms(){ //lex order is maintained here
		Node[] ret = { new Node( seq+"A", A(val)), new Node( seq+"B", B(val)), new Node( seq+"C", C(val)) };
		return ret;
	  }
	  
	int A(int num){//12345678 -> 87654321
		int rnum = 0;
		while(num != 0){
			rnum = rnum*10 + num%10;
			num/=10;
		}
		return rnum;
	}	
	int B(int num){//12345678 -> 41236785
	  int first = num / 10000;
	  int second = num % 10000;	  
	  first = (first%10)*1000 +(first/10);
	  second = (second%1000) * 10 + second / 1000;
	  return first * 10000 + second;	
	}
	int C(int number){//1234 5678 -> 1724 5368
		  int result = 0;
		  result = number - number % 10000000;
		  result += (number % 100 - number % 10) * 100000;
		  result += (number % 10000000 - number % 1000000) / 10;
		  result += number % 100000 - number % 1000;
		  result += (number % 1000000 - number % 100000) / 1000;
		  result += (number % 1000 - number % 100) / 10;
		  result += number % 10;
		  return result;
	}
	}


}