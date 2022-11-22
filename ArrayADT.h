#include <stdio.h>
#include <stdlib.h>
#define CAPACITY 10

typedef struct Array
{
    int array[CAPACITY];
    int lastIndex;
} array;

void getInputForArry(int *num, char *promptText)
{
    printf("%s", promptText);
    scanf("%d", num);
}

/*
    Fills all the elements of array to be -999.
*/
void initializeArry(struct Array *arr)
{
    arr->lastIndex = -1;
}

/*
    Takes integer elements as input.
*/
void createArry(struct Array *arr)
{
    int element;
    for (int x = 0; x < CAPACITY; x++)
    {
        getInputForArry(&element, "Enter an element to add in array: (enter -999 to quit): ");
        if (element != -999)
        {
            arr->lastIndex += 1;
            arr->array[arr->lastIndex] = element;
        }
        else
        {
            return;
        }
    }
}

/*
    Prints all the elements in the array considering it is not -999.
*/
void printArry(struct Array arr)
{
    printf("\n");
    int x = 0;
    while (x <= arr.lastIndex && x < CAPACITY)
    {
        printf("%d ", arr.array[x++]);
    }
}

/*
    Deletes an element from an array at a given index.
*/
void deleteElementFromArry(struct Array *arr)
{
    int indexToDelete;
    getInputForArry(&indexToDelete, "\nEnter the index of element to delete from an array: ");
    indexToDelete--;
    int lastIndex = arr->lastIndex;

    if (indexToDelete >= 0 && indexToDelete < lastIndex)
    {
        int x;
        for (x = indexToDelete; x < lastIndex; x++)
        {
            arr->array[x] = arr->array[x + 1];
        }
        arr->lastIndex -= 1;
    }
    else
    {
        printf("\nInvaid index.");
    }
}

// /*
//     Insert an element in an array at a given index.
// */
void insertElementInArray(struct Array *arr)
{
    int indexToAdd;

    int allowedIndex = arr->lastIndex;
    if (allowedIndex >= CAPACITY - 1)
    {
        printf("\nArray full, cannot add elements");
        return;
    }
    getInputForArry(&indexToAdd, "\nEnter the index number to add element in array: ");
    indexToAdd--;
    if (indexToAdd <= allowedIndex)
    {
        for (int x = allowedIndex; x >= indexToAdd; x--)
        {
            arr->array[x + 1] = arr->array[x];
        }
        int elementToAdd;
        getInputForArry(&elementToAdd, "Enter an element to add: ");
        arr->array[indexToAdd] = elementToAdd;
        arr->lastIndex += 1;
    }
    else
    {
        printf("\nInvalid index entered.");
    }
}

// /*
//     Checks if array is full.
// */
void isArryFull(struct Array arr)
{
    int isArrayFull = (arr.lastIndex >= CAPACITY - 1) ? 1 : 0;
    if (isArrayFull)
    {
        printf("\nArray is full.\n");
    }
    else
    {
        printf("\nArray is not full.\n");
    }
}

/*
    Checks if array is empty.
*/
void isArryEmpty(struct Array arr)
{
    if (arr.lastIndex == -1)
    {
        printf("\nArray is empty.");
        return;
    }
    printf("\nArray is not empty.");
}

/*
    prints length of array.
*/
int lengthOfArry(struct Array arr)
{
    printf("\nLength of the arry is %d.", arr.lastIndex + 1);
}

void searchElementOfArry(struct Array arr)
{
    int elementToSearch;
    getInputForArry(&elementToSearch, "\nEnter the element to search: ");
    for (int x = 0; x <= arr.lastIndex; x++)
    {
        if (arr.array[x] == elementToSearch)
        {
            printf("\n%d is present at index %d.", elementToSearch, x);
            return;
        }
    }
    printf("\n%d is not present in the array.", elementToSearch);
}

void sortArry(struct Array *arr)
{

    for (int x = 0; x <= arr->lastIndex; x++)
    {
        for (int y = 0; y < (arr->lastIndex) - x; y++)
        {
            if (arr->array[y] > arr->array[y + 1])
            {
                int temp;
                temp = arr->array[y];
                arr->array[y] = arr->array[y + 1];
                arr->array[y + 1] = temp;
            }
        }
    }
    printf("Array sorted successfully.");
}

void binarySearch(struct Array arr)
{
    int elementToSearch;
    getInputForArry(&elementToSearch, "\nEnter the element to search: ");
    int low = 0;
    int high = arr.lastIndex;
    int mid;
    while (high >= low)
    {
        mid = (low + high) / 2;
        if (arr.array[mid] == elementToSearch)
        {
            printf("\nElement %d was found at %d index.\n", elementToSearch, mid);
            for (int x = 0; x <= arr.lastIndex; x++)
            {
                if (x == mid)
                {
                    printf(" (%d) ", arr.array[x]);
                }
                else
                {
                    printf(" %d ", arr.array[x]);
                }
            }
            return;
        }
        else if (arr.array[mid] > elementToSearch)
        {
            high = mid - 1;
        }
        else
        {
            low = mid + 1;
        }
    }
    printf("%d is not present in the array", elementToSearch);
}

void sumOfElementsOfArray(array arr)
{
    int sum = 0;
    for (int x = 0; x <= arr.lastIndex; x++)
    {
        sum += arr.array[x];
    }
    printf("\nSum of all the elements of the array is %d\n", sum);
}

void copyAnArray(array *copiedArr, array toCopyArr)
{
    if (toCopyArr.lastIndex == -1)
    {
        printf("\nArray has no element to copy.\n");
        return;
    }
    for (int x = 0; x <= toCopyArr.lastIndex; x++)
    {
        copiedArr->lastIndex = x;
        copiedArr->array[copiedArr->lastIndex] = toCopyArr.array[x];
    }
    printf("\nArray copy operation successfull.\n");
}

void appendArrayInArray(array *toAppendIn, array toAppend)
{
    if (((toAppendIn->lastIndex + 1) + (toAppend.lastIndex + 1)) > CAPACITY)
    {
        printf("%d %d", (toAppendIn->lastIndex + 1) + (toAppend.lastIndex + 1), CAPACITY);
        printf("Array not empty enough to complete merge operation.");
        return;
    }
    toAppendIn->lastIndex += 1;
    int x;
    for (x = 0; x <= toAppend.lastIndex; x++)
    {
        toAppendIn->array[(toAppendIn->lastIndex) + x] = toAppend.array[x];
    }
    toAppendIn->lastIndex += x - 1;
    printf("\nArray append operation successfull.\n");
}


void reverseAnArray(array *arr)
{
    int x = 0;
    int bufferArr[CAPACITY];
    for (int y = arr->lastIndex; y >= 0; y--)
    {
        bufferArr[x] = arr->array[y];
        x++;
    }
    for (int x = 0; x <= arr->lastIndex; x++)
    {
        arr->array[x] = bufferArr[x];
    }
    printf("\nArray reverse operation successfull.\n");
}