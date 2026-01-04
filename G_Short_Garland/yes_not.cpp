#include <bits/stdc++.h>
using namespace std;


int checkEvenOrOdd(int n)
{
    if(n==0)
    {
        return 0;

    }
    if(n&1)
    {
        cout<<"odd";
    }
    else
    {
        cout<<"even";
    }
}
int main() {
    
       
    cout<<"enter any number ";
    int n; cin>>n;
    checkEvenOrOdd(n);
    return 0;
}
