#include <stdio.h>
#define SIZE 5

typedef struct Queue
{
    int _[SIZE];
    int frontIndex;
    int rearIndex;
} queue;

void initializeQueue(queue *q)
{
    q->frontIndex = -1;
    q->rearIndex = -1;
    for (int x = 0; x < SIZE; x++)
    {
        q->_[x] = 0;
    }
}

int isEmpty(queue q)
{
    if (q.frontIndex == -1 && q.rearIndex == -1)
    {
        return 1;
    }
    return 0;
}

int isFull(queue q)
{
    if (q.rearIndex > SIZE - 2)
    {
        return 1;
    }
    return 0;
}

int count(queue q)
{
    if (q.frontIndex == -1 && q.rearIndex == -1)
    {
        return 0;
    }
    return (q.rearIndex - q.frontIndex) + 1;
}

void enqueue(queue *q,int value){
    if(q->rearIndex > SIZE - 2){
        printf("\nqueue, overflow.\n");
        return;
    }
    else{
        if(q->frontIndex == -1 && q->rearIndex == -1){
            q->frontIndex=0;
            q->rearIndex=0;
        }else{
            q->rearIndex++;
        }
        q->_[q->rearIndex]=value;
    }
}

int dequeue(queue *q,int value){
    int elementToReturn=-999;
    if(q->frontIndex == -1 && q->rearIndex == -1){
        printf("\nqueue, is already is empty.\n");
        return elementToReturn;
    }else{
        if(q->frontIndex==q->rearIndex){
            elementToReturn=q->_[q->frontIndex];
            q->_[q->frontIndex]=0;
            q->frontIndex=-1;
            q->rearIndex=-1;
        }else{
            elementToReturn=q->_[q->frontIndex];
            q->_[q->frontIndex]=0;
            q->frontIndex++;
        }
    }
    return elementToReturn;
}