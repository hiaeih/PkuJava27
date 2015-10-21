//Maximum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 //初始化支持 TreeNode（int x）这种方式，即 把x赋给val，left和right赋值NULL。
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		//递归
		int depth =Math.max(maxDepth(root.left),maxDepth(root.right))+1;
		return depth;
 
    }
}