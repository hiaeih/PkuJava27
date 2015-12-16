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
    //对于不是对称，1,2，3,3，null，2，null不是对称的，但是该方法判断出来为true
    /*public boolean isSymmetric(TreeNode root) {
        if(root == null || root.left == null && root.right == null)
            return true;
        //可以通过中序遍历存入list集合中，如果对称，则应该左右子树集合是逆序的
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
        //递归，导致不断新建，无法保存之前数据！！！因此需要设置为全局
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