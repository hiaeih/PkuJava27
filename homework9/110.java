/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*平衡二叉树是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。*/
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        if(root.left==null&&root.right==null) return true;
        //这里仅凭左右孩子高度差值不大于1不能判断其是平衡树
        if(Math.abs(height(root.left)-height(root.right))>1) return false;
        
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    public int height(TreeNode tn){
        if(tn==null) return 0;
        return Math.max(height(tn.left),height(tn.right))+1;
    }
}