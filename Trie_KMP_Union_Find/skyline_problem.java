/*A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), 
write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: 
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, 
is merely used to mark the termination of the skyline, and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:
[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].*/

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        //corner case
        if (buildings == null) {
            return new ArrayList<>();
        }
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        
        //we convert each rectangle representation as [top left corner, top right corner]
        for (int[] building: buildings) {
            //to differentiate to top left and top right corner point, top left is
            //negative and top right is positive
            height.add(new int[]{building[0], -building[2]});
            height.add(new int[]{building[1], building[2]});
        }
        
        //sort the res based on horizontal point
        Collections.sort(height, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] != b[0]){
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        });
        
        //create a priorityQueue to keep track of cur max height
        Queue<Integer> maxCurHeight = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2, i1);
            }
        });

        //Initialize 0 height at first
        maxCurHeight.offer(0);
        int prev = 0;
        for (int[] h: height) {
            if(h[1] < 0) {
                maxCurHeight.offer(-h[1]);
            } else {
                maxCurHeight.remove(h[1]);
            }
            
            int cur = maxCurHeight.peek();
            if (prev != cur) {
                res.add(new int[] {h[0], cur});
                prev = cur;
            }
        }
        
        return res;

    }
}