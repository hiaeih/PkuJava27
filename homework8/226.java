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
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        if(root.right==null&&root.left==null) return root;
        TreeNode tmp=root.right;
        root.right=root.left;
        root.left=tmp;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }
}