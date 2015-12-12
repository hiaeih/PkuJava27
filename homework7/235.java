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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
         if(root==null || p==null || q==null) return null;
        
        if((p.val-root.val)*(q.val-root.val)<=0){//p��q����Ϊroot�����Һ��� !![2,1] ==>"=0"
            return root;
        }else if((p.val-root.val)<0){//������
           return lowestCommonAncestor(root.left,p,q);
        }else {
            return lowestCommonAncestor(root.right,p,q);
        }
    }
}