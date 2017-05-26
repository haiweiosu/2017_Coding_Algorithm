/*Given an array of integers sorted in ascending order, 
find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        //corner cases
        if (nums == null || nums.length == 0 || target < Integer.MIN_VALUE || target > Integer.MAX_VALUE) {
            int[] cornerResult = new int[]{-1,-1};
            return cornerResult;
        }
        
        int[] result = new int[2];
        
        int firstPosition = getFirstOne(nums, target);
        int lastPosition = getLastOne(nums, target);
        result[0] = firstPosition;
        result[1] = lastPosition;
        
        return result;
    }
        
    private int getFirstOne(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
    
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else{
                end = mid;
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        
        return -1;
    }
        
    private int getLastOne(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
    
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else{
                start = mid;
            }
        }
        
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        
        return -1;
    }
}