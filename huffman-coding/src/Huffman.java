import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    Character c;
    Integer count;
    Node left;
    Node right;

    public Node(Character c, Integer count) {
        this.c = c;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return "Node [c=" + c + ", count=" + count + "]";
    }

}

class Encoded {
    String encodedStr;
    Map<Character, String> table;

    public Encoded(String encodedStr, Map<Character, String> table) {
        this.encodedStr = encodedStr;
        this.table = table;
    }

}

public class Huffman {

    /*
     * Encode the string and return the encoded string and the table
     */
    public Encoded encode(String str,boolean debug) {
        // Step 1: Get number of times the character occour in the string
        Map<Character, Integer> table = countCharOccurance(str);

        // Step 2: Add to priority queue
        PriorityQueue<Node> pq = addToPriorityQueue(table);

        // Step 3: Convert it into the tree
        Node root = getTree(pq);

        // Step 4: Get final conversion table (traverse the tree, in the path left is 0 and right is 1) add successively and for each character store the current code.
        Map<Character,String> finalTable = getFinalConversionTable(root);

        // Step 5: Get final string
        String fStr = getStringFromTable(finalTable,str,debug);
        return new Encoded(fStr,finalTable);
    }

    private Map<Character, Integer> countCharOccurance(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (Character c : str.toCharArray()) {
            charCount.put(c, charCount.get(c) == null ? 1 : charCount.get(c) + 1);

        }
        return charCount;
    }

    private PriorityQueue<Node> addToPriorityQueue(Map<Character, Integer> table) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : table.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        return pq;
    }

    private Node getTree(PriorityQueue<Node> pq) {
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(null, left.count + right.count);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        return pq.poll();
    }

    private Map<Character,String> getFinalConversionTable(Node root){
        Map<Character,String> fTable = new HashMap<>();
        traverseTree(root,fTable,"");
        return fTable;
    }

    private void traverseTree(Node root, Map<Character, String> table, String code) {
        if (root == null) {
            return;
        }

        if (root.c != null) {
            table.put(root.c, code);
        }

        traverseTree(root.left, table, code + "0");
        traverseTree(root.right, table, code + "1");
    }

    private String getStringFromTable(Map<Character,String> fTable,String str,boolean debug){
        if(debug){
            for (Map.Entry<Character, String> entry : fTable.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character c:str.toCharArray()){
            sb.append(fTable.get(c));
        }
        return sb.toString();
    }

    /*
     * Decode the string using the table
     */
    public String decode(String str,Map<Character,String> fTable){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();){
            for(Map.Entry<Character,String> entry:fTable.entrySet()){
                if(str.startsWith(entry.getValue(),i)){
                    sb.append(entry.getKey());
                    i+=entry.getValue().length();
                    break;
                }
            }
        }
        return sb.toString();
    }
}
