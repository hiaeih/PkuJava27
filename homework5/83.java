/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode s=head;
        ListNode e=head.next;
        // if(e.next==null && e.val==s.val) {
        //     s.next=null;
        //     return head;
        // }
        // if(e.next==null && e.val!=s.val){
        //     return head;
        // }
        while(e!=null) {//e.next!=null
            if(e.val==s.val){
                //e=e.next;//s.next=e.next;//
                s.next=e.next;
                e=e.next;
            }else {
                // s.next=e;
                // s=e;//s=s.next;
                // e=e.next;
                s=e;
                e=e.next;
            }
        }
        return head;
    }
}