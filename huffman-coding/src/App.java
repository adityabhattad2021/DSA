import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class App {
    public static void main(String[] args) throws Exception {
        String strToBeEncoded = "aaaaaaaabbbbbbbccccdd";
        Huffman huffman = new Huffman();
        Encoded encoded = huffman.encode(strToBeEncoded,true);
        System.out.println(encoded.encodedStr);
        System.out.println(huffman.decode(encoded.encodedStr,encoded.table));
    }
}
