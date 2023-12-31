public class App {
    public static void main(String[] args) throws Exception {
        Queue<Integer> queue = new Queue<>();
        for(int x=1;x<=10;x++){
            queue.enQueue(x);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
}
