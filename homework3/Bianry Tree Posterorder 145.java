import java.util.*;
/**����
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
	//�ݹ�
        /*while(root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            list.add(root.val);
        }*/
	//�����ȷ�����������������������ֻ�е������������ջ����Ѿ����ʹ��˲ſ�����������Ҫ�ж�����������ٳ�ջ��ѹ��list��
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null)
            return list;
        TreeNode cur;
        //��ǰ���
        TreeNode pre = null;
        //ǰһ�η��ʵĽ��
        stack.push(root);
        while(!stack.empty()){
            cur=stack.peek();
            if((cur.left == null && cur.right==null)|| pre != null && (pre == cur.left || pre == cur.right)){
                list.add(cur.val);  //�����ǰ���û�к��ӽ����ߺ��ӽڵ㶼�ѱ����ʹ�
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