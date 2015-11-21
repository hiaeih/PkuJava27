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
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        else if(root.left==null&&root.right==null) return 1;
        else{
        int depth1=maxDepth(root.left);
        int depth2=maxDepth(root.right);
        //加1很重要
        return depth1>depth2?depth1+1:depth2+1;
        }
    }
}