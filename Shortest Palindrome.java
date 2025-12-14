/*
You are given a string s. You can convert s to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

 

Example 1:

Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: s = "abcd"
Output: "dcbabcd"
 

Constraints:

0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
*/


class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return s;

        String rev = new StringBuilder(s).reverse().toString();
        String temp = s + "#" + rev;

        int[] lps = new int[temp.length()];

        for (int i = 1; i < temp.length(); i++) {
            int j = lps[i - 1];

            while (j > 0 && temp.charAt(i) != temp.charAt(j)) {
                j = lps[j - 1];
            }

            if (temp.charAt(i) == temp.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        int palLen = lps[temp.length() - 1];
        String add = s.substring(palLen);

        return new StringBuilder(add).reverse().toString() + s;
    }
}
