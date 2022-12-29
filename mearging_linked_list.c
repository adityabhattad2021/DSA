/*
    Algorithm:
    Traverse both lists and find the lengths n and m of each list.
    Set two pointers p1 and p2 to the head of each list.
    Set a counter c to 0.
    While c < abs(n - m), increment c and advance p1 or p2, depending on which list has the greater length.
    Advance both pointers, one node at a time, until they meet at the merging point or reach the end of the lists (in which case the lists do not intersect).
*/

/*
  With Stack
  Push the nodes of the first list onto one stack.
  Push the nodes of the second list onto another stack.
  Pop a node from each stack. If the nodes are different, repeat this step.
  If the nodes are the same, return the node. If one of the stacks is empty, return NULL to indicate that the lists do not intersect.
*/



// Code
#include<stdio.h>
#include<stdlib.h>

struct Node {
  int data;
  struct Node* next;
};

struct Node* findMergingPoint(struct Node* head1, struct Node* head2) {
  // find the lengths of each list
  int n = 0;
  struct Node* p = head1;
  while (p != NULL) {
    n++;
    p = p->next;
  }
  int m = 0;
  p = head2;
  while (p != NULL) {
    m++;
    p = p->next;
  }

  // set the pointers to the heads of each list
  struct Node* p1 = head1;
  struct Node* p2 = head2;

  // advance the pointers as needed
  int c = 0;
  while (c < abs(n - m)) {
    if (n > m) {
      p1 = p1->next;
    } else {
      p2 = p2->next;
    }
    c++;
  }

  // advance both pointers until they meet at the merging point or reach the end of the lists
  while (p1 != NULL && p2 != NULL) {
    if (p1 == p2) {
      return p1; // found the merging point
    }
    p1 = p1->next;
    p2 = p2->next;
  }

  return NULL; // lists do not intersect
}


int main() {
  // create two lists that intersect at the third node
  struct Node n1, n2, n3, n4, n5, n6;
  n1.data = 1;
  n1.next = &n2;
  n2.data = 2;
  n2.next = &n3;
  n3.data = 3;
  n3.next = &n4;
  n4.data = 4;
  n4.next = NULL;
  n5.data = 5;
  n5.next = &n6;
  n6.data = 6;
  n6.next = &n2;

  // find the merging point
  struct Node* mergingPoint = findMergingPoint(&n1, &n5);
  if (mergingPoint != NULL) {
    printf("The merging point is at node with data %d\n", mergingPoint->data);
  } else {
    printf("The lists do not intersect\n");
  }

  return 0;
}
