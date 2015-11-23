/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) return true;
        ListNode slow=head;
        ListNode fast=head;
        //����λָ��
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast!=null) {
          // reverseList(slow.next);
           slow=slow.next;
        }
        //����һ��Ҫע���õ���ָ���������������ɳ�ͻ
        ListNode r = reverseList(slow);
        ListNode p=head;
        while(r!=null){
            if(p.val!=r.val) {return false;}
            r=r.next;
            p=p.next;
        }
        return true;
    }
    public ListNode reverseList(ListNode head){
       if(head==null||head.next==null) return head;
        else {
       ListNode pre=head; 
        ListNode rear=pre.next;  
        pre.next=null;
        while(rear != null){  
            ListNode tmp = rear.next;  
            //pre.next=rear.next;
            rear.next=pre;
            pre=rear;
            rear=tmp;
              
        }
        head=pre;
        return head;  
        }
    }
   
}