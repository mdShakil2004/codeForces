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
        
        vector<long long> v(n + 1);
        for (int i = 1; i <= n; i++) {
            cin >> v[i];
        }
        
        vector<int> a(n + 1), b(n + 1);
        for (int i = 1; i <= n; i++) {
            cin >> a[i];
        }
        for (int i = 1; i <= n; i++) {
            cin >> b[i];
        }
        
        // pos_b[obj] = position of obj in Bob's preference list
        vector<int> pos_b(n + 1);
        for (int i = 1; i <= n; i++) {
            pos_b[b[i]] = i;
        }
        
        // dp[i][j] = max value when considering first i from Alice, first j from Bob
        const long long NEG_INF = -1e18;
        vector<vector<long long>> dp(n + 1, vector<long long>(n + 1, NEG_INF));
        dp[0][0] = 0;
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] == NEG_INF) continue;
                
                // Alice takes a[i+1]
                if (i < n) {
                    int obj = a[i + 1];
                    int bob_pos = pos_b[obj];
                    if (bob_pos > j) {
                        // Bob hasn't reached this object yet, Alice can take it
                        dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] + v[obj]);
                    }
                }
                
                // Bob takes b[j+1] (if it's not already taken by Alice)
                if (j < n) {
                    dp[i][j + 1] = max(dp[i][j + 1], dp[i][j]);
                }
            }
        }
        
        long long ans = NEG_INF;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                ans = max(ans, dp[i][j]);
            }
        }
        
        cout << ans << "\n";
    }
    
    return 0;
}