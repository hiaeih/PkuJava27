/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
       //���ÿ���ָ�루1-2�� ����ָ������-->true
       ListNode i = head;//��ָ��
       ListNode j = head;//��ָ��
       //int met = 1;
       
    //   if(head==null){
    //       return false;
    //   }else {
    //       while((i.next != null) && (j.next != null)) {//(head!==null)-->null
    //           i = i.next;
    //           j = j.next.next;
               
    //           if(i==j){//i
    //               met++;
    //               break;//����if/while/else??
    //           }
    //           System.out.println("break out of if");
    //       }
    //       System.out.println("break out of while");
    //   }
    //   System.out.println("break out of else");
    //   if(met>1){
    //       return true;
    //   }else {
    //       return false;
    //   }
    
    while(true) {
        if(head==null || i==null || j==null || j.next==null) {//(j!=null)==>(i.next!=null) ,(j.next==null)==>(j.next.next==null)
            return false;
        }
        
        i = i.next;
        j = j.next.next;//[1]-->Line 47: java.lang.NullPointerException
        if(i==j) {
            return true;
        }
    }
       
    }
}