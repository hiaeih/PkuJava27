/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode pre = head;
        ListNode cur = head.next;
        while(pre != null && cur != null){
            if(pre.val == cur.val){
                ListNode temp = cur.next;
                pre.next = temp;
                if(temp != null){
                    cur = temp;
                    //针对1,1,1出错
                    /*pre = temp;
                    cur = temp.next;*/
                }//没加出现Limit exeed
                else
                    return head;
            }else{
                pre = pre.next;
                cur = cur.next;
            }
        }
        return head;
    }
}