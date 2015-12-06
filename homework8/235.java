/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*BST�йؼ������ظ���������������ڣ������еĹؼ���һ��С�ڸ���������������ڣ������йؼ���һ�����ڸ�������������ȻҲ��BST���������������������е�������*/
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        //Ҫע��ϲ�
        //if(root==p||root==q) return root;
        //if(root.val>Math.min(p.val,q.val)&&root.val<Math.max(p.val,q.val)) return root;
        if(root.val<Math.min(p.val,q.val)){
            root=root.right;
            //��Ҫ����lowestCommonAncestor����������з������͵�
            return lowestCommonAncestor(root,p,q);
        }
        if(root.val>Math.max(p.val,q.val)){
            root=root.left;
            return lowestCommonAncestor(root,p,q);
        }
        return root;
    }
}