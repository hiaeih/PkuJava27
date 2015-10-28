/*Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.*/
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
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        //递归
        /*if((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return 1;*/
        /*int leftHigh = root.left == null?0:minDepth(root.left);
        int rightHigh = root.right == null ? 0: minDepth(root.right);
        //特殊情况，返回有节点一侧,注意和最深求法不同
        if(leftHigh == 0 || rightHigh == 0)
            return leftHigh + rightHigh + 1;
        return leftHigh > rightHigh?rightHigh+1:leftHigh+1;*/
        //非递归,和max算法类似，只是判断遇到叶子节点直接返回，不继续遍历
        int high = 1;
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        while(list.size() > 0){
            int hl = 0,hr = 0;
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            for(int i = 0; i < list.size(); i++){
                TreeNode curr = list.get(i);
                if(curr.left != null){
                    //hl++;
                    nextLevel.add(curr.left);
                }if(curr.right != null){
                    //hr++;
                    nextLevel.add(curr.right);
                }if(curr.left == null && curr.right == null){
                    return high;    //区别
                    /*int high1 = Math.min(hl,hr);
                    high += Math.min(high,high1);*/
                }
            }
            if(nextLevel.size() == 0)
                break;
            else{
                high++;
                list = nextLevel;
            }
        }
        return high;
    }
}