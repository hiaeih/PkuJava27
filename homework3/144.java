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
    public List<Integer> preorderTraversal(TreeNode root) {
         ArrayList<Integer> preOrder = new ArrayList<Integer>();
        //利用栈实现
        if(root==null){
            return preOrder;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.empty()){
            TreeNode n = stack.pop();
            //①根节点
            preOrder.add(n.val);
            //②右子树
            if(n.right!=null){
                stack.push(n.right);
            }
            //③左子树
            if(n.left!=null){
                stack.push(n.left);
            }

            //顺序错误，栈：后进先出，故顺序应相反
            // //②左子树
            // if(n.left!=null){
            //     stack.push(n.left);
            // }
            // //③右子树
            // if(n.right!=null){
            //     stack.push(n.right);
            // }
        }
        return preOrder;
    }
}