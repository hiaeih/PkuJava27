/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //借鉴125题目的做法。但是没有满足O(1)space.
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        ListNode ln=head;
        Stack st=new Stack();
        while(ln!=null)
        {
            st.push(ln.val);
            ln=ln.next;
        }
        while(head!=null){
            int he=(int)st.pop();
            if(he!=head.val) return false;
            head=head.next;
        }
        return true;
    }
}