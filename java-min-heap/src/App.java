public class App {
    public static void main(String[] args) {
        // Creating a MinHeap instance
        MinHeap minHeap = new MinHeap();

        // Adding elements to the heap
        minHeap.push(10);
        minHeap.push(4);
        minHeap.push(15);
        minHeap.push(20);
        minHeap.push(3);
        minHeap.push(2);
        minHeap.push(8);
        minHeap.push(0);
        minHeap.push(5);

        // Printing out elements as they are removed from the heap
        // The elements should come out in ascending order
        System.out.println("Removing elements from the heap:");
        while (true) {
            try {
                int min = minHeap.pop();
                System.out.println(min);
            } catch (Exception e) {
                // Break out of the loop if the heap is empty
                System.out.println("Heap is empty.");
                break;
            }
        }
    }
}
