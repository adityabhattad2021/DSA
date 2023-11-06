class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int x=m;x<m+n;x++){
            nums1[x]=nums2[x-m];
        }
    }
}