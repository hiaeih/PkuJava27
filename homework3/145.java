/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //后序遍历时，一个结点需要在两种情况下才能够输出：第一，它已经是叶子结点；第二，它不是叶子结点，但是它的儿子已经输出过。这里第二种情况我们//又细分为两种情况
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
 
        List<Integer> lst = new ArrayList<Integer>();
 
        if(root == null)
            return lst; 
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
 
        TreeNode prev = null;
        while(!stack.empty()){
            TreeNode curr = stack.peek();
        //向下查询二叉树，看curr是否是叶子结点
        //1.prev是curr父节点
            if(prev == null || prev.left == curr || prev.right == curr){
           // if(prev == null ){
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
        //叶子结点，就添加到链表中，输出之
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
        //向上查询二叉树
        //2.pre是curr左孩子
            }else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
        //非叶子结点，是无右孩子的结点，且左孩子已输出，故输出该结点
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
       //向上查询二叉树
       //3.pre是curr右孩子，即curr是非叶子结点，左右孩子均有，但是左右孩子均已输出，故轮到输出该结点
            }else if(curr.right == prev){
                stack.pop();
                lst.add(curr.val);
            }
 
            prev = curr;
        }
 
        return lst;
    }
}
