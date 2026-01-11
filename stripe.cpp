#include<bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    vector<long long> arr(n);
    long long total_sum = 0;
    
    for(int i = 0; i < n; i++){
        cin >> arr[i];
        total_sum += arr[i];
    }
    
    // If total sum is odd, no valid cut exists
    if(total_sum % 2 != 0){
        cout << 0 << endl;
        return 0;
    }
    
    long long target = total_sum / 2;
    long long count = 0;
    long long prefix_sum = 0;
    
    // We can cut after positions 0 to n-2
    // (cutting after position i means left piece has i+1 elements)
    for(int i = 0; i < n - 1; i++){
        prefix_sum += arr[i];
        if(prefix_sum == target){
            count++;
        }
    }
    
    cout << count << endl;
    
    return 0;
}