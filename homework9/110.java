/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*ƽ���������һ�ÿ����������������������ĸ߶Ȳ�ľ���ֵ������1����������������������һ��ƽ���������*/
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        if(root.left==null&&root.right==null) return true;
        //�����ƾ���Һ��Ӹ߶Ȳ�ֵ������1�����ж�����ƽ����
        if(Math.abs(height(root.left)-height(root.right))>1) return false;
        
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    public int height(TreeNode tn){
        if(tn==null) return 0;
        return Math.max(height(tn.left),height(tn.right))+1;
    }
}