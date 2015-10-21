package week2;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class TwoHundredandThirtyfour {
    public boolean isPalindrome(ListNode head) {
        //运行超时
        /*int len = 0;
        if(head == null || head.next == null)
            return true;
        String s = "";
        while(head != null){
            len++;
            s += head.val;
            head = head.next;
        }
        int i = 0;
        for(i = 0;i < len/2;i++)
            if(s.charAt(i) != s.charAt(len - i -1))
                break;
        return i == len/2?true:false;*/
        /*if(head == null || head.next == null) { return true;}
        ListNode slow = head, fast = head;
         //find middle node
         while(fast.next != null && fast.next.next != null) {
             slow = slow.next;
             fast = fast.next.next;
         }
         //reverse the second half linked list
         ListNode last = slow.next, pre = head;
         while(last.next != null ) {
             ListNode tmp = last.next;
             last.next = tmp.next;
             tmp.next = slow.next;
             slow.next = tmp;
        }
         while(slow.next != null) {
             slow = slow.next;
             if(pre.val != slow.val) return false;
             pre = pre.next;
         }
         return true;*/
        int len = 0;
        if(head == null || head.next == null)
            return true;
        ListNode ln = head;
        ListNode prev = new ListNode(0);
		prev.next = head;
		head = prev;
		ListNode p = head.next, temp = null;
		while (p != null) {
			prev = temp;
			temp = p;
			p = p.next;
			temp.next = prev;
		}
		head.next = temp;
        head = head.next;
		int num = 0;
        if((head.val == ln.val) && num < len/2){
            System.out.print(head.val + " " + ln.val);
            num++;
            head = head.next;
            ln = ln.next;
        }
        return num == len/2?true:false;
    }
}
