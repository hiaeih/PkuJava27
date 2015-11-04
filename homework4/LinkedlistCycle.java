/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedlistCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        //¿ìÂýÖ¸Õë
        ListNode slow = head;
        ListNode quick = head;
        while(quick != null){
            slow = slow.next;
            quick = quick.next;
            if(quick != null)
                quick = quick.next;
            if (quick != null && (slow == quick))
                return true;  
        }
        return false;
    }
}