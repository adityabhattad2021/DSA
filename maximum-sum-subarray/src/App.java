public class App {
    public static void main(String[] args) throws Exception {
        int[] arry = {-2,-5,6,-2,-3,1,5,-6};
        System.out.println(maxSumSubarray(arry,0,arry.length-1));
    }

    public static int maxSumSubarray(int[] arry,int start,int end){
        if(start==end){
            return arry[start];
        }
        
        int mid =  (start+end)/2;
        int leftMax = maxSumSubarray(arry, start, mid);
        int rightMax = maxSumSubarray(arry, mid+1, end);
        int crossSum = maxCrossSum(arry,start,mid,end);
         
        // return crossSum > (leftMax > rightMax ? leftMax : rightMax) ? crossSum : (leftMax > rightMax ? leftMax : rightMax); 
        return Math.max(crossSum, Math.max(leftMax, rightMax));
    }

    private static int maxCrossSum(int[] arry, int start, int mid, int end) {
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for(int x = mid;x>=0;x--){
            sum+=arry[x];
            if(sum>leftMax){
                leftMax=sum;
            }
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for(int x=mid+1;x<=end;x++) {
            sum+=arry[x];
            if(sum>rightMax){
                rightMax=sum;
            }
        }

        return leftMax+rightMax;
    }

}