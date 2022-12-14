#include <stdio.h>
#define SIZE 5

typedef struct Queue
{
    int _[SIZE];
    int frontIndex;
    int rearIndex;
    int elementCount;
} circualarQueue;

int initializeQueue(circualarQueue *q)
{
    q->frontIndex = -1;
    q->rearIndex = -1;
    for (int x = 0; x < SIZE; x++)
    {
        q->_[x] = 0;
    }
    q->elementCount=0;
}

int isEmpty(circualarQueue q)
{
    if (q.frontIndex == -1 && q.rearIndex == -1)
    {
        return 1;
    }
    return 0;
}

int isFull(circualarQueue q)
{
    if ((q.rearIndex+1)%SIZE==q.frontIndex)
    {
        return 1;
    }
    return 0;
}

int count(circualarQueue q)
{
    if (q.frontIndex == -1 && q.rearIndex == -1)
    {
        return 0;
    }
    return q.elementCount;
}

void enqueue(circualarQueue *q, int value)
{
    if (isFull(*q))
    {
        printf("\nqueue, overflow.\n");
        return;
    }
    else
    {
        if (q->frontIndex == -1 && q->rearIndex == -1)
        {
            q->frontIndex = 0;
            q->rearIndex = 0;
        }
        else
        {
            q->rearIndex=(q->rearIndex+1)%SIZE;
        }
        q->_[q->rearIndex] = value;
        q->elementCount++;
    }
}

int dequeue(circualarQueue *q)
{
    int elementToReturn = -999;
    if (isEmpty(*q))
    {
        printf("\nqueue, is already is empty.\n");
        return elementToReturn;
    }
    else
    {
        if (q->frontIndex == q->rearIndex)
        {
            elementToReturn = q->_[q->frontIndex];
            q->_[q->frontIndex] = 0;
            q->frontIndex = -1;
            q->rearIndex = -1;
        }
        else
        {
            elementToReturn = q->_[q->frontIndex];
            q->_[q->frontIndex] = 0;
            q->frontIndex=(q->frontIndex+1)%SIZE;
        }
    }
    q->elementCount--;
    return elementToReturn;
}

// Not necessary.
void display(circualarQueue q){
    printf("The circualarQueue is as follows: \n");
    for(int x= 0;x<SIZE;x++){
        printf("%d ",q._[x]);
    }
}