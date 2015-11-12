/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //表示结点里的数据或指针的时候要用‘.’，而不是‘->’
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode p=head;
        while(p!=null&&p.next!=null){
            if(p.val==p.next.val){
                p.next=p.next.next;
            }
            else{//else一定要写上，否则即使if条件满足了，还会执行p=p.next，结果就要出错了
            p=p.next;}
        }
        return head;
    }
}
