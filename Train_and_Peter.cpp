#include<bits/stdc++.h>
using namespace std;

// Function to check if we can find seq1 followed by seq2 in the given string
// They should not overlap but there can be a gap between them
bool canSeeSequences(const string& flags, const string& seq1, const string& seq2) {
    int n = flags.length();
    int len1 = seq1.length();
    int len2 = seq2.length();
    
    // Try to find seq1 at each possible position
    for (int i = 0; i <= n - len1; i++) {
        // Check if seq1 matches at position i
        if (flags.substr(i, len1) == seq1) {
            // Now try to find seq2 after seq1 ends
            // seq2 must start at position i + len1 or later
            for (int j = i + len1; j <= n - len2; j++) {
                if (flags.substr(j, len2) == seq2) {
                    return true;
                }
            }
        }
    }
    return false;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    string flags, seq1, seq2;
    cin >> flags >> seq1 >> seq2;
    
    // Check forward direction
    bool forward = canSeeSequences(flags, seq1, seq2);
    
    // Check backward direction (reverse the flags string)
    string reversedFlags = flags;
    reverse(reversedFlags.begin(), reversedFlags.end());
    bool backward = canSeeSequences(reversedFlags, seq1, seq2);
    
    if (forward && backward) {
        cout << "both" << endl;
    } else if (forward) {
        cout << "forward" << endl;
    } else if (backward) {
        cout << "backward" << endl;
    } else {
        cout << "fantasy" << endl;
    }
    
    return 0;
}