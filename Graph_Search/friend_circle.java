public class Solution {
    /*     - define visited array for each person, mark all ppl in one cycle visited using dfs
           - traverse the he specific row, if get 1, traverse the corresponding row of that column #
             since 2's friend is considered indirect friend of 1
           - once finishing visit a 1 cycle, increment circle res*/
           
    /* Complexity: Time O(N^2) -- n cycle  Space O(1)*/
    public int findCircleNum(int[][] M) {
        //corner case
        if (M == null || M.length == 0) {
            return -1;
        }
        
        int cycle = 0;
        boolean[] visited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                cycle++;
                visited[i] = true;
                dfs(M, visited, i);
            }
        }
        
        return cycle;
    }
    
    private void dfs(int[][] M, boolean[] visited, int index) {
        for (int j = 0; j < M[0].length; j++) {
            if (!visited[j] && M[index][j] == 1) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }
}