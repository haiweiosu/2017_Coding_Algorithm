//Given a number of BinaryTreeNode, determine if they can form a binary BinaryTreeNode
//Definition for a binary tree node.
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}

//By the definition of a binary tree, it is a type of tree whose child can have maximum 2 children. 
//And it is also an acyclic graph. 


class Solution {
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


	public boolean isValidBT(List<TreeNode> nodes) {
		//corner case
		if (nodes == null || nodes.size() == 0) {
			return false;
		}

		
	}
	}
 
