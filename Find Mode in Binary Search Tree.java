/*
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    Integer prev = null;
    int count = 0;
    int maxCount = 0;

    public int[] findMode(TreeNode root) {
        // First pass: find max frequency
        inorder(root);

        // Prepare for second pass
        prev = null;
        count = 0;

        List<Integer> result = new ArrayList<>();
        inorderCollect(root, result);

        // Convert list to array
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    // First inorder traversal
    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != null && node.val == prev) {
            count++;
        } else {
            count = 1;
        }

        maxCount = Math.max(maxCount, count);
        prev = node.val;

        inorder(node.right);
    }

    // Second inorder traversal
    private void inorderCollect(TreeNode node, List<Integer> result) {
        if (node == null) return;

        inorderCollect(node.left, result);

        if (prev != null && node.val == prev) {
            count++;
        } else {
            count = 1;
        }

        if (count == maxCount) {
            result.add(node.val);
        }

        prev = node.val;

        inorderCollect(node.right, result);
    }
}

