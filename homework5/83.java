/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //��ʾ���������ݻ�ָ���ʱ��Ҫ�á�.���������ǡ�->��
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode p=head;
        while(p!=null&&p.next!=null){
            if(p.val==p.next.val){
                p.next=p.next.next;
            }
            else{//elseһ��Ҫд�ϣ�����ʹif���������ˣ�����ִ��p=p.next�������Ҫ������
            p=p.next;}
        }
        return head;
    }
}
