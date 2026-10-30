class Solution {
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int i=0,j=0;
      int n1=arr1.length;
      int n2=arr2.length;
      int n=n1+n2;

      int indx2=n/2;
      int indx1=indx2-1;

      int count=0;
      int indexle=-1,indexle2=-1;

      while(i<n1 && j<n2) {
        if(arr1[i]<arr2[j]) {
          if(count==indx1) indexle=arr1[i];
          if(count==indx2) indexle2=arr1[i];
          count++;
          i++;
        }
        else {
          if(count==indx1) indexle=arr2[j];
          if(count==indx2) indexle2=arr2[j];
          count++;
          j++;
        }
      }
      while(i<n1) {
        if(count==indx1) indexle=arr1[i];
          if(count==indx2) indexle2=arr1[i];
          count++;
          i++;
      }
      while(j<n2) {
        if(count==indx1) indexle=arr2[j];
          if(count==indx2) indexle2=arr2[j];
          count++;
          j++;
      }

      if(n%2==1) return indexle2;
      return (indexle+indexle2)/2.0;

    }
}