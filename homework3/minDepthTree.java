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
        //�ݹ�
        /*if((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return 1;*/
        /*int leftHigh = root.left == null?0:minDepth(root.left);
        int rightHigh = root.right == null ? 0: minDepth(root.right);
        //��������������нڵ�һ��,ע��������󷨲�ͬ
        if(leftHigh == 0 || rightHigh == 0)
            return leftHigh + rightHigh + 1;
        return leftHigh > rightHigh?rightHigh+1:leftHigh+1;*/
        //�ǵݹ�,��max�㷨���ƣ�ֻ���ж�����Ҷ�ӽڵ�ֱ�ӷ��أ�����������
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
                    return high;    //����
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