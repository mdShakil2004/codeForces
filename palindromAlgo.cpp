
/*  


Find the maximum length L such that there exists a palindrome of length L appearing at least twice inside substring s[l..r]

*/


#include <bits/stdc++.h>
using namespace std;

static const int MAXN = 500000 + 5;

int n, q;
string s;

// Manacher arrays
int d1[MAXN], d2[MAXN];

// Fenwick Tree
struct Fenwick {
    int n;
    vector<int> f;
    Fenwick(int n=0): n(n), f(n+1,0) {}
    void reset() { fill(f.begin(), f.end(), 0); }
    void add(int i, int v){
        for(; i<=n; i+=i&-i) f[i]+=v;
    }
    int sum(int i){
        int s=0;
        for(; i>0; i-=i&-i) s+=f[i];
        return s;
    }
    int range(int l,int r){
        if(l>r) return 0;
        return sum(r)-sum(l-1);
    }
};

vector<pair<int,int>> palEnd[MAXN]; // for each length L: (end_pos, start_pos)
vector<tuple<int,int,int>> queries[MAXN]; // per length L: (r, l, idx)
int ans[MAXN];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q;
    cin >> s;
    s = " " + s; // 1-indexed

    // -------- Manacher (odd) --------
    for(int i=1,l=1,r=0;i<=n;i++){
        int k = (i>r)?1:min(d1[l+r-i], r-i+1);
        while(i-k>=1 && i+k<=n && s[i-k]==s[i+k]) k++;
        d1[i]=k;
        if(i+k-1>r){
            l=i-k+1;
            r=i+k-1;
        }
    }

    // -------- Manacher (even) --------
    for(int i=1,l=1,r=0;i<=n;i++){
        int k = (i>r)?0:min(d2[l+r-i+1], r-i+1);
        while(i-k-1>=1 && i+k<=n && s[i-k-1]==s[i+k]) k++;
        d2[i]=k;
        if(i+k-1>r){
            l=i-k;
            r=i+k-1;
        }
    }

    // -------- Collect palindromes --------
    for(int i=1;i<=n;i++){
        for(int k=1;k<=d1[i];k++){
            int L = 2*k-1;
            int l = i-k+1;
            int r = i+k-1;
            palEnd[L].push_back({r,l});
        }
        for(int k=1;k<=d2[i];k++){
            int L = 2*k;
            int l = i-k;
            int r = i+k-1;
            palEnd[L].push_back({r,l});
        }
    }

    // -------- Read queries --------
    for(int i=1;i<=q;i++){
        int l,r;
        cin>>l>>r;
        int len = r-l+1;
        int maxL = len;
        for(int L=1;L<=maxL;L++){
            queries[L].push_back({r,l,i});
        }
    }

    Fenwick fw(n);

    // -------- Process each palindrome length --------
    for(int L=1;L<=n;L++){
        if(palEnd[L].empty() || queries[L].empty()) continue;

        sort(palEnd[L].begin(), palEnd[L].end());
        sort(queries[L].begin(), queries[L].end());

        fw.reset();
        int ptr = 0;

        for(auto &[qr,ql,idx]:queries[L]){
            while(ptr<(int)palEnd[L].size() && palEnd[L][ptr].first<=qr){
                fw.add(palEnd[L][ptr].second,1);
                ptr++;
            }
            int cnt = fw.range(ql,n);
            if(cnt>=2){
                ans[idx] = max(ans[idx], L);
            }
        }
    }

    // -------- Output --------
    for(int i=1;i<=q;i++){
        cout<<ans[i]<<"\n";
    }
    return 0;
}
