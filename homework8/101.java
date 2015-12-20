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
    public boolean isSymmetric(TreeNode root) {
        // if(root==null){
        //     return true;//?false
        // }
        // if((root.left==null && root.right!=null)||(root.left!=null && root.right==null)){
        //     return false;
        // }
        // if(root.left.val!=root.right.val){
        //     return false;
        // }
        // if(root.left==null && root.right==null){
        //     return true;
        // }else {
        //     return (isSymmetric(root.left)&&isSymmetric(root.right));
        // }
        
        if(root==null){
            return true;
        }
        return symmetric(root.left,root.right);
    }
    boolean symmetric(TreeNode left,TreeNode right){
        if(left==null && right==null){
            return true;
        }else if(left==null || right==null){
            return false;
        }
        if(left.val != right.val){
            return false;
        }
        return (symmetric(left.left,right.right) && symmetric(left.right,right.left));
        //return (symmetric(left.left) && symmetric(right.right));
    }
}