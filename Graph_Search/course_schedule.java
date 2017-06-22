/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.*/

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] inDegree = new int[numCourses];
        
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        
        //put each prerequite with its classes to follow
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            adjList.computeIfAbsent(prerequisites[i][1], k -> new LinkedList<>()).add(prerequisites[i][0]);
        }
        
        //BFS search
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            if (adjList.get(course) != null) {
                for (int k : adjList.get(course)) {
                    inDegree[k]--;
                    if (inDegree[k] == 0) {
                        queue.offer(k);
                    }
                }
            }
        }
        
        return count == numCourses;
    }
}