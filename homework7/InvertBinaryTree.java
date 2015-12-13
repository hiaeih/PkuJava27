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
		//递归实现

		// if (root == null||root.left==null)
			// return root;
		// if (root==null)
			// return root;
		// TreeNode temp = root.left;
		// root.left=invertTree(root.right);
		// root.right=invertTree(temp);
		// return root;
		//非递归实现  使用栈
		//1、交换根节点的左右子节点
		//2、交换第二层每个节点的左右子节点
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