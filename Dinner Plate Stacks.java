/*
You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.

Implement the DinnerPlates class:

DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks capacity.
void push(int val) Pushes the given integer val into the leftmost stack with a size less than capacity.
int pop() Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all the stacks are empty.
int popAtStack(int index) Returns the value at the top of the stack with the given index index and removes it from that stack or returns -1 if the stack with that given index is empty.
 

Example 1:

Input
["DinnerPlates", "push", "push", "push", "push", "push", "popAtStack", "push", "push", "popAtStack", "popAtStack", "pop", "pop", "pop", "pop", "pop"]
[[2], [1], [2], [3], [4], [5], [0], [20], [21], [0], [2], [], [], [], [], []]
Output
[null, null, null, null, null, null, 2, null, null, 20, 21, 5, 4, 3, 1, -1]

Explanation: 
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:     4
                                                       1  3  5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
                                                        1  3  5
                                                        ﹈ ﹈ ﹈ 
D.pop()            // Returns 5.  The stacks are now:      4
                                                        1  3 
                                                        ﹈ ﹈  
D.pop()            // Returns 4.  The stacks are now:   1  3 
                                                        ﹈ ﹈   
D.pop()            // Returns 3.  The stacks are now:   1 
                                                        ﹈   
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.
 

Constraints:

1 <= capacity <= 2 * 104
1 <= val <= 2 * 104
0 <= index <= 105
At most 2 * 105 calls will be made to push, pop, and popAtStack.
*/

import java.util.*;

class DinnerPlates {
    private int capacity;
    private ArrayList<Stack<Integer>> stacks;
    private TreeSet<Integer> available;
    private int rightMost;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        this.stacks = new ArrayList<>();
        this.available = new TreeSet<>();
        this.rightMost = -1;
    }

    public void push(int val) {
        if (available.isEmpty()) {
            // Create new stack
            Stack<Integer> stack = new Stack<>();
            stack.push(val);
            stacks.add(stack);

            int index = stacks.size() - 1;
            if (capacity > 1) {
                available.add(index);
            }

            rightMost = index;
        } else {
            // Push into leftmost available stack
            int index = available.first();
            Stack<Integer> stack = stacks.get(index);
            stack.push(val);

            if (stack.size() == capacity) {
                available.remove(index);
            }

            rightMost = Math.max(rightMost, index);
        }
    }

    public int pop() {
        while (rightMost >= 0 &&
               (rightMost >= stacks.size() || stacks.get(rightMost).isEmpty())) {
            rightMost--;
        }

        if (rightMost < 0) return -1;

        Stack<Integer> stack = stacks.get(rightMost);
        int val = stack.pop();

        available.add(rightMost);

        return val;
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size()) return -1;

        Stack<Integer> stack = stacks.get(index);
        if (stack.isEmpty()) return -1;

        int val = stack.pop();
        available.add(index);

        return val;
    }
}
