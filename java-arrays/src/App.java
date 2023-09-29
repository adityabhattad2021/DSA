import java.lang.reflect.Array;

public class App {

    public <T> void printArray(T[] arry) {
        for (int x = 0; x < arry.length; x++) {
            System.out.print(arry[x] + " ");
        }
        System.out.println();
    }

    public <T> void reverseArray(T[] arry, Class<T> clazz) {

        @SuppressWarnings("unchecked")
        T[] buffer = (T[]) Array.newInstance(clazz, arry.length);
        for (int x = 0; x < arry.length; x++) {
            buffer[x] = arry[arry.length - 1 - x];
        }
        for (int x = 0; x < arry.length; x++) {
            arry[x] = buffer[x];
        }

    }

    public Integer[] removeOddIntegers(Integer[] arry) {

        int evenCount = 0;
        for (int x = 0; x < arry.length; x++) {
            if (arry[x] % 2 == 0) {
                evenCount++;
            }
        }

        Integer[] evenArray = new Integer[evenCount];

        int counter = 0;
        for (int x = 0; x < arry.length; x++) {
            if (arry[x] % 2 == 0) {
                evenArray[counter] = arry[x];
                counter++;
            }
        }

        return evenArray;

    }

    public Integer[] findMaxAndMin(Integer[] arry) {

        Integer[] maxAndMin = new Integer[2];
        Integer max = Integer.MIN_VALUE;
        Integer min = Integer.MAX_VALUE;

        for (int x = 0; x < arry.length; x++) {
            if (arry[x] > max) {
                max = arry[x];
            }
            if (arry[x] < min) {
                min = arry[x];
            }
        }

        maxAndMin[0] = max;
        maxAndMin[1] = min;

        return maxAndMin;

    }

    // Given an array of integers, return second maximum value, consider the second
    // max value exists.
    public Integer secondMaxValue(Integer[] arry) {
        Integer maxValue = findMaxAndMin(arry)[0];
        Integer secondMaxValue = Integer.MIN_VALUE;
        for (int x = 0; x < arry.length; x++) {
            if (arry[x] > secondMaxValue && arry[x] != maxValue) {
                secondMaxValue = arry[x];
            }
        }
        return secondMaxValue;
    }

    // Given an array of integers, write a function to move all 0's to the end of it
    // while maintaining the relative order of the non zero elements
    public void moveAllZerosToEnd(Integer[] arry) {

        // This approach works, but changes the relative order of the non zero elements.
        // int lastIndex=arry.length-1;
        // for(int x=0;x<lastIndex;x++){
        // if(arry[x]==0){
        // // Swap with the element at the last index.
        // Integer temp=arry[x];
        // arry[x]=arry[lastIndex];
        // arry[lastIndex]=temp;
        // lastIndex--;
        // }
        // }

        // Using bubble sort as it is stable algo, but time complexity is O(n^2) 😓.
        // for(int x=0;x<arry.length;x++){
        // for(int y=0;y<arry.length-x-1;y++){
        // if(arry[y]==0){
        // Integer temp=arry[y];
        // arry[y]=arry[y+1];
        // arry[y+1]=temp;
        // }
        // }
        // }

        // A little better approach, time complexity O(n)🥳
        int y = 0;
        for (int x = 0; x < arry.length; x++) {
            if (arry[x] != 0 && arry[y] == 0) {
                Integer temp = arry[x];
                arry[x] = arry[y];
                arry[y] = temp;
            }
            if (arry[y] != 0) {
                y++;
            }
        }
    }

    public Integer[] resizeArry(Integer[] arry, int newCapacity) {
        if (arry.length < newCapacity) {
            Integer[] newArry = new Integer[newCapacity];
            for (int x = 0; x < arry.length; x++) {
                newArry[x] = arry[x];
            }
            return newArry;
        } else {
            return arry;
        }
    }

    // Given an array of n-1 distinct numbers in the range of 1 to n. Find the
    // missing number in it.
    public Integer findMissingInteger(Integer[] arry) {
        Integer rangeOfNumbers = arry.length;
        boolean[] isPresent = new boolean[rangeOfNumbers + 1];
        for (int x = 0; x < isPresent.length; x++) {
            isPresent[x] = false;
        }
        for (int x = 0; x < arry.length; x++) {
            isPresent[arry[x] - 1] = true;
        }
        Integer missingValue = 0;
        for (int x = 0; x < isPresent.length; x++) {
            if(!isPresent[x]){
                missingValue = x + 1; 
            }
        }
        return missingValue;
    }


    public boolean checkIftheStringIsPalindrome(String str){
        char[] buffer=str.toCharArray();
        char[] reverseStr= new char[buffer.length];
        for(int x=0;x<buffer.length;x++){
            reverseStr[x]=buffer[buffer.length-1-x];
        }
        boolean isPalindrome=true;
        for(int x=0;x<buffer.length;x++){
            if(buffer[x]!=reverseStr[x]){
                isPalindrome=false;
                break;
            }
        }
        return isPalindrome;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        Integer[] newArray;

        newArray = new Integer[12];
        // or
        Integer[] anotherArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        for (int x = 0; x < 12; x++) {
            newArray[x] = x;
        }

        newArray[4] = 32;
        newArray[8] = 62;
        newArray[1] = 62;

        // app.printArray(newArray);
        // app.reverseArray(anotherArray,Integer.class);
        // app.reverseArray(newArray,Integer.class);
        // app.printArray(newArray);
        // app.printArray(anotherArray);
        // Integer[] evenArry=app.removeOddIntegers(anotherArray);
        // app.printArray(evenArry);

        // Integer[] maxAndMin=app.findMaxAndMin(newArray);
        // app.printArray(maxAndMin);
        // Integer secondMaxValue = app.secondMaxValue(newArray);

        // System.out.println(secondMaxValue);

        // Integer[] arryWithZeros = { 0, 1, 3, 41, 0, 0, 23, 54, 0, 3, 0, 5, 21, 6, 12,
        // 0 };
        // app.moveAllZerosToEnd(arryWithZeros);
        // app.printArray(arryWithZeros);
        // newArray=app.resizeArry(newArray, 21);
        // newArray[20] = 52;
        // app.printArray(newArray);

        // Integer[] theArry = { 2, 4, 1, 5, 6, 3, 7 ,8};
        // System.out.println(app.findMissingInteger(theArry));

        boolean isPalindrome = app.checkIftheStringIsPalindrome("rouor");
        if(isPalindrome){
            System.out.println("String is palindrome.");
        }else{
            System.out.println("String ain't palindrome.");
        }

    }
}
