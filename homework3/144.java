/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//借助栈来做，root进栈后，让右孩子先进栈，再让左孩子进栈，这样就可以先把左孩子加入链表里了，进而实现先序遍历
 
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
 
        if(root == null)
            return list;
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
 
        while(!stack.empty()){
            TreeNode n = stack.pop();
            list.add(n.val);
 
            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }
 
        }
        return list;
    }
}
