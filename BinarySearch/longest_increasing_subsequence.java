/*Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity. */

public class Solution {
    public int lengthOfLIS(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] LIS = new int[nums.length];
        int n = nums.length;
        int max = 0;
        
        //initial
        for (int i = 0; i < nums.length; i++) {
            LIS[i] = 1;
        }
        
        //general case
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
            if (LIS[i] > max) {
                max = LIS[i];
            }
        }
        
        return max;
    }
}