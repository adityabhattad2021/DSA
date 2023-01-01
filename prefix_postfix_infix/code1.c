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
#include <stdio.h>
#include <stdlib.h>

typedef struct stack
{
    int top;
    unsigned int size;
    char *arry;
} Stack;

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
    if ((toCheck >= 'a' && toCheck <= 'z') || (toCheck >= 'A' && toCheck <= 'Z'))
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

void infixToPostfix(char *infix, char *postfix)
{
    Stack s;
    initializeStack(30,&s);
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
            while (!isEmpty(s) || peek(s) != '(')
            {
                postfix[postfixIndex++] = pop(&s);
            }
            pop(&s);
            infixIndex++;
        }
        else
        {
            while (!isEmpty(s)  &&  precedance(peek(s)) >= precedance(currentChar))
            {
                postfix[postfixIndex++] = pop(&s);
            }
            push(&s, infix[infixIndex++]);
        }
    }
    while(!isEmpty(s)){
        postfix[postfixIndex++]=pop(&s);
    }
    postfix[postfixIndex]='\0';
}

int main()
{
    char infix[100], postfix[100];
    printf("Enter the infix expression: ");
    scanf("%s", infix);
    infixToPostfix(infix, postfix);
    printf("The postfix expression is: %s\n", postfix);
    return 0;
}
