// Problem: 706. Intersection of two sorted arrays
// Source: https://takeuforward.org/practice/dsa/intersection-of-two-sorted-arrays
// Synced: 2026-10-20T10:44:15.298Z

import java.util.*;

class Solution {
    public int[] intersectionArray(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[li