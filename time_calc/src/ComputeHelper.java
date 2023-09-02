public class ComputeHelper {

    // Find the minimum and maximum values in the data using brute force approach
    public void bruteForce(Integer[] data) {
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        for (int x = 0; x < data.length; x++) {
            if (data[x] < min) {
                min = data[x];
            }
            if (data[x] > max) {
                max = data[x];
            }
        }
        System.out.printf("\nThe max value is %d and the min value is %d.\n", max, min);
    }

    // Find the minimum and maximum values in the data using divide and conquer approach
    public void divideAndConquer(Integer[] data) {
        // Create a duplicate array for sorting without affecting the original data
        Integer[] duplicateData = data;
        // Sort the duplicate data using merge sort
        mergeSort(duplicateData);
        System.out.printf("\nThe max value is %d and the min value is %d.\n", duplicateData[duplicateData.length - 1], duplicateData[0]);
    }

    // Merge sort algorithm for sorting an array
    public void mergeSort(Integer[] arry) {
        int length = arry.length;
        if (length <= 1) {
            return; // Base case: nothing to sort
        }
        int middle = length / 2;
        Integer[] leftArry = new Integer[middle];
        Integer[] rightArray = new Integer[length - middle];
        int leftIndex = 0, rightIndex = 0;
        for (; leftIndex < length; leftIndex++) {
            if (leftIndex < middle) {
                leftArry[leftIndex] = arry[leftIndex];
            } else {
                rightArray[rightIndex] = arry[leftIndex];
                rightIndex++;
            }
        }

        // Recursively sort the left and right subarrays
        mergeSort(leftArry);
        mergeSort(rightArray);

        int leftArrySize = leftArry.length;
        int rightArraySize = rightArray.length;
        int left = 0, right = 0, main = 0;
        // Merge the sorted left and right subarrays back into the original array
        while (left < leftArrySize && right < rightArraySize) {
            if (leftArry[left] < rightArray[right]) {
                arry[main] = leftArry[left];
                left++;
            } else {
                arry[main] = rightArray[right];
                right++;
            }
            main++;
        }
        // Copy any remaining elements from leftArry and rightArray (if any)
        while (left < leftArrySize || right < rightArraySize) {
            if (left < leftArrySize) {
                arry[main] = leftArry[left];
                left++;
            } else {
                arry[main] = rightArray[right];
                right++;
            }
            main++;
        }
    }
}
