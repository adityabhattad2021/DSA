import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap {

    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(-1);
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private void swimUp() {
        int temp = heap.size() - 1;
        while (temp/2 >= 1 && heap.get(temp) < heap.get(temp / 2)) {
            swap(temp, temp / 2);
            temp /= 2;
        }
    }

    private void sinkDown() {
        int parentIndex = 1; 
        while (true) {
            int leftChildIndex = 2 * parentIndex ; 
            int rightChildIndex = 2 * parentIndex + 1;
            int smallest = parentIndex;
            if (leftChildIndex < heap.size() && heap.get(leftChildIndex) < heap.get(smallest)) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(smallest)) {
                smallest = rightChildIndex;
            }
            if (smallest != parentIndex) {
                swap(parentIndex, smallest);
                parentIndex = smallest;
            } else {
                break;
            }
        }
    }
    

    public void push(int ele){
        heap.add(ele);
        swimUp();
    }

    public int pop(){
        if(heap.size()<=1){
            throw new NoSuchElementException("The heap is empty");
        }
        int ele = heap.get(1);
        swap(1,heap.size()-1);
        heap.remove(heap.size()-1);
        sinkDown();
        return ele;
    }

    public int peek(){
        if(heap.size()<=1){
            throw new NoSuchElementException("The heap is empty");
        }
        return heap.get(1);
    }

}