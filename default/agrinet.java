/*
ID: arviman1
LANG: JAVA
TASK: agrinet
 */
import java.io.*;
import java.util.*;


public class agrinet {
    public void solve(int testNumber, Scanner in, PrintWriter out) {

      int n = in.nextInt();
      int[][] dist = new int[n][n];
      for(int i = 0 ; i < n; ++i)
        for(int j = 0 ; j < n; ++j)
          dist[i][j] = in.nextInt();

      boolean[] vis = new boolean[n];
      int[] dis = new int[n];
      Arrays.fill(dis, Integer.MAX_VALUE);
      vis[0] = true;
      dis[0] = 0;
      int treecost = 0 ;

      for(int i = 0 ; i < n; ++i){
        if(dist[0][i] != 0){
          dis[i] = dist[0][i];
        }
      }
      for(int added = 0 ; added < n; ++added){
        int minValue = Integer.MAX_VALUE;
        int mini=-1;
        for(int i = 0 ; i < n; ++i){
          if(!vis[i]){
            if(dis[i] != Integer.MAX_VALUE && dis[i] < minValue){
              mini = i;
              minValue = dis[i];
            }
          }
        }
        if(mini==-1)continue;
        vis[mini] = true;
        treecost += dis[mini];
        for(int j = 0 ; j < n; ++j){
          if(dis[j] > dist[mini][j]){
            dis[j] = dist[mini][j];
          }
        }
      }

      out.println(treecost);

    }
}
