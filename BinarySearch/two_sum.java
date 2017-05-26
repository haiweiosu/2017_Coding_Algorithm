public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //corner cases
        if (nums == null || nums.length == 0 || 
        target < Integer.MIN_VALUE || 
        target > Integer.MAX_VALUE) {
            new ArrayList<>();
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int index1 = map.get(target - nums[i]);
                result[0] = index1;
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        
        return result;
    }
} 