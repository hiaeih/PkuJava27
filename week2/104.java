package week2;

public class OneHundredandFour {
	public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
            else{
                int hl = maxDepth(root.left);
                int hr = maxDepth(root.right);
                return hl >= hr?hl + 1:hr + 1;
            }
            /*else if(root.left == null && root.right == null)
                return 1;
                else
                    return maxDepth(root.left)+maxDepth(root.right);*/
    }
}
