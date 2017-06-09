/*Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true*/

/*Note:
You may assume that all words are consist of lowercase letters a-z.*/

public class WordDictionary {
    //set up trie data structure
    public class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String res = "";
    }
    
    private TrieNode root = new TrieNode();
    
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] temp = word.toCharArray();
        TrieNode node = root;
        
        for (char c : temp) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.res = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] word, int k, TrieNode node) {
        //termination case
        if (k == word.length) {
            return !node.res.equals("");
        }
        if (word[k] != '.') {
            return node.children[word[k] - 'a'] != null && match(word, k + 1, node.children[word[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(word, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */