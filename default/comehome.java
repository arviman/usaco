/*
ID: arviman1
LANG: JAVA
TASK: comehome
 */

/*
It's dinner time, and the cows are out in their separate pastures. Farmer John rings the bell so they will start walking to the barn. Your job is to figure out which one cow gets to the barn first (the supplied test data will always have exactly one fastest cow).

Between milkings, each cow is located in her own pasture, though some pastures have no cows in them. Each pasture is connected by a path to one or more other pastures (potentially including itself). Sometimes, two (potentially self-same) pastures are connected by more than one path. One or more of the pastures has a path to the barn. Thus, all cows have a path to the barn and they always know the shortest path. Of course, cows can go either direction on a path and they all walk at the same speed.

The pastures are labeled `a'..`z' and `A'..`Y'. One cow is in each pasture labeled with a capital letter. No cow is in a pasture labeled with a lower case letter. The barn's label is `Z'; no cows are in the barn, though.

PROGRAM NAME: comehome

INPUT FORMAT

Line 1:	Integer P (1 <= P <= 10000) the number of paths that interconnect the pastures (and the barn)
Line 2..P+1:	Space separated, two letters and an integer: the names of the interconnected pastures/barn and the distance between them (1 <= distance <= 1000)
SAMPLE INPUT (file comehome.in)

5
A d 6
B d 3
C e 9
d Z 8
e Z 3
OUTPUT FORMAT

A single line containing two items: the capital letter name of the pasture of the cow that arrives first back at the barn, the length of the path followed by that cow.
SAMPLE OUTPUT (file comehome.out)

B 11
 */
import java.io.*;
import java.util.*;
//import javafx.util.Pair;


public class comehome {


  public void solve(int testNumber, Scanner in, PrintWriter out) {
    //solveFW(testNumber,in, out);return;
    int n = in.nextInt();
    //int[][] d = new int[n+1][n+1];

    HashMap<Integer, List<Pair<Integer,Integer>>> d = new HashMap<Integer, List<Pair<Integer,Integer>>>();

    int[] dist = new int[n+1];
    boolean[] v = new boolean[n+1];
    boolean[] c = new boolean[n+1];
    Arrays.fill(dist, Integer.MAX_VALUE);

    HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    int nameToken = 0;
    for(int i = 0 ; i < n; ++i)
    {
      char a = in.next().charAt(0);
      char b = in.next().charAt(0);
      int val = Integer.parseInt(in.next());
      int an, bn;

      if(!map.containsKey(a)) {
        if(a >= 'A' && a <'Z')
          c[nameToken] = true;
        map.put(a, an = nameToken);

        ++nameToken;
      }
      else
        an = map.get(a);

      if(!map.containsKey(b)) {
        if(b >= 'A' && b <'Z')
          c[nameToken] = true;
        map.put(b, bn = nameToken);

        ++nameToken;
      }
      else
        bn = map.get(b);

      setInMap(d, val, bn, an);
      setInMap(d, val, an, bn);

    }
    int zi = map.get('Z');
    dist[zi] = 0;
    for(int i = 0 ; i <= n; ++i) {
      int nxt = getVertex(dist, v);
      v[nxt] = true;

      Iterator<Pair<Integer, Integer>> iter = d.get(nxt).iterator();
      while(iter.hasNext()) {
        Pair<Integer,Integer> pr = iter.next();
        dist[pr.getFirst()] = Math.min(dist[pr.getFirst()], dist[nxt] + pr.getSecond());
      }
    }

    char retChar='Z'; int retVal = Integer.MAX_VALUE; int besti = 0;
    for(int i = 0 ; i <= n; ++i){
      if(c[i]){
        if(dist[i] < retVal) {
          retVal = dist[i];
          besti = i;
        }
      }
    }

    for(Character k : map.keySet())
    {
      if(map.get(k) == besti){
        retChar = k; break;
      }
    }

    out.printf("%c %d\n", retChar, retVal);

    out.close();
  }

  private void setInMap(HashMap<Integer, List<Pair<Integer, Integer>>> d, int weight, int val, int key) {
    if(d.containsKey(key)){
      List<Pair<Integer,Integer>> lst = d.get(key);
      Pair<Integer,Integer> pr = new Pair<Integer,Integer>(val, weight);
      lst.add(pr);
    }else{
      List<Pair<Integer,Integer>> lst = new ArrayList<Pair<Integer, Integer>>();
      lst.add(new Pair<Integer,Integer>(val, weight));
      d.put(key,lst);
    }
  }

  private int getVertex(int[] dist, boolean[] v) {
    int n = dist.length;
    int mini = 0;
    int minval = Integer.MAX_VALUE;
    for(int i = 0; i < n; ++i)    {
      if(!v[i] && dist[i] <minval){
        mini = i; minval = dist[i];
      }
    }
    return mini;
  }

  public void solveFW(int testNumber, Scanner in, PrintWriter out) {
    int n = in.nextInt();
    int[][] d = new int[n+1][n+1];
    int[][] dist = new int[n+1][n+1];

    boolean[] c = new boolean[n+1];
    for(int i = 0 ; i <= n; ++i)
      Arrays.fill(d[i], Integer.MAX_VALUE);
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    HashMap<Integer, Character> revmap = new HashMap<Integer, Character>();

    int nameToken = 0;
    for(int i = 0 ; i < n; ++i)
    {
      char a = in.next().charAt(0);
      char b = in.next().charAt(0);
      int val = Integer.parseInt(in.next());
      int an, bn;

      if(!map.containsKey(a)) {
        if(a >= 'A' && a <'Z')c[nameToken] = true;
        map.put(a, an = nameToken);
        revmap.put(nameToken, a);
        ++nameToken;
      }
      else
        an = map.get(a);

      if(map.containsKey(b))
        bn = map.get(b);
      else {
        if(b >= 'A' && b <'Z')c[nameToken] = true;
        map.put(b, bn = nameToken);
        revmap.put(nameToken, b);
        ++nameToken;
      }
      d[an][bn] = d[bn][an] = val;
    }

    for(int i = 0 ; i <= n; ++i)
      for(int j = 0 ; j <= n; ++j)
        dist[i][j] = d[i][j];


    for(int k = 0 ; k <= n; ++k)
      for(int i = 0 ; i <= n; ++i)
        for(int j = 0 ; j <= n; ++j)
          if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE)
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);


    int smallest = Integer.MAX_VALUE;
    int smallesti = -1;

    int zi = map.get('Z');

    for(int i = 0 ; i <= n; ++i) {
      if (c[i] && smallest > dist[i][zi]){
        smallesti = i;
        smallest = dist[i][zi];
      }
    }
    out.printf("%c %d\n", revmap.get(smallesti) , smallest);

    out.close();
  }

  public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
      super();
      this.first = first;
      this.second = second;
    }

    public int hashCode() {
      int hashFirst = first != null ? first.hashCode() : 0;
      int hashSecond = second != null ? second.hashCode() : 0;

      return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
      if (other instanceof Pair) {
        Pair otherPair = (Pair) other;
        return
            ((  this.first == otherPair.first ||
                ( this.first != null && otherPair.first != null &&
                    this.first.equals(otherPair.first))) &&
                (	this.second == otherPair.second ||
                    ( this.second != null && otherPair.second != null &&
                        this.second.equals(otherPair.second))) );
      }

      return false;
    }

    public String toString()
    {
      return "(" + first + ", " + second + ")";
    }

    public A getFirst() {
      return first;
    }

    public void setFirst(A first) {
      this.first = first;
    }

    public B getSecond() {
      return second;
    }

    public void setSecond(B second) {
      this.second = second;
    }
  }

}
