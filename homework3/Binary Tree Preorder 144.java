import java.util.*;
/*
Given a binary tree, return the preorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
Note: Recursive solution is trivial, could you do it iteratively?*/
/**前序
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
        //开始没定义stack类型，返回错误
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        /*while(root != null){
	   list.add(root.val);
	   preorderTraversal(root.left);
	   preorderTraversal(root.right);
	}*/
	if(root == null)
            return list;
        while((root != null) || !stack.empty()){
            //将当前节点作为一会的转折节点，因此保存至栈中
            if(root != null){
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }
            else{
                root = stack.pop();
                root = root.right;
            }
        }
        return list;
    }
}