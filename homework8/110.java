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
    public boolean isBalanced(TreeNode root) {
        //˼·���������������ĸ߶Ȳ����ж�
        if(root==null){
            return true;
        }
        if (height(root) == -1){//�߶Ȳ����1
			return false;
        }
        return true;
        
    }
    public int height(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        int l = height(root.left);
        int r = height(root.right);
        
        if(l==-1 || r==-1){//����ƽ�������
            return -1;
        }
        if (Math.abs(l-r) > 1) {//����ƽ�������
			return -1;
		}
		return Math.max(l,r)+1;//return 1;
    }
}