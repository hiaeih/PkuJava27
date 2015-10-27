import java.util.*;
/**后序
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
        List<Integer> list = new ArrayList<Integer>();
	//递归
        /*while(root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            list.add(root.val);
        }*/
	//后序：先访问左子树、右子树、根，只有当左右子树都空或者已经访问过了才可以输出，因此要判断这两种情况再出栈，压入list内
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return list;
        TreeNode cur;
        //当前结点
        TreeNode pre = null;
        //前一次访问的结点
        stack.push(root);
        while(!stack.empty()){
            cur=stack.peek();
            if((cur.left == null && cur.right==null)|| pre != null && (pre == cur.left || pre == cur.right)){
                list.add(cur.val);  //如果当前结点没有孩子结点或者孩子节点都已被访问过
                stack.pop();
                pre=cur;
            }else{
                    if(cur.right != null)
                    stack.push(cur.right);
                    if(cur.left != null)
                    stack.push(cur.left);
                }
        }
        
        /*Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return list;
        while((root != null) || (!stack.empty())){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                root = stack.peek();
                root = root.right;
            }
            
        }*/
        return list;
    }
}