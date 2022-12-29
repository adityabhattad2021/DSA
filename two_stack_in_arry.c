#include<stdio.h>
#define MAX_SIZE 100 // maximum size of the array

int array[MAX_SIZE]; // array to store the two queues
int front1 = 0; // front index for Queue1
int rear1 = -1; // rear index for Queue1
int front2 = MAX_SIZE - 1; // front index for Queue2
int rear2 = MAX_SIZE; // rear index for Queue2

// function to check if Queue1 is full
int isFull1()
{
  if (rear1 + 1 == front2)
  {
    return 1;
  }
  return 0;
}

// function to check if Queue2 is full
int isFull2()
{
  if (rear2 - 1 == front1)
  {
    return 1;
  }
  return 0;
}

// function to check if Queue1 is empty
int isEmpty1()
{
  if (front1 > rear1)
  {
    return 1;
  }
  return 0;
}

// function to check if Queue2 is empty
int isEmpty2()
{
  if (front2 < rear2)
  {
    return 1;
  }
  return 0;
}

// function to add an element to Queue1
void enqueue1(int value)
{
  if (isFull1())
  {
    printf("Queue1 is full\n");
  }
  else
  {
    rear1++;
    array[rear1] = value;
  }
}

// function to add an element to Queue2
void enqueue2(int value)
{
  if (isFull2())
  {
    printf("Queue2 is full\n");
  }
  else
  {
    rear2--;
    array[rear2] = value;
  }
}

// function to remove an element from Queue1
int dequeue1()
{
  if (isEmpty1())
  {
    printf("Queue1 is empty\n");
    return -1;
  }
  else
  {
    int value = array[front1];
    front1++;
    return value;
  }
}

// function to remove an element from Queue2
int dequeue2()
{
  if (isEmpty2())
  {
    printf("Queue2 is empty\n");
    return -1;
  }
  else
  {
    int value = array[front2];
    front2--;
    return value;
  }
}


int main()
{
  // add elements to Queue1
  enqueue1(1);
  enqueue1(2);
  enqueue1(3);

  // add elements to Queue2
  enqueue2(4);
  enqueue2(5);
  enqueue2(6);

  // remove elements from Queue1
  int value = dequeue1();
  printf("Removed element from Queue1: %d\n", value);
  value = dequeue1();
  printf("Removed element from Queue1: %d\n", value);

  // remove elements from Queue2
  value = dequeue2();
  printf("Removed element from Queue2: %d\n", value);
  value = dequeue2();
  printf("Removed element from Queue2: %d\n", value);

  return 0;
}
