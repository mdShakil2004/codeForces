

#include <bits/stdc++.h>
using namespace std;

static const int MAXN = 500000 + 5;
static const uint64_t BASE = 91138233;

int n, q;
string s;

/* ---------------- Manacher ---------------- */
int d1[MAXN], d2[MAXN];

/* ---------------- Rolling Hash ---------------- */
uint64_t pw[MAXN], hsh[MAXN];

uint64_t get_hash(int l, int r) {
    return hsh[r] - hsh[l - 1] * pw[r - l + 1];
}

/* ---------------- Palindrome Storage ---------------- */
struct Pal {
    int l;
    uint64_t h;
};

unordered_map<uint64_t, vector<int>> palpos[MAXN]; 
// palpos[L][hash] = sorted start positions

/* ---------------- Queries ---------------- */
struct Query {
    int l, r, id;
};
vector<Query> qs;
int ans[MAXN];

/* ---------------- Check Function ---------------- */
bool check(int L, const Query &Q) {
    int left = Q.l;
    int right = Q.r - L + 1;
    if (right < left) return false;

    for (auto &kv : palpos[L]) {
        auto &v = kv.second;
        int cnt = upper_bound(v.begin(), v.end(), right)
                - lower_bound(v.begin(), v.end(), left);
        if (cnt >= 2) return true;
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q;
    cin >> s;
    s = " " + s;

    /* -------- Manacher odd -------- */
    for (int i = 1, l = 1, r = 0; i <= n; i++) {
        int k = (i > r) ? 1 : min(d1[l + r - i], r - i + 1);
        while (i - k >= 1 && i + k <= n && s[i - k] == s[i + k]) k++;
        d1[i] = k;
        if (i + k - 1 > r) {
            l = i - k + 1;
            r = i + k - 1;
        }
    }

    /* -------- Manacher even -------- */
    for (int i = 1, l = 1, r = 0; i <= n; i++) {
        int k = (i > r) ? 0 : min(d2[l + r - i + 1], r - i + 1);
        while (i - k - 1 >= 1 && i + k <= n && s[i - k - 1] == s[i + k]) k++;
        d2[i] = k;
        if (i + k - 1 > r) {
            l = i - k;
            r = i + k - 1;
        }
    }

    /* -------- Rolling Hash Precompute -------- */
    pw[0] = 1;
    for (int i = 1; i <= n; i++) {
        pw[i] = pw[i - 1] * BASE;
        hsh[i] = hsh[i - 1] * BASE + (s[i] - 'a' + 1);
    }

    /* -------- Collect Palindromes -------- */
    for (int i = 1; i <= n; i++) {
        for (int k = 1; k <= d1[i]; k++) {
            int L = 2 * k - 1;
            int l = i - k + 1;
            uint64_t h = get_hash(l, l + L - 1);
            palpos[L][h].push_back(l);
        }
        for (int k = 1; k <= d2[i]; k++) {
            int L = 2 * k;
            int l = i - k;
            uint64_t h = get_hash(l, l + L - 1);
            palpos[L][h].push_back(l);
        }
    }

    /* -------- Sort Positions -------- */
    for (int L = 1; L <= n; L++) {
        for (auto &kv : palpos[L]) {
            sort(kv.second.begin(), kv.second.end());
        }
    }

    /* -------- Read Queries -------- */
    qs.resize(q);
    for (int i = 0; i < q; i++) {
        cin >> qs[i].l >> qs[i].r;
        qs[i].id = i;
    }

    /* -------- Solve Queries -------- */
    for (auto &Q : qs) {
        int lo = 1, hi = Q.r - Q.l + 1, best = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(mid, Q)) {
                best = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        ans[Q.id] = best;
    }

    /* -------- Output -------- */
    for (int i = 0; i < q; i++) {
        cout << ans[i] << "\n";
    }
    return 0;
}
