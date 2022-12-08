#include <stdio.h>

/*
    Algorithms to be covered:

    1. Bubble Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts ✅
    2. Insertion Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts ✅
    3. Selection Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts ✅

    4. Heap Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts
    5. Merge Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts ✅
    6. Quick Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts ✅

    7. Tree Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts

    8. Shell Sort -Time Complexity: O(n^(3/2)) or O(n log(n)) Comparsion Basd Sorts  ✅


    9. Count Sort -Time Complexity: O(n) Index Based Sorts ✅ (only implemented for positive integer values)
    10. Bucket Sort -Time Complexity: O(n) Index Based Sorts
    11. Radix Sort -Time Complexity: O(n) Index Based Sorts ✅


    Criteria on which sorts should be analized
    1.Number of comparisions
    2.Number of swaps
    3.Adaptability : In a few sorting algorithms, the complexity changes based  on pre-sorted input i.e. pre-sorted array of the input affects the running  time. The algorithms that take this adaptability into account are known to be adaptive algorithms. For example – Quick sort is an adaptive sorting algorithm because the time complexity of Quick sort depends on the  initial input sequence. If input is already sorted then time complexity becomes O(n^2) and if input sequence is not sorted then time complexity becomes O(n logn).
    4.Stable: Preserves order of repeating elements.
    5.Extra memory
*/

/*
    BUBBLE SORT
    Number of comparisions=O(n^2).
    Number of swaps=O(n^2).
    We can make it adaptive (i.e. Only compares one time if array is aready sorted).
    It is stable.
    It takes only one extra memory slot.
    Space Complexity O(1)
*/
void BubbleSort(int arry[], int size)
{
    for (int x = 0; x < size - 1; x++)
    {
        for (int y = 0; y < size - x - 1; y++)
        {
            if (arry[y] > arry[y + 1])
            {
                int temp = arry[y];
                arry[y] = arry[y + 1];
                arry[y + 1] = temp;
            }
        }
    }
}
void adaptiveBubbleSort(int arry[], int size)
{
    for (int x = 0; x < size - 1; x++)
    {
        int alreadySorted = 1;
        for (int y = 0; y < size - x - 1; y++)
        {
            if (arry[y] > arry[y + 1])
            {
                alreadySorted = 0;
                int temp = arry[y];
                arry[y] = arry[y + 1];
                arry[y + 1] = temp;
            }
        }
        if (alreadySorted)
        {
            return;
        }
    }
}

/*
    INSERTION SORT
    Number of comparisions=O(n^2)
    Number of swaps=O(n^2)
    It is adaptive by nature
    In best case its time complexity is O(n)
    It is stable
    It takes only one extra memory slot.
    Space Complexity O(1)
*/
void insertionSort(int arry[], int size)
{
    for (int x = 1; x < size; x++)
    {
        int key = arry[x];
        int y = x - 1;
        while (y >= 0 && arry[y] > key)
        {
            arry[y + 1] = arry[y];
            y--;
        }
        arry[y + 1] = key;
    }
}

/*
    SELECTION SORT
    Number of Comparisions= O(n^2)
    Number of Swaps= O(1)
    It cannot be made adaptive.
    It is not stable.
    It only performs one swap in any case.
    It takes only one extra memory slot.
    Space Complexity O(1)
*/
void selectionSort(int arry[], int size)
{
    for (int x = 0; x < size - 1; x++)
    {
        int indexOfMin = x;
        for (int y = x + 1; y < size; y++)
        {
            if (arry[indexOfMin] > arry[y])
            {
                indexOfMin = y;
            }
        }
        int temp = arry[indexOfMin];
        arry[indexOfMin] = arry[x];
        arry[x] = temp;
    }
}

