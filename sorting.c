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


    9. Count Sort -Time Complexity: O(n) Index Based Sorts
    10. Bucket Sort -Time Complexity: O(n) Index Based Sorts
    11. Radix Sort -Time Complexity: O(n) Index Based Sorts


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

#include <stdio.h>

int main()
{
    int array[] = {3, 2, 1, 5, 6, 9, 12, 14, 15, 14, 0, 4};
    int sizeOfArray = sizeof(array) / sizeof(array[0]);
    printf("\nUnsorted array is : ");
    for (int x = 0; x < sizeOfArray; x++)
    {
        printf(" %d ", array[x]);
    }
    shellSort(array, sizeOfArray);
    printf("\nSorted array is   : ");
    for (int x = 0; x < sizeOfArray; x++)
    {
        printf(" %d ", array[x]);
    }
    printf("\n");
    return 0;
}