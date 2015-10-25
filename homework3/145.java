//Binary Tree Postorder Traversal
//Given a binary tree, return the postorder traversal of its nodes' values.
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
    public List<Integer> postorderTraversal(TreeNode root) {
		//1.递归实现
/**        List<Integer> list = new ArrayList<Integer>();
		if(root =null){
			return list;
		}
		List<Integer> listLeft= postorderTraversal(root.left);
		List<Integer> listRight = postorderTraversal(root.right);
		for(int i =0;i<listLeft.size();i++){
			list.add(listLeft.get(i));
		}
		for(int i =0;i<listRight.size();i++){
			list.add(listRight.get(i));
		}
		list.add(root.val);
		return list;
**/
		//2.非递归实现
		//设置两个结点
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		TreeNode last = null;
		TreeNode now=root;
		if(root==null){
			return list;
		}
		stack.push(root);
		while (stack.isEmpty()==false) {
        now = stack.peek();
        if (last == null || last.left == now || last.right == now) { // traverse down the tree
            if (now.left != null) {
                stack.push(now.left);
            } else if (now.right != null) {
                stack.push(now.right);
            }
        } else if (now.left == last) { // traverse up the tree from the left
            if (now.right != null) {
                stack.push(now.right);
            }
        } else { // traverse up the tree from the right
            result.add(now.val);
            stack.pop();
        }
        last = now;
    }
		return list;
		
    }
}