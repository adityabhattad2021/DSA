#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// TODO: Make a singly linked list enter the candidate job title wise.

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

// TODO: change to more appropriate name.
typedef struct singly_linked_list_node
{
    struct doubly_linked_list *job;
    struct singly_linked_list_node *next;
} jobCandidateList;

typedef struct singly_linked_list
{
    struct singly_linked_list_node *head;
} allJobs;

void initializeAllJobs(allJobs *list)
{
    list->head = NULL;
}

/// @brief Initializes the doubly linked list.
/// @param list: The list to initialize.
void initializeListOfCandidate(listOfCandidate *list)
{
    list->job_title = malloc(sizeof(char) * 100);
    list->head = NULL;
}

/// @brief This will create a doubly linked list node(i.e. Candidate), in heap.
/// @param name: Name of the candidate.
/// @param skillLevel: Skill level of the candidate.
/// @return Candidate
Candidate *createNewCandidate(char name[], float skillLevel)
{
    Candidate *newNode = (Candidate *)malloc(sizeof(Candidate));
    newNode->candidateName = malloc(sizeof(char) * 100);
    strcpy(newNode->candidateName, name);
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
void printListOfCandidate(listOfCandidate list)
{
    char listTitle[100];
    strcpy(listTitle, list.job_title);
    if (list.head == NULL)
    {
        printf("\nList for title '%s' is empty\n", listTitle);
    }
    else
    {
        Candidate *temp;
        temp = list.head;
        printf("\nPrinting list for title '%s':", listTitle);
        while (temp)
        {
            printf("\n");
            printf("\nCandidate Name: %s", temp->candidateName);
            printf("\nCandidate Skill Level is: %.2f", temp->skill_level);
            temp = temp->next;
        }
    }
}

/// @brief Add a new doubly linked list not in its resepective doubly linked list according to the job title.
/// @param headOfList: The list of all jobs (i.e. singly linked list containing doubly linked list).
/// @param newCandidate: The candidate to add.
void addCandidateToJobTitle(allJobs *all_jobs, char *title, Candidate *newCandidate)
{
    if (all_jobs->head == NULL)
    {
        printf("asdasd");
        listOfCandidate *newList = NULL;
        newList = (listOfCandidate *)malloc(sizeof(listOfCandidate));
        initializeListOfCandidate(newList);
        strcpy(newList->job_title, title);
        addCandidate(newList, newCandidate);
        jobCandidateList *newNode = (jobCandidateList *)malloc(sizeof(jobCandidateList));
        newNode->job = newList;
        newNode->next = NULL;
        all_jobs->head = newNode;
    }
    else
    {
        jobCandidateList *temp = NULL, *temp2 = NULL;
        temp = all_jobs->head;
        temp2 = all_jobs->head;
        while (temp)
        {
            if (strcmp(temp->job->job_title, title) == 0)
            {
                addCandidate(temp->job, newCandidate);
                return;
            }
            temp=temp->next;
        }
        listOfCandidate *newList = NULL;
        newList = (listOfCandidate *)malloc(sizeof(listOfCandidate));
        initializeListOfCandidate(newList);
        strcpy(newList->job_title, title);
        addCandidate(newList, newCandidate);
        while (temp2->next != NULL)
        {
            temp2 = temp2->next;
        }
        jobCandidateList *newNode = (jobCandidateList *)malloc(sizeof(jobCandidateList));
        newNode->job = newList;
        newNode->next = NULL;
        temp2->next = newNode;
    }
}

void printAllJobs(allJobs all_jobs)
{
    jobCandidateList *temp = NULL;
    temp = all_jobs.head;
    while (temp)
    {
        printf("\n%s-->", temp->job->job_title);
        Candidate *temp2 = NULL;
        temp2 = temp->job->head;
        while (temp2)
        {
            printf("%s->",temp2->candidateName);
            temp2=temp2->next;
        }
        
        temp=temp->next;
    }
}

int main()
{
    allJobs all_jobs;
    initializeAllJobs(&all_jobs);
    while (1)
    {
        printf("Enter the job title: ");
        char jobTitle[100];
        scanf("%s", jobTitle);
        printf("Enter the name of the candidate: ");
        char name[100];
        scanf("%s", name);
        printf("Enter candidate skill level: ");
        float skill;
        scanf("%f", &skill);
        Candidate *newCandidate = createNewCandidate(name, skill);
        addCandidateToJobTitle(&all_jobs, jobTitle, newCandidate);
        printf("To stop press -999, else any other number: ");
        int toStop;
        scanf("%d", &toStop);
        if (toStop == -999)
        {
            break;
        }
    }
    printAllJobs(all_jobs);
    return 0;
}