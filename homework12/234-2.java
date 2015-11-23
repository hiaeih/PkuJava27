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
        //求中位指针
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast!=null) {
          // reverseList(slow.next);
           slow=slow.next;
        }
        //这里一定要注意用到的指针的声明，以免造成冲突
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