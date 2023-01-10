/*
    What is an expression?
    => An expression is a combination of symbols that can be numbers (constants), variables, operations, symbols of grouping and other punctuation written in a specific way

    Operands: numbers, variables, symbols of grouping and other punctuation
    Operators: entity that decides the operation to be performed on the operands.


    e.g. 2 + 3 * 4 - 5 / 6 + 7 here 2, 3, 4, 5, 6, 7 are operands and +, -, *, / are operators

    Infix notation: An expression is said to be in infix notation if the operator is written in between the operands.
    e.g. 2 + 3 * 4 - 5 / 6 + 7

    Prefix notation: An expression is said to be in prefix notation if the operator is written before the operands.
    e.g. + 2 - * 3 4 / 5 6 + 7

    Postfix notation: An expression is said to be in postfix notation if the operator is written after the operands.
    e.g. 2 3 4 * + 5 6 / - 7 +


    Why do we need prefix and postfix notation?
    => Infix notation is not suitable for computers to evaluate the expression because it is not easy to determine the precedence of the operators.

    Order of precedence of operators:
    1. Parenthesis ()
    2. Exponentiation ^
    3. Multiplication, division, modulus * / %
    4. Addition, subtraction + -


*/

/*
    Infix to postfix conversion using stack
    Algorithm:
    1. Scan the infix expression from left to right.
    2. If the scanned character is an operand, output it.
    3. If operator arrives and the stack is empty, push it onto the stack.
    4. If the operator has higher precedence than the operator at the top of the stack, push it onto the stack.
    5. If the operator has lower precedence than the operator at the top of the stack, pop the stack and output it. Repeat step 5 until the operator at the top of the stack has lower precedence than the operator being scanned.
    6. If the operator has equal precedence with the operator at the top of the stack, use associativity rule. If the associativity is left to right, pop the stack and output it. Repeat step 5 until the operator at the top of the stack has lower precedence than the operator being scanned. If the associativity is right to left, push the operator onto the stack.
    7. If the scanned character is a left parenthesis, push it onto the stack.
    8. If the scanned character is a right parenthesis, pop the stack and output it until a left parenthesis is encountered. Discard both the parenthesis.
    9. Repeat steps 1 to 8 until the infix expression is completely scanned.
    10. Pop and output from the stack until it is empty.
*/

