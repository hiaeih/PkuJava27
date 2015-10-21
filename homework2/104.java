//Maximum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 //��ʼ��֧�� TreeNode��int x�����ַ�ʽ���� ��x����val��left��right��ֵNULL��
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		//�ݹ�
		int depth =Math.max(maxDepth(root.left),maxDepth(root.right))+1;
		return depth;
 
    }
}