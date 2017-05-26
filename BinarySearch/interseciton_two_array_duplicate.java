public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        //corner case
        if (nums1 == null || nums2 == null ||
            nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Map<Integer, Integer> map1 = new HashMap<>();
        
        for (int i = 0; i < nums1.length; i++) {
            if (!map1.containsKey(nums1[i])) {
                map1.put(nums1[i], 1);
            } else{
                int freq = map1.get(nums1[i]);
                freq += 1;
                map1.put(nums1[i], freq);
            }
        }
        
        int size = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map1.containsKey(nums2[i]) && map1.get(nums2[i]) > 0) {
                size += 1;
                list.add(nums2[i]);
                map1.put(nums2[i], map1.get(nums2[i]) - 1);
            }
        }
        
        int[] result = new int[list.size()];
        int i = 0; 
        for (Integer element : list) {
            result[i++] = element;
        }
        
        return result;
        
    }
}