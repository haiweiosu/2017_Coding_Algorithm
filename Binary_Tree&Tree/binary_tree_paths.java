/*Given a binary tree, return all root-to-leaf paths.*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
    
        List<String> res = new ArrayList<>();    
        if (root != null) {
            searchBT(root, "", res);
        }
        
        return res;
    }
    
    private void searchBT(TreeNode root, String cur, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(cur + root.val);
        }
        
        if (root.left != null) {
            searchBT(root.left, cur + root.val + "->", res);
        }
        
        if (root.right != null) {
            searchBT(root.right, cur + root.val + "->", res);
        }
    }
    
    
    
    
    //method2 referenced from Leetcode top solution, not my own solution
    List<String> paths = new LinkedList<>();

    if(root == null) return paths;
    
    if(root.left == null && root.right == null){
        paths.add(root.val+"");
        return paths;
    }

     for (String path : binaryTreePaths(root.left)) {
         paths.add(root.val + "->" + path);
     }

     for (String path : binaryTreePaths(root.right)) {
         paths.add(root.val + "->" + path);
     }

     return paths;
}