/*
    QUICK SORT
    It is also called Selection Exchange Sort and Partition Exchange Sort.
    It moves smaller elements to the left of a pivot.
    recursively divides array into two partitions.
    It is adaptive.
    It is not stable.
    Best case O(n log(n))
    Average case O(n log(n))
    Worst case O(n^2)
    Space Complexity O(log(n)) due to recursion

*/
void quickSort(int arr[], int startingIndex, int endingIndex)
{
    if (endingIndex <= startingIndex)
    {
        return;
    }
    int pivotElement = arr[endingIndex];
    int y = startingIndex - 1;
    for (int x = startingIndex; x < endingIndex; x++)
    {
        if (arr[x] < pivotElement)
        {
            y++;
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }
    y++;
    int temp = arr[y];
    arr[y] = arr[endingIndex];
    arr[endingIndex] = temp;
    quickSort(arr, startingIndex, y - 1);
    quickSort(arr, y + 1, endingIndex);
}

/*
    MERGE SORT
    Recursively divides array in 2, sort, re-combine
    It is not adaptive
    It is stable
    Time Complexity : O(n log(2))
    Space Complexity : O(n)
*/
// Helper Function.(To get the idea of merge clearly)
// void merge(int motherArry[], int leftArry[], int rightArry[], int motherArrySize)
// {
//     int leftArraySize = motherArrySize / 2;
//     int rightArraySize = motherArrySize - leftArraySize;
//     int l = 0, r = 0, main = 0;
//     while (l < leftArraySize && r < rightArraySize)
//     {
//         if (leftArry[l] > rightArry[r])
//         {
//             motherArry[main] = rightArry[r];
//             main++;
//             r++;
//         }
//         else
//         {
//             motherArry[main] = leftArry[l];
//             main++;
//             l++;
//         }
//     }
//     while (l < leftArraySize)
//     {
//         motherArry[main] = leftArry[l];
//         main++;
//         l++;
//     }
//     while (r < rightArraySize)
//     {
//         motherArry[main] = rightArry[r];
//         main++;
//         r++;
//     }
// }
void mergeSort(int arry[], int size)
{
    if (size < 2)
    {
        return;
    }
    int leftArrySize = size / 2;
    int rightArrySize = size - leftArrySize;
    int leftArry[leftArrySize];
    int rightArry[rightArrySize];
    int leftArryIndex = 0, rightArryIndex = 0;
    for (int i = 0; i < size; i++)
    {
        if (i < (size / 2))
        {
            leftArry[leftArryIndex] = arry[i];
            leftArryIndex++;
        }
        else
        {
            rightArry[rightArryIndex] = arry[i];
            rightArryIndex++;
        }
    }
    mergeSort(leftArry, leftArrySize);
    mergeSort(rightArry, rightArrySize);
    // merge(arry,leftArry,rightArry,size);

    int leftArraySize = size / 2;
    int rightArraySize = size - leftArraySize;
    int l = 0, r = 0, main = 0;
    while (l < leftArraySize && r < rightArraySize)
    {
        if (leftArry[l] > rightArry[r])
        {
            arry[main] = rightArry[r];
            main++;
            r++;
        }
        else
        {
            arry[main] = leftArry[l];
            main++;
            l++;
        }
    }
    while (l < leftArraySize)
    {
        arry[main] = leftArry[l];
        main++;
        l++;
    }
    while (r < rightArraySize)
    {
        arry[main] = rightArry[r];
        main++;
        r++;
    }
}

/*
    SHELL SORT
    It is an in-place comparison sort.
    It is mainly variation of sorting by exchange (bubble sort) or sorting by insertion (insertion sort)
    It is adaptive
    It is not stable
    Time complexity: best: O(n log(n)) worst: O(n^2)
    Space complexity: O(1)
*/
void shellSort(int arry[], int size)
{
    for (int gap = size / 2; gap > 0; gap /= 2)
    {
        for (int x = gap; x < size; x++)
        {
            int temp = arry[x];
            int y = 0;
            for (y = x; y >= gap && arry[y - gap] > temp; y -= gap)
            {
                arry[y] = arry[y - gap];
            }
            arry[y] = temp;
        }
    }
}

/*
    COUNT SORT
    Counting sort is a sorting algorithm that sorts the elements of an array by counting the number of occurrences of each unique element in the arry.

    The count is stored in an auxillary array and the sorting is done by mapping the count as an index of auxillary array. This mapping is done by performing arithematic calculations on those counts to determine
    the position of each key value(unique element) in the output sequence.

    It is often as a subroutine in another sorting algorithm, radix sort, that can handle larger keys more efficiently.

    It is NOT a comparison based sort.
    It is not adaptive.
    It can be made stable. (refer)
    https://stackoverflow.com/questions/2572195/how-is-counting-sort-a-stable-sort
    Time Complexity: O(n+k)
    Space Complexity: O(n+k)
    where n is the number of elements in input array and k is the range of input.
*/
int maxOfTwo(int a, int b)
{
    return a > b ? a : b;
}

// Count sort only for positive elements
int maxInArry(int arry[], int size)
{
    int max = 0;
    for (int x = 0; x < size; x++)
    {
        max = maxOfTwo(max, arry[x]);
    }
    return max;
}

void countSort(int arry[], int size)
{
    int maxIntInArry = maxInArry(arry, size);
    int rangeOfCountArry = maxIntInArry + 1;
    int countArry[rangeOfCountArry];
    for (int x = 0; x < rangeOfCountArry; x++)
    {
        countArry[x] = 0;
    }
    for (int y = 0; y < size; y++)
    {
        countArry[arry[y]]++;
    }
    int indexForMainArry = 0;
    for (int z = 0; z < rangeOfCountArry; z++)
    {
        while (countArry[z] != 0)
        {
            arry[indexForMainArry] = z;
            indexForMainArry++;
            countArry[z]--;
        }
    }
}

/*
    RADIX SORT
    Radix sort is a non-competative sorting algorithm.
    It avoids comparison by creating and distributing elements into buckets according to their radix.
    For elements with more than one significant digit, this bucketing process is repeated for each digit, while preserving the ordering of the prior step, until all the digits have been considered. For this reason, radix sort has also been called bucket sort and digital sort.
    Typically radix sort uses counting sort as a subroutine to sort.
    It is not adaptive.
    It is stable.
    Time Complexity: O d(n+k)
    Space Complexity: O(n+k)
    where 'd' is the number of digits the max element in the array has and 'n' is the number of elements in the input array and 'k' is the range og input.
*/

void countSortForRadixSort(int arry[], int size, int decimal)
{
    int buffer[size];

    int countArry[10];

    for (int i = 0; i < 10; i++)
    {
        countArry[i] = 0;
    }

    for (int j = 0; j < size; j++)
    {
        countArry[(arry[j] / decimal) % 10]++;
    }

    for (int k = 1; k < 10; k++)
    {
        countArry[k] += countArry[k - 1];
    }

    for (int l = size - 1; l >= 0; l--)
    {
        buffer[countArry[(arry[l] / decimal) % 10] - 1] = arry[l];
        countArry[(arry[l] / decimal) % 10]--;
    }

    for (int m = 0; m < size; m++)
    {
        arry[m] = buffer[m];
    }
}

void radixSort(int arry[], int size)
{
    int max = maxInArry(arry, size);
    for (int decimal = 1; max / decimal > 0; decimal *= 10)
    {
        countSortForRadixSort(arry, size, decimal);
    }
}

int main()
{
    int array[] = {3, 2, 1, 5, 6, 9, 12, 14, 15, 14, 0, 4};
    int sizeOfArray = sizeof(array) / sizeof(array[0]);
    printf("\nUnsorted array is : ");
    for (int x = 0; x < sizeOfArray; x++)
    {
        printf(" %d ", array[x]);
    }
    radixSort(array, sizeOfArray);
    printf("\nSorted array is   : ");
    for (int x = 0; x < sizeOfArray; x++)
    {
        printf(" %d ", array[x]);
    }
    printf("\n");
    return 0;
}