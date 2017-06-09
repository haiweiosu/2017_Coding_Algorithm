/* 
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"] */


public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        //method1
        //corner case
        if (words == null || words.length < 2) {
            return new ArrayList<>();
        }
        
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String part1 = words[i].substring(0,j);
                String part2 = words[i].substring(j);
                if (isPalindrome(part1)) {
                    String part2Rev = new StringBuilder(part2).reverse().toString();
                    if (map.containsKey(part2Rev) && map.get(part2Rev) != i) {
                        List<Integer> candidate = new ArrayList<>();
                        candidate.add(map.get(part2Rev));
                        candidate.add(i);
                        res.add(candidate);
                    }
                }
                if (isPalindrome(part2)) {
                    String part1Rev = new StringBuilder(part1).reverse().toString();
                    if (map.containsKey(part1Rev) && map.get(part1Rev) != i && part2.length() != 0) {
                        List<Integer> candidate = new ArrayList<>();
                        candidate.add(i);
                        candidate.add(map.get(part1Rev));
                        res.add(candidate);
                    }
                }
            }
        }
        
        return res;
    }
    
    private boolean isPalindrome(String word) {
        int low = 0, high = word.length() - 1;
        
        while (low <= high) {
            if (word.charAt(low++) != word.charAt(high--)) {
                return false;
            }
        }
        
        return true;
    }

    
    //method2
    class TrieNode {
        TrieNode [] next;
        int index;
        List<Integer> list;
    
        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
    
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
        for (int i = 0; i < words.length; i++) search(words, i, root, res);
        
        return res;
    }
    
    private void addWord(TrieNode root, String word, int index) {
    
        for (int i = word.length() - 1; i >= 0; i--) {
            char cur = word.charAt(i);
            int j = cur - 'a';
            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.next[j];
        }
        
        root.list.add(index);
        root.index = index;
    }
        
    private void search(String[] words, int index, TrieNode root, List<List<Integer>> res) {
        
        for (int i = 0; i < words[index].length(); i++) {
            if (isPalindrome(words[index], i, words[index].length() - 1) && root.index >= 0 && root.index != index) {
                res.add(Arrays.asList(index, root.index));
            }
            
            root = root.next[words[index].charAt(i) - 'a'];
            if (root == null) {
                return;
            }
        }
        
        for (int j : root.list) {
            if (j == index) {
                continue;
            }
            res.add(Arrays.asList(index, j));
        }
    }
    
    private boolean isPalindrome(String word, int low, int high) {
        while (low < high) {
            if (word.charAt(low++) != word.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}