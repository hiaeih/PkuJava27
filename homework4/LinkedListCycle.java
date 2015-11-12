//Linked List Cycle
//Given a linked list, determine if it has a cycle in it.
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
public class Solution {
    public boolean hasCycle(ListNode head) {
		
		// 助教讲过
		if(head==null||head.next==null){
			return false;
		}
		// ListNode a = head;
		// ListNode b = head;
		// while (b!=null){
			// if(a!=null){
				// a=a.next;
			// }else{
				// a=head;
				// b=b.next;
			// }
			// if(a==b){
				// return true;
			// }		
		// }
		// return false;
		 ListNode slow = head;
        ListNode fast = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
            if (fast != null && (slow == fast))
                return true;  
        }
        return false;
    }
}