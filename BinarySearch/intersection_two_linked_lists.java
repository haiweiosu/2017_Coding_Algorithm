/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        //corner case
        if (headA == null || headB == null){
            return null;
        }
        
        int lengthA = 1;
        int lengthB = 1;
        ListNode tempA = headA;
        ListNode tempB = headB;
        
        //calculate the length of two linked lists
        while (headA.next != null) {
            lengthA++;
            headA = headA.next;
        }
        
        while (headB.next != null) {
            lengthB++;
            headB = headB.next;
        }
        
        headA = tempA;
        headB = tempB;
        
        
        int diff = 0;
        int index = 0;
        //compare two linked lists length, take the shorter one as start
        if (lengthA >= lengthB) {
            diff = lengthA - lengthB;
            index = 0;
            //move the pointer of lengthA to the same position as listB
            while (headA != null && index < diff) {
                headA = headA.next;
                index++;
            }
            
        } else {
            diff = lengthB - lengthA;
            index = 0;
            //move the pointer of lengthB to the same position as listA
            while (headB != null && index < diff) {
                headB = headB.next;
                index++;
            }
        }
        
        //now two pointers on two linked lists are sycned
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}