/*
    Infix to Prefix conversion using stack.
    1. Reverse the expression and swap '(' with ')' and  vise-a-versa.
    2. Put the operands as they arrive
    3. If the operator arrives and the stack is empty, push to the stack.
    4. If the incoming operator has higher precedance than the one at the top of the stack, push it onto the stack.
    5. If the incoming operator has equal precedance with the top of the stack and incoming operator is '^' pop and print the top of the stack continue step 5 till the condition is true.
    6. If the incoming operator has equal precedance with the top of the stack, push it onto the stack.
    7. If the incoming operator has lower precedance than the top of the stack, pop and print the top of the stack, continue with step 7 untill condition is true.
    8. At the end of the expression, pop and print all the operators from the stack.
    9. If incoming symbol is '(' push it onto the stack.
    10. If incoming symbol is ')' pop the stack and print all the operators till '(' is found or the stack is empty, at the end pop '(' from the stack.
    11. If the top of stack is '(', push operator on the stack.
    12. At the end reverse the output string.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct stack
{
    int top;
    unsigned int size;
    char *arry;
} Stack;


typedef struct doubly_node{
    char data;
    struct doubly_node *next;
    struct doubly_node *previous;
} DoublyNode;

// Every number will have its seperate linked list.
typedef struct doubly_linked_list{
    struct doubly_node *head;
    struct doubly_node *tail;
} NumberOrOperator;

void initializeNewNumberList(NumberOrOperator *n){
    n->head=NULL;
    n->tail=NULL;
}

DoublyNode *create_new_node_doubly(char data){
    DoublyNode *newNode=(DoublyNode *)malloc(sizeof(DoublyNode));
    newNode->data=data;
    newNode->next=NULL;
    newNode->previous=NULL;

    return newNode;
}

void add_at_end_of_number_or_operator(NumberOrOperator *n,char data){
    DoublyNode *newNode=NULL;
    newNode=create_new_node_doubly(data);

    if(n->head==NULL && n->tail==NULL){
        n->head=newNode;
        n->tail=newNode;
    }else if(n->head==n->tail){
        n->tail=newNode;
        
        n->head->next=n->tail;
        n->tail->previous=n->head;
    }else{
        DoublyNode *temp=NULL;
        temp=n->tail;
        
        n->tail=newNode;
        
        temp->next=n->tail;
        n->tail->previous=temp;
    }
}

typedef struct singly_node{
    struct doubly_linked_list *element;
    struct singly_node *next;
} SinglyNode;

typedef struct singly_linked_list{
    struct singly_node *head;
} Expression;

void initializeExpression(Expression *e){
    e->head=NULL;
}

// Each node of this singly linked list will contain the pointer to a doubly linked list.
SinglyNode *create_new_node_singly(NumberOrOperator *toAdd){
    SinglyNode *newNode=(SinglyNode *)malloc(sizeof(SinglyNode));

    newNode->element=toAdd;
    newNode->next=NULL;

    return newNode;
}

void add_at_end_of_expression(Expression *expression,NumberOrOperator *toAdd){
    SinglyNode *newNode=NULL;
    newNode = create_new_node_singly(toAdd);

    if(expression->head==NULL){
        expression->head=newNode;
    }else{
        SinglyNode *temp=NULL;
        temp=expression->head;
        while(temp->next!=NULL){
            temp=temp->next;
        }
        temp->next=newNode;
    }
}


void initializeStack(int size, Stack *s)
{
    s->top = -1;
    s->size = size;
    s->arry = calloc(s->size, sizeof(char));
}

int isFull(Stack s)
{
    if (s.top == s.size - 1)
    {
        printf("\nakdjdkasd\n");
        return 1;
    }
    return 0;
}

int isEmpty(Stack s)
{
    if (s.top == -1)
    {
        return 1;
    }
    return 0;
}

void push(Stack *s, char toPush)
{
    if (isFull(*s))
    {
        printf("\nStack is full cannot push.\n");
        return;
    }
    s->arry[++s->top] = toPush;
}

char pop(Stack *s)
{
    if (isEmpty(*s))
    {
        printf("\nStack is already empty.\n");
        return '\0';
    }
    return s->arry[s->top--];
}

char peek(Stack s)
{
    if (isEmpty(s))
    {
        printf("\nNo element present in the stack.\n");
        return '\0';
    }
    return s.arry[s.top];
}

int isOperand(char toCheck)
{
    if ((toCheck >= 'a' && toCheck <= 'z') || (toCheck >= 'A' && toCheck <= 'Z') || (toCheck >= '0' && toCheck <= '9'))
    {
        return 1;
    }
    return 0;
}

// the 'break;' is unnecessary in the switch case, but it is added for readability.
int precedance(char toCheck)
{
    switch (toCheck)
    {
    case '+':
    case '-':
        return 1;
        break;
    case '*':
    case '/':
    case '%':
        return 2;
        break;
    case '^':
        return 3;
        break;
    default:
        return -1;
        break;
    }
}

void reverseTheExpression(char *expression)
{
    int length = 0;
    while (expression[length] != '\0')
    {
        length++;
    }
    char buffer[length + 1];
    for (int x = length - 1, y = 0; x >= 0; x--, y++)
    {
        if (expression[x] == ')')
        {
            buffer[y] = '(';
        }
        else if (expression[x] == '(')
        {
            buffer[y] = ')';
        }
        else
        {
            buffer[y] = expression[x];
        }
    }
    buffer[length] = '\0';
    int a = 0;
    while (buffer[a] != '\0')
    {
        expression[a] = buffer[a];
        a++;
    }
}

void infixToPostfix(char *infix, char *postfix)
{
    Stack s;
    initializeStack(30, &s);
    int infixIndex = 0, postfixIndex = 0;
    while (infix[infixIndex] != '\0')
    {
        char currentChar = infix[infixIndex];
        if (isOperand(currentChar))
        {
            postfix[postfixIndex++] = infix[infixIndex++];
        }
        else if (currentChar == '(')
        {
            push(&s, infix[infixIndex++]);
        }
        else if (currentChar == ')')
        {
            while (!isEmpty(s) && peek(s) != '(')
            {
                postfix[postfixIndex++] = pop(&s);
            }
            if (peek(s) == '(')
            {
                pop(&s);
                infixIndex++;
            }
        }
        else
        {
            while (!isEmpty(s) && precedance(peek(s)) >= precedance(currentChar))
            {
                postfix[postfixIndex++] = pop(&s);
            }
            push(&s, infix[infixIndex++]);
        }
    }
    while (!isEmpty(s))
    {
        postfix[postfixIndex++] = pop(&s);
    }
    postfix[postfixIndex] = '\0';
}

void infixToPrefix(char *infix, char *prefix)
{
    Stack s;
    initializeStack(40, &s);
    reverseTheExpression(infix);
    int infixIndex = 0, prefixIndex = 0;
    while (infix[infixIndex] != '\0')
    {
        char currentChar = infix[infixIndex];
        if (isOperand(currentChar))
        {
            prefix[prefixIndex++] = infix[infixIndex++];
        }
        else if (currentChar == '(')
        {
            push(&s, infix[infixIndex++]);
        }
        else if (currentChar == ')')
        {
            while (!isEmpty(s) && peek(s) != '(')
            {
                prefix[prefixIndex++] = pop(&s);
            }
            if (peek(s) == '(')
            {
                pop(&s);
                infixIndex++;
            }
        }
        else
        {
            if (isEmpty(s))
            {
                push(&s, infix[infixIndex++]);
            }
            else if (precedance(peek(s)) < precedance(currentChar))
            {
                push(&s, infix[infixIndex++]);
            }
            else if (precedance(peek(s)) == precedance(currentChar) && currentChar == '^')
            {
                while (!isEmpty(s) && precedance(peek(s)) == precedance(currentChar) && currentChar == '^')
                {
                    prefix[prefixIndex++] = pop(&s);
                }
                push(&s, infix[infixIndex++]);
            }
            else if (precedance(peek(s)) == precedance(currentChar))
            {
                push(&s, infix[infixIndex++]);
            }
            else
            {
                while (!isEmpty(s) && precedance(peek(s)) > precedance(currentChar))
                {
                    prefix[prefixIndex++] = pop(&s);
                }
                push(&s, infix[infixIndex++]);
            }
        }
    }
    while (!isEmpty(s))
    {
        prefix[prefixIndex++] = pop(&s);
    }
    prefix[prefixIndex] = '\0';
    reverseTheExpression(prefix);
}

int evaluatePostfix(char *postfix)
{
    Stack s;
    initializeStack(30, &s);

    int i;
    int n = strlen(postfix);

    for (i = 0; i < n; i++)
    {
        if (isdigit(postfix[i]))
        {
            push(&s, postfix[i] - '0');
        }
        else
        {
            int a = pop(&s);
            int b = pop(&s);
            switch (postfix[i])
            {
            case '+':
                push(&s, b + a);
                break;
            case '-':
                push(&s, b - a);
                break;
            case '*':
                push(&s, b * a);
                break;
            case '/':
                push(&s, b / a);
                break;
            }
        }
    }

    return pop(&s);
}


int evaluatePostfixVersion2(char *infix){
    Stack s;
    Expression postfix;
    

    initializeStack(30,&s);
    initializeExpression(&postfix);
    int infixIndex=0;
    while(infix[infixIndex]!='\0'){
        char currentChar=infix[infixIndex];
        if(isOperand(currentChar)){
            NumberOrOperator num_or_operator;
            initializeNewNumberList(&num_or_operator);
            while(isOperand(currentChar)){
                add_at_end_of_number_or_operator(&num_or_operator,currentChar);
                infixIndex++;
                currentChar=infix[infixIndex];
            }
            add_at_end_of_expression(&postfix,&num_or_operator);
        }else if(currentChar=='('){
            push(&s,infix[infixIndex++]);
        }else if(currentChar==')'){
            while(!isEmpty(s) && peek(s) != '('){
                char temp=pop(&s);
                NumberOrOperator num_or_operator;
                initializeNewNumberList(&num_or_operator);
                add_at_end_of_number_or_operator(&num_or_operator,temp);
                add_at_end_of_expression(&postfix,&num_or_operator);
            }if(peek(s)=='('){
                pop(&s);
                infixIndex++;
            }
        }else{
            while(!isEmpty(s) && precedance(peek(s))>=precedance(currentChar)){
                char temp=pop(&s);
                NumberOrOperator num_or_operator;
                initializeNewNumberList(&num_or_operator);
                add_at_end_of_number_or_operator(&num_or_operator,temp);
                add_at_end_of_expression(&postfix,&num_or_operator);
            }
            push(&s,infix[infixIndex++]);
        }
    }
    while(!isEmpty(s)){
        char temp=pop(&s);
        NumberOrOperator num_or_operator;
        initializeNewNumberList(&num_or_operator);
        add_at_end_of_number_or_operator(&num_or_operator,temp);
        add_at_end_of_expression(&postfix,&num_or_operator);
    }


    // Testing
    SinglyNode *temp=NULL;
    temp=postfix.head;
    while(temp!=NULL){
        printf("\nElement: ");
        DoublyNode *d_temp=NULL;
        d_temp=temp->element->head;
        while(d_temp){
            printf("%c ",d_temp->data);
            d_temp=d_temp->next;
        }
        temp=temp->next;
    }

}



int evaluatePrefix(char *prefix)
{
    Stack s;
    initializeStack(30, &s);
    int i;
    int n = strlen(prefix);
    for (int x = n-1; x >= 0; x--)
    {
        if (isdigit(prefix[x]))
        {
            push(&s, prefix[x] - '0');
        }
        else
        {
            int a = pop(&s);
            int b = pop(&s);
            switch (prefix[x])
            {
            case '+':
                push(&s, b + a);
                break;
            case '-':
                push(&s, b - a);
                break;
            case '*':
                push(&s, b * a);
                break;
            case '/':
                push(&s, b / a);
                break;
            }
        }
    }
    return pop(&s);
}

int main()
{
    char infix[100], postfix[100], prefix[100];
    int choice, toStop = 0;

    while (!toStop)
    {
        printf("\n1. To convert infix to postfix\n");
        printf("2. To convert infix to prefix\n");
        printf("3. Stop\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
            printf("\nEnter the infix expression: ");
            scanf("%s", infix);
            evaluatePostfixVersion2(infix);
            // infixToPostfix(infix, postfix);
            // int result = evaluatePostfix(postfix);
            // printf("Result: %d\n", result);
            // printf("The postfix expression is: %s\n", postfix);
            break;
        }
        case 2:
        {
            printf("\nEnter the inifx expression: ");
            scanf("%s", infix);
            infixToPrefix(infix, prefix);
            int result = evaluatePrefix(prefix);
            printf("Result: %d\n", result);
            printf("\nThe prefix expression is: %s\n", prefix);
            break;
        }
        case 3:
        {
            toStop = 1;
            break;
        }
        }
    }

    return 0;
}
