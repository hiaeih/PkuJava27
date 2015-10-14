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
        //采用直接覆盖的方式
        if(node.next==null){
            return;
        }
        node.val=node.next.val;//node.ListNode(node.next.val);
        node.next=node.next.next;
        
    }
}