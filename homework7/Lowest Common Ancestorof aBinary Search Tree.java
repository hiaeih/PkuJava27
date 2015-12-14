//Lowest Common Ancestor of a Binary Search Tree

//Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

//According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node 
//in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
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
		//二叉搜索树 
		//p,q比root小,LCA在左子树
		//p,q比root大，LCA在右子树
		//p,q一个比root小，一个比root大，或者和root值相等，返回root
		
		if (root==null)
			return root;
		if(p==null||q==null)
			return null;
		// if(p.val<root.val&&q.val>root.val){
			// return root;
		// }
		if(Math.max(p.val,q.val)<root.val){
			return lowestCommonAncestor(root.left,p,q);
		}else if(Math.min(p.val,q.val)>root.val){
			return lowestCommonAncestor(root.right,p,q);
		}else{
			return root;
		}
		
    }
}