//Minimum Depth of Binary Tree
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
    public int minDepth(TreeNode root) {
		
		if(root==null){
			return 0;
		}
		//二叉树最小深度概念问题
		// int min = Math.min(minDepth(root.left),minDepth(root.right))+1;
		//对于有单个孩子为空的节点，为空的孩子会返回0，但这个节点并非叶子节点，故返回的结果是错误的。
		
		int left =minDepth(root.left);
		int right=minDepth(root.right);
		if(left==0&&right==0){
			return 1;
		}else if(left==0){
			left=Integer.MAX_VALUE;
		}else if(right==0){
			right=Integer.MAX_VALUE;
		}
		int min = Math.min(left,right)+1;
		return min;
		
        
    }
}