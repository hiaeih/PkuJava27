//Invert Binary Tree
//Invert a binary tree.

     // 4
   // /   \
  // 2     7
 // / \   / \
// 1   3 6   9
// to
     // 4
   // /   \
  // 7     2
 // / \   / \
// 9   6 3   1
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
    public TreeNode invertTree(TreeNode root) {
		//�ݹ�ʵ��

		// if (root == null||root.left==null)
			// return root;
		// if (root==null)
			// return root;
		// TreeNode temp = root.left;
		// root.left=invertTree(root.right);
		// root.right=invertTree(temp);
		// return root;
		//�ǵݹ�ʵ��  ʹ��ջ
		//1���������ڵ�������ӽڵ�
		//2�������ڶ���ÿ���ڵ�������ӽڵ�
		 if(root == null) return null;  
        Stack<TreeNode> stack = new Stack<TreeNode>();  
        stack.push(root);  
        while(!stack.isEmpty()){  
            TreeNode cur = stack.pop();  
            TreeNode temp = cur.left;  
            cur.left = cur.right;  
            cur.right = temp;  
            if(cur.left != null) stack.push(cur.left);  
            if(cur.right != null) stack.push(cur.right);  
        }  
        return root;  
		
		
		
    }
}