/*Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].*/



public class Solution {
    private Set<String> set = new HashSet<>();
    private final static int[][] dirs = new int[][] {{-1,0}, {1,0}, {0,1}, {0,-1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        //corner case
        if (board == null || board[0].length == 0 
            || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        //using trie structure to pud every word from words into it
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        
        int m = board.length;
        int n = board[0].length;
        
        boolean[] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        
        return new ArrayList<>(res);
    }
    
    private void dfs(char[][] board, boolean[][] visited, String cur, int i, int j, Trie trie) {
        //termination case
        if (i < 0 || i > board.length || j < 0 || j > board[0].length) {
            return;
        }
        
        int m = board.length, n = board[0].length;
        
        if (visited[i][j]) {
            return;
        }
        
        cur += board[i][j];
        if (!trie.startWith(cur)){
            return;
        }
        
        if (trie.search(word)){
            res.add(word);
        }
        
        //dfs on 4 directions
        visited[i][j] = true;
        for (int[] dir : dirs) {
            dfs(board, visited, cur, i + dir[0], j+ dir[1], trie);
        }
        
        visited[i][j] = false;
    }
}