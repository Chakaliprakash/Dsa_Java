// Problem: 713. Union of two sorted arrays
// Source: https://takeuforward.org/practice/dsa/union-of-two-sorted-arrays
// Synced: 2026-10-20T10:33:55.735Z

import java.util.*;

class Solution {
    public int[] unionArray(int[] nums1, int[] nums2) {
        ArrayList<Integer> union = new ArrayList<>();

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            int val;

            if (nums1[i] <= nums2[j]) {
                val = nums1[i++];