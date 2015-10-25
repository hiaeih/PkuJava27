//Binary Tree Preorder Traversal
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
		//1.递归 
		// List<Integer> list = new ArrayList<Integer>();
		// if(root == null){
			// return list;
		// }
		// List <Integer> listLeft= preorderTraversal(root.left);
		// List <Integer> listRight= preorderTraversal(root.right);
		// list.add(root.val);
		// for(int i =0;i<listLeft.size();i++){
			// list.add(listLeft.get(i));
		// }
		// for(int i = 0;i<listRight.size();i++){
			// list.add(listRight.get(i));
		// }
        // return list;
		//2.迭代
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root==null){
			return list;
		}
		while(root!=null||stack.isEmpty()==false){
			if(root == null){
				root=stack.pop();
				root=root.right;
				
			}else if(root.left!=null){
				list.add(root.val);
				stack.push(root);
				root=root.left;
			}else if(root.left==null&&root.right!=null){
				list.add(root.val);
				root=root.right;
				
			}else if(root.left==null&&root.right==null){
				list.add(root.val);
				if(stack.isEmpth()==false){
					root=stack.pop();
				}
				root=root.right;
			}
		}
		return list;


    }
}