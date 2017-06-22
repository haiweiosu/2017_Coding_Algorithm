/*There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".*/

public class Solution {
    public String alienOrder(String[] words) {
        //corner case
        if (words == null || words.length == 0) {
            return "";
        }
        //build a graph of character
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        //map of counting degree of each character
        Map<Character, Integer> degree = new HashMap<>();
        
        //return result
        StringBuilder res = new StringBuilder();
        
        //initialize the graph
        initGraph(words, graph, degree);
        
        //build graph and count indegree
        graphAndIndegree(words, graph, degree);
        
        //topological sort
        topologicalSort(graph, degree, res);
        
        // if equal then it is acyclic
        return res.length() == degree.size() ? res.toString() : "";
    }
    
    private void initGraph(String[] words, Map<Character, Set<Character>> map, Map<Character, Integer> degree) {
        for (String word : words) {
            char[] temp = word.toCharArray();
            for (char character: temp) {
                if (map.get(character) == null) {
                    map.put(character, new HashSet<>());
                } 
                if (degree.get(character) == null) {
                    degree.put(character, 0);
                }
            }
        }
    }
    
    private void graphAndIndegree(String[] words, Map<Character, Set<Character>> map, Map<Character, Integer> degree) {
        Set<String> edges = new HashSet<>();
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i+1];
            
            int len = Math.min(cur.length(), next.length());
            
            for (int j = 0; j < len; j++) {
                //the edge from cur(j) -> next(j) has not been used
                if (cur.charAt(j) != next.charAt(j)) {
                    Set<Character> set = new HashSet<>();
                    if (map.containsKey(cur.charAt(j))) {
                        set = map.get(cur.charAt(j));
                    }
                    if (!set.contains(next.charAt(j))) {
                        set.add(next.charAt(j));
                        map.put(cur.charAt(j), set);
                        degree.put(next.charAt(j), degree.get(next.charAt(j)) + 1);
                    }
                    break;
                }
            }
        }
    }
    
    //bfs on topological sort
    private void topologicalSort(Map<Character, Set<Character>> graph, Map<Character, Integer> degree, StringBuilder res) {
        Queue<Character> queue = new LinkedList<>();
        for (Character node : degree.keySet()) {
            if (degree.get(node) == 0) {
                queue.offer(node);
            }
        }
        
        while (!queue.isEmpty()) {
            Character curr = queue.poll();
            res.append(curr);
            Set<Character> set = graph.get(curr);
            if (set != null) {
                for (Character c : set) {
                    int value = degree.get(c);
                    value--;
                    if (value == 0) {
                        queue.add(c);
                    }
                    degree.put(c, value);
                }
            }
        }
    }
}