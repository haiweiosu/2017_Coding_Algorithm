/*Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.*/


class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public String element = "";
    
    public TrieNode() {
        
    }
}

public class Trie {
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        //corner case
        if (word == null) {
            return;
        }
        
        TrieNode node = root;
        char[] str = word.toCharArray();
        
        for (char element : str) {
            //node doesn't contain this children, create a new trie node 
            if (node.children[element - 'a'] == null) {
                node.children[element - 'a'] = new TrieNode();
            }
            //move node pointer to this newest children of trienode
            node = node.children[element - 'a'];
        }
        //after iteration, the entire word will be the leaf of this trie
        node.element = word;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //corner case
        if (word == null) {
            return false;
        }
    
        TrieNode node = root;
        char[] str = word.toCharArray();
        
        for (char element : str) {
            //Trie doesn't contain at least prefix of this word
            if (node.children[element - 'a'] == null) {
                return false;
            }
            //move node pointer to the next child
            node = node.children[element - 'a'];
        }
        
        //after loop, check weather the leaf node value is equal to word
        return node.element.equals(word);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //corner case
        if (prefix == null) {
            return false;
        }
        
        TrieNode node = root;
        char[] str = prefix.toCharArray();
        
        for (char element : str) {
            if (node.children[element - 'a'] == null) {
                return false;
            }
            node = node.children[element - 'a'];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */