/*
    Algorithms to be covered:

    1. Bubble Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts ✅
    2. Insertion Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts
    3. Selection Sort  -Time Complexity: O(n^2) Comparsion Basd Sorts

    4. Heap Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts
    5. Merge Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts
    6. Quick Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts

    7. Tree Sort -Time Complexity: O(nlog(n)) Comparsion Basd Sorts

    8. Shell Sort -Time Complexity: O(n^(3/2)) Comparsion Basd Sorts


    9. Count Sort -Time Complexity: O(n) Index Based Sorts
    10. Bucket Sort -Time Complexity: O(n) Index Based Sorts
    11. Radix Sort -Time Complexity: O(n) Index Based Sorts


    Criteria on which sorts should be analized
    1.Number of comparisions
    2.Number of swaps
    3.Adaptive
    4.Stable: Preserves order of repeating element
    5.Extra memory
*/

/*
    BUBBLE SORT
    Number of comparisions=O(n^2).
    Number of swaps=O(n^2).
    We can make it adaptive (i.e. Only compares one time if array is aready sorted).
    It is stable.
    It takes only one extra memory slot.
*/
void adaptiveBubbleSort(int arry[], int size)
{
    for (int x = 0; x < size - 1; x++)
    {
        int alreadySorted=1;
        for (int y = 0; y < size - x - 1; y++)
        {
            if (arry[y] > arry[y + 1])
            {
                alreadySorted=0;
                int temp = arry[y];
                arry[y] = arry[y + 1];
                arry[y + 1] = temp;
            }
        }
        if(alreadySorted){
            return;
        }
    }
}

#include<stdio.h>

int main(){
    int array[]={3,2,1,5,6,9,12,14,15,14,0,4};
    int size=sizeof(array)/sizeof(array[0]);
    printf("\nUnsorted array is: ");
    for(int x = 0;x<size;x++){
        printf(" %d ",array[x]);
    }
    adaptiveBubbleSort(array,size);
    printf("\nSorted array is:   ");
    for(int x = 0;x<size;x++){
        printf(" %d ",array[x]);
    }
    printf("\n");
    return 0;
}