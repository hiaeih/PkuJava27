/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*BST中关键码无重复，左子树如果存在，其所有的关键码一定小于根，右子树如果存在，其所有关键码一定大于根，左右子树自然也是BST，因此有特性中序遍历序列单调递增*/
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        //要注意合并
        //if(root==p||root==q) return root;
        //if(root.val>Math.min(p.val,q.val)&&root.val<Math.max(p.val,q.val)) return root;
        if(root.val<Math.min(p.val,q.val)){
            root=root.right;
            //不要忘了lowestCommonAncestor这个函数是有返回类型的
            return lowestCommonAncestor(root,p,q);
        }
        if(root.val>Math.max(p.val,q.val)){
            root=root.left;
            return lowestCommonAncestor(root,p,q);
        }
        return root;
    }
}