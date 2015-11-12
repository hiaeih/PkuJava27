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
    List<Integer> list=new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<TreeNode>();
        if(root==null){
            return list;
        }
        TreeNode p=root;
        while(!stack.empty()||p!=null){
            //沿着左子树一直走到底
            if(p!=null){
                stack.push(p);
                p=p.left;
            }
            //开始往链表里添加结点值
            else{
                 TreeNode st=stack.pop();
                 list.add(st.val);
                 p=st.right;
            }
        }
        return list;
    }
}