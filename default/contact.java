/*
ID: arviman1
LANG: JAVA
TASK: contact
 */
import java.io.*;
import java.util.*;


public class contact {
  int A,B,outputN,n;
    public void solve(int testNumber, Scanner in, PrintWriter out) {

      A=in.nextInt();
      B=in.nextInt();
      outputN=in.nextInt();
      HashMap<String, Integer> hm = new HashMap<String, Integer>();
      StringBuilder sb = new StringBuilder();
      while(in.hasNext())
        sb.append(in.next());
      int n = sb.length();


      for(int s = 0; s <= n-A; ++s){
        for(int l = A; l <= B; ++l){
          if(s+l > n)
            break;
          String str = sb.substring(s, s+l);
          updateFrequency(hm, str);
        }
      }

      outputMost(hm, outputN, out);
      out.close();
    }

  private void outputMost(HashMap<String, Integer> hm, int n, PrintWriter out) {
    TreeSet<Item> ts = new TreeSet<Item>();

    Set<String> keys = hm.keySet();
    for(String key : keys){
      ts.add(new Item(hm.get(key),key));
    }

    int countForFreq = 0;
    int lastFrequency = ts.first().Frequency;
    boolean println=false;
    Item item;
    for(int i = 0; ts.size() > 0 ; ){
      println = false;
      ts.remove(item = ts.first());
      if(lastFrequency == item.Frequency)
        ++countForFreq;
      else {
        out.println();
        println = true;
        countForFreq = 1;
      }

      if(countForFreq == 1){
        ++i;
        if(i>n)break;
        out.println(item.Frequency);
        out.print(item.str);
        println = false;
      }
      else if((countForFreq-1) % 6 == 0){
        out.println();
        out.print(item.str);
        println = false;
      }
      else{
        out.print(" " + item.str);
        println = false;
      }

      lastFrequency = item.Frequency;
    }

    if(!println)
      out.print("\n");

  }
  public class Item implements Comparable{
    public int Frequency;
    public String str;

    Item(int f, String s){
      this.Frequency = f; this.str = s;
    }

    @Override
    public int compareTo(Object o) {
      Item other = (Item)o;
      if(this.Frequency < other.Frequency){
        return 1;
      }
      else if(this.Frequency > other.Frequency){
        return -1;
      }
      else{
        int myLength=this.str.length(), otherLength=other.str.length();
        if(myLength < otherLength)return -1;
        else if(myLength > otherLength)return 1;
        else{
          for(int i = 0; i < myLength; ++i){
            if(this.str.charAt(i) < other.str.charAt(i))
              return -1;
            else if(this.str.charAt(i) > other.str.charAt(i))
              return 1;
          }
          return 0;
        }
      }
    }
  }

  private void updateFrequency(HashMap<String, Integer> hm, String str) {
    if(hm.containsKey(str)){
      hm.put(str, hm.get(str)+1);
    }
    else{
      hm.put(str, 1);
    }
  }
}
