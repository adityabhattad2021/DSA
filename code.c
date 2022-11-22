#include"ArrayADT.h"



int main()
{
    array arry, dupicateArry;
    initializeArry(&arry);
    initializeArry(&dupicateArry);
    isArryEmpty(arry);
    createArry(&arry);
    printf("\nEnter values for duplicate array.\n");
    createArry(&dupicateArry);
    printArry(arry);
    insertElementInArray(&arry);
    printArry(arry);
    deleteElementFromArry(&arry);
    printArry(arry);
    searchElementOfArry(arry);
    lengthOfArry(arry);
    isArryFull(arry);
    sortArry(&arry);
    printArry(arry);
    binarySearch(arry);
    printArry(arry);
    sumOfElementsOfArray(arry);
    copyAnArray(&dupicateArry,arry);
    printArry(dupicateArry);
    appendArrayInArray(&arry,dupicateArry);
    printArry(arry);
    reverseAnArray(&arry);
    printArry(arry);
    return 0;
}


