/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //�������ʱ��һ�������Ҫ����������²��ܹ��������һ�����Ѿ���Ҷ�ӽ�㣻�ڶ���������Ҷ�ӽ�㣬�������Ķ����Ѿ������������ڶ����������//��ϸ��Ϊ�������
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
        //���²�ѯ����������curr�Ƿ���Ҷ�ӽ��
        //1.prev��curr���ڵ�
            if(prev == null || prev.left == curr || prev.right == curr){
           // if(prev == null ){
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
        //Ҷ�ӽ�㣬����ӵ������У����֮
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
        //���ϲ�ѯ������
        //2.pre��curr����
            }else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
        //��Ҷ�ӽ�㣬�����Һ��ӵĽ�㣬�������������������ý��
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
       //���ϲ�ѯ������
       //3.pre��curr�Һ��ӣ���curr�Ƿ�Ҷ�ӽ�㣬���Һ��Ӿ��У��������Һ��Ӿ�����������ֵ�����ý��
            }else if(curr.right == prev){
                stack.pop();
                lst.add(curr.val);
            }
 
            prev = curr;
        }
 
        return lst;
    }
}
