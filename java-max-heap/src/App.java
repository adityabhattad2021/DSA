public class App {
    public static void main(String[] args) {
        // Creating a Maxheap instance
        MaxHeap maxHeap = new MaxHeap();

        // Adding elements to the heap
        maxHeap.push(10);
        maxHeap.push(4);
        maxHeap.push(15);
        maxHeap.push(20);
        maxHeap.push(3);
        maxHeap.push(2);
        maxHeap.push(8);
        maxHeap.push(0);
        maxHeap.push(5);

        System.out.println(maxHeap.peek());

        // Printing out elements as they are removed from the heap
        // The elements should come out in ascending order
        System.out.println("Removing elements from the heap:");
        while (true) {
            try {
                int min = maxHeap.pop();
                System.out.println(min);
            } catch (Exception e) {
                // Break out of the loop if the heap is empty
                System.out.println("Heap is empty.");
                break;
            }
        }
    }
}
