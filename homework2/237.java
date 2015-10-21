package week2;

/*Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.*/
public class TwoHundredandThirySeven {
	public void deleteNode(ListNode node) {
		// 没有先前节点，就让该节点的next只想next.next就好了
		if (node == null)
			return;
		else {
			node.val = node.next.val;
			node.next = node.next.next;
		}
	}
}
