#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;
    while (t--) {
        string s;
        cin >> s;

        bool hasN = false;
        for (char c : s) {
            if (c == 'N') {
                hasN = true;
                break;
            }
        }

        if (hasN) cout << "YES\n";
        else cout << "NO\n";
    }
    return 0;
}
