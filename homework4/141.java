public class Solution {
    //方法一
    /*public boolean hasCycle(ListNode head) {
       if(head == null || head.next == null)
              return false;
         
         ListNode Faster = head, Slower = head;
         
         while(Faster.next!=null && Faster.next.next!=null){
             Slower = Slower.next;
             Faster = Faster.next.next;
             
             if(Faster == Slower)
              return true;
         }
         return false;
    }*/
    //方法二
    public boolean hasCycle(ListNode head) {
        ListNode slow;
        ListNode fast;
        
    if(head==null)
       return false;
        slow=head;
        fast=head.next;
    while(true){
        if(fast==null)
           return false;
           
         fast=fast.next;
         if(fast==null)
           return false;
         if(slow==fast)
           return true;
           
        fast=fast.next;
        if(fast==null)
           return false;
        if(slow==fast)
           return true;
           
        slow=slow.next;
        if(slow==fast)
           return true;
    }
}
}
//这个类已经嵌在leetcode里了，所以不能单独再写
// class ListNode {
//      int val;
//      ListNode next;
//      ListNode(int x) {
//          val = x;
//          next = null;
//      }
//   }