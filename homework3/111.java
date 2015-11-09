/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //方法一：递归实现方法
 /*public class Solution {
     public int minDepth(TreeNode root) {
         int left=Integer.MAX_VALUE;
         int right=Integer.MAX_VALUE;
        if(root == null){
            return 0;
        }
        if((root.left==null)&&(root.right==null)){
            return 1;
        }
        if(root.left!=null)  left=minDepth(root.left);
        if(root.right!=null)  right=minDepth(root.right);
        return Math.min(left,right)+1;
     }
 }*/
 //方法二：非递归实现方法
public class Solution {
     public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
 
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();
 //一个结点对应一个count
        nodes.add(root);
        counts.add(1);
 
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();
 
            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
 
            if(curr.left == null && curr.right == null){
                return count;
            }
        }
 
        return 0;
    }
}