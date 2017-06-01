/*Serialization is the process of converting a data structure or object into a sequence of bits 
so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed 
later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string 
and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative 
and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    
    //method: BFS level order traversal
    public String serialize(TreeNode root) {
        //corner case
        if (root == null) {
            return "";
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                res.append("null ");
                continue;
            }
            
            res.append(cur.val + " ");
            queue.add(cur.left);
            queue.add(cur.right);
        }
        
        return res.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //corner case
        if (data == null || data == "") {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        //parse the string to a string array
        String[] str = data.split(" ");
        
        //initialize the root by the first element of array
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        
        queue.add(root);
        
        for (int i = 1; i < str.length; i++) {
            TreeNode cur = queue.poll();
            if (!str[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(str[i]));
                cur.left = left;
                queue.add(left);
            }
            if (!str[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(str[i]));
                cur.right = right;
                queue.add(right);
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));