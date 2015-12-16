/*Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null || root.left == null && root.right == null)
            return true;
        int Hl = 0, Hr = 0;
        if(root.left != null){
            TreeNode rootL = root.left;
            Hl = height(rootL);
        }
        if(root.right != null){
            TreeNode rootR = root.right;
            Hr = height(rootR);
        }
        //return Math.abs(Hl - Hr) < 2 ? true:false;
        //not only root is balance but every subtress are balance.
        if(Math.abs(Hl - Hr) > 1)
            return false;
        else
            return  isBalanced(root.left) && isBalanced(root.right) ? true:false;
        
    }
    public int height(TreeNode root){
        int l = 0, r = 0;
        if(root.left == null && root.right == null)
            return 1;
        else if(root.left != null)
                l = height(root.left);
             if(root.right != null)
                r = height(root.right);
        return Math.max(l,r) + 1;
    }
}