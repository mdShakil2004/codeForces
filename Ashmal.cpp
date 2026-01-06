#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    
    while (t--) {
        int n;
        cin >> n;
        
        vector<string> a(n);
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        
        string s = "";
        
        for (int i = 0; i < n; i++) {
            // Compare adding to beginning vs end
            string add_begin = a[i] + s;
            string add_end = s + a[i];
            
            if (add_begin < add_end) {
                s = add_begin;
            } else {
                s = add_end;
            }
        }
        
        cout << s << "\n";
    }
    
    return 0;
}