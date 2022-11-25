#include <stdio.h>
#include <stdlib.h>

void infiniteArray()
{
    int *p, *q;
    int initialSize = 5;
    p = (int *)malloc(initialSize * sizeof(int));
    int sizeCounter = 0;
    while (1)
    {
        printf("Enter a number to add inside the infinite array (-999 to stop): ");
        int buffer;
        scanf("%d", &buffer);
        if (buffer == -999)
        {
            break;
        }
        if (sizeCounter < initialSize)
        {
            *(p + sizeCounter) = buffer;
            sizeCounter++;
        }
        else{
            int oldInitialSize=initialSize;
            initialSize*=2;
            q=(int *)malloc(initialSize*sizeof(int));
            for(int x=0;x<oldInitialSize;x++){
                *(q+x)=*(p+x);
            }         
            free(p);
            p=q;
            q=NULL;
            p[sizeCounter] = buffer;
            sizeCounter++;
        }
    }
    printf("Array is: ");
    for(int x=0;x<sizeCounter;x++){
        printf("%d ",*(p+x));
    }
    free(p);
}

int main()
{
    infiniteArray();
    return 0;
}


