#include <stdio.h>
#include<stdlib.h>
#include <string.h>


typedef struct doubly_linked_list_node
{
    char *candidateName;
    float skill_level;
    struct doubly_linked_list_node *next;
    struct doubly_linked_list_node *previous;
} Candidate;

typedef struct doubly_linked_list
{
    char *job_title;
    struct doubly_linked_list_node *head;
} listOfCandidate;

typedef struct singly_linked_list_node
{
    struct doubly_linked_list job;
    struct singly_linked_list_node *next;
} allJobs;

/// @brief Initializes the doubly linked list.
/// @param list: The list to initialize.
void initializeListOfCandidate(listOfCandidate *list){
    list->job_title=malloc(sizeof(char)*100);
    list->head=NULL;
}

/// @brief This will create a doubly linked list node(i.e. Candidate), in heap.
/// @param name: Name of the candidate.
/// @param skillLevel: Skill level of the candidate.
/// @return Candidate
Candidate *createNewCandidate(char name[], float skillLevel)
{
    Candidate *newNode = (Candidate *)malloc(sizeof(Candidate));
    newNode->candidateName=malloc(sizeof(char)*100);
    strcpy(newNode->candidateName,name);
    newNode->skill_level = skillLevel;
    newNode->next = NULL;
    newNode->previous = NULL;
    return newNode;
}


/// @brief Add a candidate(a.k.a doubly linked list node) to the list of the candidate(doubly linked list).
/// @param list: head of the doubly linked list.
/// @param newNode: new candidate(doubly linked list node) to add.
void addCandidate(listOfCandidate *list, Candidate *newNode)
{
    if (list->head == NULL)
    {
        list->head = newNode;
    }
    else
    {
        if (list->head->next == NULL)
        {
            newNode->previous = list->head;
            list->head->next = newNode;
        }
        else
        {
            Candidate *temp = NULL;
            temp = list->head;
            while (temp->next != NULL)
            {
                temp = temp->next;
            }
            temp->next = newNode;
            newNode->previous = temp;
        }
    }
}


/// @brief Print the entire doubly linked list(list of candidate).
/// @param list: The list to be printed.
void printListOfCandidate(listOfCandidate list){
    char listTitle[100];
    strcpy(listTitle,list.job_title);
    if(list.head==NULL){
        printf("\nList for title '%s' is empty\n",listTitle);
    }else{
        Candidate *temp;
        temp=list.head;
        printf("\nPrinting list for title '%s':",listTitle);
        while(temp){
            printf("\n");
            printf("\nCandidate Name: %s",temp->candidateName);
            printf("\nCandidate Skill Level is: %.2f",temp->skill_level);
            temp=temp->next;
        }
    }
}


int main()
{
    listOfCandidate newList;
    initializeListOfCandidate(&newList);
    printf("Enter the job title: ");
    char jobTitle[100];
    scanf("%s", jobTitle);
    strcpy(newList.job_title,jobTitle);
    while(1){
        printf("Enter the name of the candidate: ");
        char name[100];
        scanf("%s",name);
        printf("Enter candidate skill level: ");
        float skill;
        scanf("%f",&skill);
        Candidate *newCandidate=createNewCandidate(name,skill);
        addCandidate(&newList,newCandidate);
        printf("To stop press -999, else any other number: ");
        int toStop;
        scanf("%d",&toStop);
        if(toStop==-999){
            break;
        }
    }
    printListOfCandidate(newList);
    return 0;
}