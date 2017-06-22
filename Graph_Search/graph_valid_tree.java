/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.*/

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        //corner cases
        if (edges.length != n - 1) {
            return false;
        }
        //By the definition of tree, it is in fact an acylic graph. 
        //Therefore, using union find to find if given edges can form a cycle. 
        //If yes, then NOT a valid tree; otherwise yes. 
        
        UnionFind u = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            u.id[i] = i;
            u.size[i] = 1;
        }
        
        for (int[] e : edges) {
            if (u.find(e[0], e[1])) {
                return false;
            }
            u.union(e[0], e[1]);
        }
        
        return u.count == 1;
        
    }
    
    class UnionFind {
        int[] id, size;
        int count;
        
        public UnionFind(int len) {
            this.id = new int[len];
            this.size = new int[len];
            this.count = len;
        }
        
        public boolean find(int p, int q) {
            return root(p) == root(q);
        }
        
        public void union(int p, int q) {
            int pi = root(p), qi = root(q);
            if (this.size[pi] < this.size[qi]) {
                this.id[pi] = qi;
                this.size[qi] += this.size[pi];
            } else {
                this.id[qi] = pi;
                this.size[pi] += this.size[qi];
            }
            this.count--;
        }
        
        public int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
    }
}