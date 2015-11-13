//Given a sorted linked list, delete all duplicates such that each element appear only once.

// For example,
// Given 1->1->2, return 1->2.
// Given 1->1->2->3->3, return 1->2->3.
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
		ListNode node = head;
		if(head==null||head.next==null)
			return head;
		// while(node.next!=null){
			// ListNode Nnext=node.next;
			// if(node.val==Nnext.val){
				// node.next=Nnext.next;
			// }
			// node=node.next;
		// }
		 while(node!=null&&node.next!=null){
			ListNode Nnext=node.next;
			if(node.val==Nnext.val){
				node.next=Nnext.next;
            }else{
				node=node.next;
			}		
		}
		return head;
        
    }
}