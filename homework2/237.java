/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        //����ֱ�Ӹ��ǵķ�ʽ
        if(node.next==null){
            return;
        }
        node.val=node.next.val;//node.ListNode(node.next.val);
        node.next=node.next.next;
        
    }
}