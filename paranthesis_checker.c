/*
    Algorithm:
        Initialize an empty stack.
    Iterate through the string containing the parentheses, one character at a time.
    If the current character is an opening parenthesis (i.e., '(' or '[' or '{'), push it onto the stack.
    If the current character is a closing parenthesis (i.e., ')' or ']' or '}'), pop the top element from the stack. If the popped element is not the matching opening parenthesis for the current closing parenthesis, return false (mismatch).
    After iterating through the entire string, if the stack is empty, return true (all parentheses are matched). If the stack is not empty, return false (there are unmatched opening parentheses).
*/


// Code
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

#define STACK_SIZE 100

char stack[STACK_SIZE];
int top = -1;

void push(char c) {
  stack[++top] = c;
}

char pop() {
  return stack[top--];
}

bool is_empty() {
  return top == -1;
}

bool is_matching_pair(char opening, char closing) {
  if (opening == '(' && closing == ')') {
    return true;
  } else if (opening == '[' && closing == ']') {
    return true;
  } else if (opening == '{' && closing == '}') {
    return true;
  }
  return false;
}

bool are_parentheses_balanced(char* expr) {
  for (int i = 0; i < strlen(expr); i++) {
    if (expr[i] == '(' || expr[i] == '[' || expr[i] == '{') {
      push(expr[i]);
    } else if (expr[i] == ')' || expr[i] == ']' || expr[i] == '}') {
      if (is_empty() || !is_matching_pair(pop(), expr[i])) {
        return false;
      }
    }
  }
  return is_empty();
}

int main() {
  char expr[STACK_SIZE];
  printf("Enter an expression containing parentheses: ");
  scanf("%s", expr);
  if (are_parentheses_balanced(expr)) {
    printf("Parentheses are balanced\n");
  } else {
    printf("Parentheses are not balanced\n");
  }
  return 0;
}
