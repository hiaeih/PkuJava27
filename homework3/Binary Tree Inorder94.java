import java.util.*;
/**������Ϊ����ǰ���˼·������������������
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        /*while(root != null){
	   preorderTraversal(root.left);
	   list.add(root.val);
	   preorderTraversal(root.right);
	}*/
	if(root == null)
            return list;
        while((root != null) || (!stack.empty())){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                list.add(stack.peek().val);
                root = stack.pop();
                root = root.right;
            }
        }
        return list;
    }
}