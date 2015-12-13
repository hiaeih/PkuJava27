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
       if(root==null) return true;
       return Symmetric(root.left,root.right);
    }
     public boolean Symmetric(TreeNode t1,TreeNode t2) {
         if(t1!=null&&t2==null||t1==null&&t2!=null) return false;
         if(t1==null&&t2==null) return true;
         //ע�������ƾt1��t2��ֵ��Ȼ������ж��䷵��ֵΪ��
         if(t1.val!=t2.val) return false;
         
         return Symmetric(t1.left,t2.right)&&Symmetric(t1.right,t2.left);
     }
}