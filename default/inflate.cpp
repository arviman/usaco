/*
ID: arviman1
PROG: inflate
LANG: C++
*/
//#include <iostream>
#include <fstream>
using namespace std;
ifstream cin("inflate.in");
ofstream cout("inflate.out");
 



int main()
{
	int m,n,min[10001],pts[10001];
    cin >> m >> n;
	for(int i = 0 ; i < n; ++i)
	  cin >>pts[i] >> min[i];
	int rem = m;
	int res[rem+1] = {0};		
    for(int i = 1; i <= rem; ++i){
      for(int j = 0 ; j < n; ++j){
        if(i-min[j]>=0){
          int val = res[i-min[j]] + pts[j];
          if(val > res[i])
            res[i] = val;
        }
      }
    }
    cout << res[m] << endl;
    return 0;
}