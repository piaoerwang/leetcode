/**
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        
        List<List<Integer>> leavesList = new ArrayList< List<Integer>>();
        List<Integer> leaves = new ArrayList<Integer>();
        
        while(root != null) {
            if(isLeave(root, leaves)) root = null;
            leavesList.add(leaves);
            // NOTE: could not clear the list, has to be a new
            leaves = new ArrayList<>();
        }
        return leavesList;
    }
    
    public boolean isLeave(TreeNode node, List<Integer> leaves) {
        
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return true;
        }
        
        if (node.left != null) {
             if(isLeave(node.left, leaves))  node.left = null;
        }
        
        if (node.right != null) {
             if(isLeave(node.right, leaves)) node.right = null;
        }
        
        return false;
    }
}
