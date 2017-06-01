/*Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.*/


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
    
    public class ResultType {
        int root2Any, any2Any;
        ResultType(int root2Any, int any2Any) {
            this.root2Any = root2Any;
            this.any2Any = any2Any;
        }
    }
    
    private ResultType getMax(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        
        ResultType left = getMax(root.left);
        ResultType right = getMax(root.right);
        
        int root2Any = Math.max(0, Math.max(left.root2Any, right.root2Any)) + root.val;
        
        int any2Any = Math.max(left.any2Any, right.any2Any);
        
        any2Any = Math.max(any2Any, Math.max(0, left.root2Any) 
                                    + Math.max(0, right.root2Any) 
                                    + root.val);
        
        return new ResultType(root2Any, any2Any);
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType res = getMax(root);
        return res.any2Any;
    }
}