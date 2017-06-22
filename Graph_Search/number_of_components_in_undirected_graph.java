/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.*/


public class Solution {
    public int countComponents(int n, int[][] edges) {
        //corner case
        if (edges == null) {
            return 0;
        }
        
        //union find
        //1. n points = n islands = n trees = n roots.
        //2. With each edge added, check which island is e[0] or e[1] belonging to.
        //3. If e[0] and e[1] are in same islands, do nothing.
        //4. Otherwise, union two islands, and reduce islands count by 1.
        //5. Bonus: path compression can reduce time by 50%.
        
        int[] roots = new int[n];
        int res = n;
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        
        for (int[] e: edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            
            if (root1 != root2) {
                roots[root1] = root2;
                res--;
            }
        }
        
        return res;
    }
    
    private int find(int[] roots, int id) {
        while(roots[id] != id) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        
        return id;
    }
}