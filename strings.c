#include<stdio.h>
#include<stdlib.h>

/*
    1. Character Set/ASCII (American Standard Code for Information Interchange) Codes
*/

/*
    There are total 128 ASCII Codes (0-127)
    To Remember:
    A - 65, B - 66, ... ,Z - 90
    a - 97, b - 98, ... ,z - 122
    0 - 48, 1 - 49, ... ,9 - 57
    Enter - 10
    Space - 13
    escape - 27
*/
void findLengthOfString(){
    char string[100];
    printf("Enter a string: ");
    fgets(string,100,stdin);
    fflush(stdin);
    int length=0;
    int x = 0;

    while(string[x]!='\0'){
        x++;
        if(string[x]!=' '){
            length++;
        }
    }
    // As it will increment one more time, so that the condition returns false.
    length--;
    printf("The string you entered was %s and it is %d characters long.",string,length); 
}

void changeCaseOfString(){
    char string[100],toConvert;
    printf("Enter a string: ");
    fgets(string,100,stdin);
    fflush(stdin);
    for(int x = 0;string[x]!='\0';x++){
        if(string[x]>='A' && string[x]<='Z'){
            string[x]+=' ';
        }
        else if(string[x]>='a' && string[x]<='z'){
            string[x]-=' ';
        }
    } 
    printf("The string is %s\n",string);
}


int main(){
    changeCaseOfString();
    return 0;
}
