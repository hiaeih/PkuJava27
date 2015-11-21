/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /*�����������ɾ�������һ���ڵ㣬��ͨ����ͬ���ǣ�û�и������������㣬ֻ��������һ��Ҫɾ�Ľڵ㣬��������ǰ�����������̫һ��������֮ǰҪɾ��һ���ڵ�ķ�����Ҫ����ǰһ���ڵ��λ�ã�Ȼ����ǰһ���ڵ��next����Ҫɾ�ڵ����һ����Ȼ��delete��Ҫɾ�Ľڵ㼴�ɡ������Ĵ��������Ȱѵ�ǰ�ڵ��ֵ����һ���ڵ��ֵ�����ˣ�Ȼ������ɾ����һ���ڵ㼴��*/
    public void deleteNode(ListNode node) {
      if(node.next!=null){
          node.val=node.next.val;
          node.next=node.next.next;
      }
      else   return;
    }
}