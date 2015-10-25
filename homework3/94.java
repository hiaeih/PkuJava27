//Binary Tree Inorder Traversal
//Given a binary tree, return the inorder traversal of its nodes' values.
//�������������
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
    public List<Integer> inorderTraversal(TreeNode root) {
        // 1.�ݹ� 
/**		List<Integer> list = new ArrayList<Integer>();
*		if(root==null){
*			return list;
*		}
*		List<Integer> listLeft= inorderTraversal(root.left);		
*		List<Integer> listRight= inorderTraversal(root.right);
*		for(int i = 0;i<listLeft.size;i++){
*		for(int i = 0;i<listLeft.size();i++){
*			list.add(listLeft.get(i));
*		}
*		list.add(root.val);
*		for(int i = 0;i<listRight.size();i++){
*			list.add(ListRight.get(i));
*		}
*		return list;
*/
		//Recursive solution is trivial, could you do it iteratively��
		//2.���� ��ջʵ��
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		 	List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root==null){
			return list;
		}
		while(root!=null||stack.isEmpty()==false){
			//���� java.lang.NullPointerException
			//������root = null���ж�
		    if(root==null){
		        root=stack.pop();
		        list.add(root.val);
		        root=root.right;
		    }else if(root.left!=null){
				stack.push(root);
				root=root.left;
			}else if(root.left==null&&root.right!=null){
				list.add(root.val);
				root=root.right;
			}else if(root.left==null&&root.right==null){
				list.add(root.val);
				if(stack.isEmpty()==false){
				root=stack.pop();
				list.add(root.val);
				}
				root=root.right;
			}
		}
		return list;
    }
}
