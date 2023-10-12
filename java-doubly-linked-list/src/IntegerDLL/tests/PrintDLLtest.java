package IntegerDLL.tests;

import IntegerDLL.PrintDLL;

public class PrintDLLtest {

    private static PrintDLL dll = null;
    
    public static void main(String[] args) {
        testPrintBackward();
    }

    private static void initializeNew(){
        dll = new PrintDLL();
    }

    private static void addTestElements(){
        for(int i=0;i<10;i++){
            PrintDLL.ListNode data =  dll.new ListNode(i);
            dll.addAtEnd(data);
        }
    }

    public static void testPrintForward(){
        initializeNew();
        addTestElements();
        dll.printForward();
    }

    public static void testPrintBackward(){
         initializeNew();
        addTestElements();
        dll.printBackward();
    }

}
