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
        //����ջʵ��
        if(root==null){
            return preOrder;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.empty()){
            TreeNode n = stack.pop();
            //�ٸ��ڵ�
            preOrder.add(n.val);
            //��������
            if(n.right!=null){
                stack.push(n.right);
            }
            //��������
            if(n.left!=null){
                stack.push(n.left);
            }

            //˳�����ջ������ȳ�����˳��Ӧ�෴
            // //��������
            // if(n.left!=null){
            //     stack.push(n.left);
            // }
            // //��������
            // if(n.right!=null){
            //     stack.push(n.right);
            // }
        }
        return preOrder;
    }
}