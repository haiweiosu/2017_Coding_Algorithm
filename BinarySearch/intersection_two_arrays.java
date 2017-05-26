public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        //corner case
        if (nums1 == null || nums2 == null || 
            nums1.length == 0 || nums2.length == 0) {
            new ArrayList<>();
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            }
        }
        
        for (int num: nums2) {
            if (map.get(num) != null) {
                set.add(num);
            }
        }
        
        int size = set.size();
        int[] result = new int[size];
        int i = 0;
        
        for (Integer element : set) {
            result[i++] = element;
        }
        
        return result;
    }
}