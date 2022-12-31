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