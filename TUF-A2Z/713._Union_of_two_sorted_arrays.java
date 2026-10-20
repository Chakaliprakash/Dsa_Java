// Problem: 713. Union of two sorted arrays
// Source: https://takeuforward.org/practice/dsa/union-of-two-sorted-arrays
// Synced: 2026-10-20T10:35:17.681Z

        }

            if (union.isEmpty() || union.get(union.size() - 1) != val) {
                union.add(val);
            }
                val = nums2[j++];
            }

            if (nums1[i] <= nums2[j]) {
                val = nums1[i++];
            } else {
        while (i < nums1.length && j < nums2.length) {
            int val;

        int i = 0, j = 0;

        ArrayList<Integer> union = new ArrayList<>();

class Solution {
    public int[] unionArray(int[] nums1, int[] nums2) {

import java.util.*;