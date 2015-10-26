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
    public int minDepth(TreeNode root) {
        // int leftMin;
        // int rightMin;
        // //采用递归求解的方式
        // if(root==null) return 0;
        // if(root.left==null && root.right==null){
        //     return 1;
        // }else if(root.left!=null){
        //     leftMin += minDepth(root.left);
        // }else{
        //     rightMin += minDepth(root.right);
        // }
        
        // if(leftMin<=rightMin){
        //     return leftMin;
        // }else{
        //     return rightMin;
        // }
        
        if(root==null) return 0;
        if(root.left==null && root.right==null){//叶子节点
            return 1;
        }else{
//            int minRight = minDepth(root.right);
//            if(minRight==0){
//                return minRight++;
//            }
//            int minLeft = minDepth(root.left);
//            if(minLeft==0){
//                return minLeft++;
//            }
        //     if(root.left==null){
        //         int minRight = minDepth(root.right);
        //         return minRight++;
        //     }
        //     if(root.right==null){
        //         int minLeft = minDepth(root.left);
        //         return minLeft++;
        //     }
        // }
        
        // if(minLeft<=minRight){
        //     return minLeft++;
        // }else{
        //     return minRight++;
        // }
        int leftDepth = root.left != null ? minDepth(root.left)
                     : 1000000;
             int rightDepth = root.right != null ? minDepth(root.right)
                     : 1000000;
             return Math.min(leftDepth, rightDepth) + 1;
        }
            
        
    }
}