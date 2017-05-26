//Given a sorted array and a target value, 
//return the index if the target is found. 
//If not, return the index where it would be if it were inserted in order.
//You may assume no duplicates in the array

public class Solution {
    public int searchInsert(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0 || target < Integer.MIN_VALUE || target > Integer.MAX_VALUE) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length;
        
        while (start < end) {
        	int mid = start + (end - start)/2;
            
            if (nums[mid] >= target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid +1;
            }
        }
        return start;
        
    }
}