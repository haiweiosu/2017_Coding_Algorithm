/*Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)*/

public class Solution {
    public int splitArray(int[] nums, int m) {
        //corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        //get the sum of array and the max element
        int sum = 0, max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (max <= nums[i]) {
                max = nums[i];
            }
        }
        
        //using binary search with left = max element and right = all sum of array
        int left = max, right = sum;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (validSub(nums, m, mid)) {
                right = mid;
            } else if (!validSub(nums, m, mid)) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    //check if current array can be continue to divide
    private boolean validSub(int[] nums, int m, int mid) {
        int subCount = 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > mid) {
                sum = nums[i];
                subCount++;
                
                if (subCount > m) {
                    return false;
                }
            }
        }
        
        return true;
    }
}