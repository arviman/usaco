/*
ID: arviman1
LANG: JAVA
TASK: fracdec
 */
import java.io.*;
import java.util.*;

   //3 | 10 --->    3 | 3

public class fracdec {

  private void add(StringBuilder q, String toAdd){
    q.append(toAdd);
  }


  private String generateQuotientFor(int d, int rem) {
    StringBuilder q = new StringBuilder();
    int org = rem;

    out:do{
      while(rem < d){
        rem *= 10;
        if(rem < d)
          add(q, "0");
        if(rem == org)
          break out;
      }
      //store rem to a list todo
      add(q, ""+ rem / d);
      rem = rem % d;
    }while (org != rem);
    return q.toString();
  }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
      //out.println(generateQuotientFor(9817, 18300));
      //out.close();
      //return;

      int n = in.nextInt();
      int d = in.nextInt();
      StringBuilder q = new StringBuilder(""+ n/d);
      int rem = n%d;
      Set<Integer> reminders = new TreeSet<Integer>();
      //track reminders..if u hit..recalc quotient from reminder and then return answer in original
      if(rem != 0)
        q.append(".");
      else q.append(".0");

      out:while(rem != 0){
        int trem = rem;
        boolean foundTrem = false;
        while(trem %10 ==0) {
          trem /= 10;
          if(reminders.contains(trem)){
            foundTrem = true;
            break;
          }
        }
        if(reminders.contains(rem) || foundTrem){
          reminders.clear();//make heap space
          String val = foundTrem? generateQuotientFor(d, trem) : generateQuotientFor(d, rem);
          int toReplaceFrom = q.lastIndexOf(val);
          q = new StringBuilder(q.substring(0, toReplaceFrom) + "("+val+")");
          break;
        }

        reminders.add(rem);

        while(rem < d){
          rem *= 10;
          if(rem < d) {
            add(q, "0");
          }
          if(reminders.contains(rem)){
            reminders.clear();
            String val = generateQuotientFor(d, rem);
            int toReplaceFrom = q.lastIndexOf(val);
            q = new StringBuilder(q.substring(0, toReplaceFrom) + "("+val+")");
            break out;
          }
          reminders.add(rem);

        }

        add(q, ""+rem / d);
        rem = rem % d;

      }
      int len = q.length();
      for(int l = 0; l < len; l+=76)
      {
        out.println(q.substring(l, Math.min(l+76, len)));
      }
      in.close();
      out.close();
    }


}
