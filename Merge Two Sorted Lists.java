/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

 

Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
 

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
*/



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Step 1: Create a dummy node (helps in building the result list)
        ListNode dummy = new ListNode(-1);

        // Tail pointer will move as we attach nodes
        ListNode tail = dummy;

        // Step 2: Compare and attach nodes from both lists
        while (list1 != null && list2 != null) {

            if (list1.val < list2.val) {
                tail.next = list1;   // attach list1 node
                list1 = list1.next;  // move list1 forward
            } else {
                tail.next = list2;   // attach list2 node
                list2 = list2.next;  // move list2 forward
            }

            tail = tail.next; // move the tail forward
        }

        // Step 3: If one list is not empty, attach the remaining part
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        // Step 4: The merged list starts at dummy.next
        return dummy.next;
    }
}
