#include <bits/stdc++.h>
using namespace std;

static const int MOD = 998244353;

int add(int a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
    return a;
}

int sub(int a, int b) {
    a -= b;
    if (a < 0) a += MOD;
    return a;
}

int mul(long long a, long long b) {
    return (int)( (a * b) % MOD );
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--) {
        int n, k;
        cin >> n >> k;

        vector<vector<int>> children(n + 1);
        for (int i = 2; i <= n; i++) {
            int p;
            cin >> p;
            children[p].push_back(i);
        }

        // compute depths
        vector<int> depth(n + 1, 0);
        queue<int> q;
        q.push(1);
        while (!q.empty()) {
            int u = q.front(); q.pop();
            for (int v : children[u]) {
                depth[v] = depth[u] + 1;
                q.push(v);
            }
        }

        // count nodes per depth
        int maxD = 0;
        vector<int> cnt(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            cnt[depth[i]]++;
            maxD = max(maxD, depth[i]);
        }

        vector<int> dp(maxD + 1, 0);
        dp[0] = 1;

        for (int d = 1; d <= maxD; d++) {
            long long sum = 0;
            for (int x = max(0, d - k); x <= d - 1; x++) {
                sum += dp[x];
            }
            sum %= MOD;

            // greedy correction
            sum = sub(sum, dp[d - 1]);

            dp[d] = mul(sum, cnt[d]);
        }

        int ans = 0;
        for (int d = 0; d <= maxD; d++) {
            ans = add(ans, dp[d]);
        }

        cout << ans << "\n";
    }
    return 0;
}
