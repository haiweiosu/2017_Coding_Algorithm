/*There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, 
for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, 
return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.*/


public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            adjList.computeIfAbsent(prerequisites[i][1], k -> new LinkedList<>()).add(prerequisites[i][0]);
        }
        
        //BFS Search
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[count++] = course;
            List<Integer> courses = adjList.get(course);
            if (courses != null) {
                for (int k : courses) {
                    inDegree[k]--;
                    if (inDegree[k] == 0) {
                        queue.offer(k);
                    }
                }
            }
        }
        if (count < numCourses) {
			return new int[0];
		}
		return res;
        
    }
}