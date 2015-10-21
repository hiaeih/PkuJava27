//Delete Node in a Linked List

/**Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
the linked list should become 1 -> 2 -> 4 after calling your function.**/

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
		ListNode nextNode=node.next;
		//刚开始想的把结点的值赋给上一个结点，然后删除最后一个结点
		// if(nextNode!=null){
			// node.val=nextNode.val;
			// deleteNode(nextNode);
		// }else{
			// node=null;
		// }
		//可以把p的后继结点元素的值赋给p结点元素的值。
		node.val=nextNode.val;
		node.next=nextNode.next;
    }
}