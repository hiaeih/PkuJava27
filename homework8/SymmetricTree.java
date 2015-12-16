/*Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following is not:

    1
   / \
  2   2
   \   \
   3    3
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root== null) return true;

        return ifSymmetric(root.left, root.right);        
    }

    public boolean ifSymmetric(TreeNode tree1, TreeNode tree2){
        if(tree1==null && tree2==null)
            return true;
        else if(tree1 == null || tree2 == null)
            return false;

        if(tree1.val != tree2.val)
            return false;
        else
            return (ifSymmetric(tree1.left, tree2.right) && ifSymmetric(tree1.right, tree2.left));
    }
    //���ڲ��ǶԳƣ�1,2��3,3��null��2��null���ǶԳƵģ����Ǹ÷����жϳ���Ϊtrue
    /*public boolean isSymmetric(TreeNode root) {
        if(root == null || root.left == null && root.right == null)
            return true;
        //����ͨ�������������list�����У�����Գƣ���Ӧ���������������������
        List<Integer> listL = new ArrayList<Integer>();
        List<Integer> listR = new ArrayList<Integer>();
        listL = inOrder(root.left);
        list = new ArrayList<Integer>();
        listR = inOrder(root.right);
        if(listL.size() != listR.size())
            return false;
        int i = 0;
        for(; i < listR.size(); i++){
            if(listL.get(i) != listR.get(listR.size() - i - 1))
                return false;
        }
        return true;
    }
    List<Integer> list = new ArrayList<Integer>();
    public List<Integer> inOrder(TreeNode root){
        //�ݹ飬���²����½����޷�����֮ǰ���ݣ����������Ҫ����Ϊȫ��
        //List<Integer> list = new ArrayList<Integer>();
        if(root == null)
            return list;
        if(root.left != null)
            inOrder(root.left);
         list.add(root.val);
         if(root.right != null)
            inOrder(root.right);
        return list;
    }*/
}