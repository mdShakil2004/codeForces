#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// Function to calculate the median of a vector
/*
double calculateMedian(vector<int>& arr) {
    int n = arr.size();
    sort(arr.begin(), arr.end());

    // If size is even, return average of middle two elements
    if (n % 2 == 0)
        return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;

    // If size is odd, return middle element
    return arr[n / 2];
}
*/

// Function to calculate the median of a vector using QuickSelect

double quickSelect(vector<int> &arr, int left, int right, int k)
{
    if (left == right)
        return arr[left];

    int pivotIndex = partition(arr, left, right);

    if (k == pivotIndex)
        return arr[k];
    else if (k < pivotIndex)
        return quickSelect(arr, left, pivotIndex - 1, k);
    else
        return quickSelect(arr, pivotIndex + 1, right, k);
}

int partition(vector<int> &arr, int left, int right)
{
}