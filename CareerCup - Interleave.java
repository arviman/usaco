/*
given a LinkedList like 1->2->3->4->5->6 
Modify it as: 
1->6->2->5->3->4
 */
import java.io.*;
import java.util.*;
public class butter {
  class Node{
    int data;
    Node next;

    Node(int d){
      data = d;
    }

    void print(PrintWriter pw){
      Node cur = this;
      while(cur != null){
        pw.print(cur.data + " ");
        cur = cur.next;
      }
    }

    void add(int n){
      Node newnode = new Node(n);
      Node cur = this;
      while(cur.next != null) {
        cur = cur.next;
      }
      cur.next = newnode;
    }

    protected Node clone(){

      Node nd = new Node(this.data);
      Node cur = this;
      while(cur.next != null){
        cur = cur.next;
        nd.add(cur.data);
      }
      return nd;
    }

    public Node reverse() {
      Node prev = null;
      Node cur = this;
      Node nxt;

      while(cur.next != null){
        nxt = cur.next;
        cur.next = prev;
        prev = cur;
        cur = nxt;
      }
      cur.next = prev;
      return cur;
    }
  }


  public void solve(int testNumber, Scanner in, PrintWriter out) {
    Node nd=null;
    if(in.hasNext())
      nd = new Node(Integer.parseInt(in.next()));
    int cnt = 1;
    while(in.hasNext()){
      int num = Integer.parseInt(in.next());
      nd.add(num);
      ++cnt;
    }

    Node gimme = interleave(nd,out, cnt);
    gimme.print(out);
    out.close();
  }

  private Node interleave(Node nd, PrintWriter out, int n) {
    Node rev = nd.clone().reverse();
    Node nl = new Node(nd.data);
    int cnt = 1;

    Node s1 = nd.next;
    Node r1 = rev;
    while(r1.next != null){
      nl.add(r1.data);
      if(++cnt == n)break;
      nl.add(s1.data);
      if(++cnt == n)break;

      r1=r1.next;
      s1=s1.next;
    }

    return nl;
  }

}
