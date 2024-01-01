import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap{


    private ArrayList<Integer> heap;


    public MaxHeap(){
        this.heap = new ArrayList<>();
        this.heap.add(-1);
    }

    private void swap(int index1,int index2){
        int temp = heap.get(index1);
        heap.set(index1,heap.get(index2));
        heap.set(index2,temp);
    }

    private void swimUp(){
        int start = heap.size()-1;
        while(start/2 >=1 && heap.get(start)>heap.get(start/2)){
            swap(start,start/2);
            start/=2;
        }
    }

    private void sinkDown(){
        int start = 1;
        while(true){
            int leftChild = 2*start;
            int rightChild = 2*start+1;
            int largest = start;
            if(leftChild<heap.size() && heap.get(leftChild)>heap.get(largest)){
                largest=leftChild;
            }
            if(rightChild<heap.size() && heap.get(rightChild)>heap.get(largest)){
                largest = rightChild;
            }

            if(largest!=start){
                swap(largest,start);
                start=largest;
            }else{
                break;
            }
        }
    }

    public void push(int ele){
        heap.add(ele);
        swimUp();
    }

    public int pop(){
        if(this.isEmpty()){
            throw new NoSuchElementException("Heap is already empty");
        }
        int ele = heap.get(1);
        swap(1,heap.size()-1);
        heap.remove(heap.size()-1);
        sinkDown();
        return ele;
    }

    public boolean isEmpty(){
        return heap.size()==1;
    }

    public int peek(){
        return heap.get(1);
    }

}