public class App {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        int[] nums1 = {1,2,3,4,0,0,0,0};
        int[] nums2 = {8,8,9,10};
        for(int x:nums1){
            System.out.print(x+" ");
        }
        System.out.println();
        for(int x:nums2){
            System.out.print(x+" ");
        }
        solution.merge(nums1, 4, nums2, 4);
        System.out.println();
        for(int x:nums1){
            System.out.print(x+" ");
        }
    }
}
