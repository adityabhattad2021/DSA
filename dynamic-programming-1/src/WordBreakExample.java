import java.util.HashMap;
import java.util.HashMap;

// Time complexity O(n*lengthOfDict)
// Space complexity O(n) because of the stack size is equal to length of the decision tree
public class WordBreakExample {
    
    public static void main(String[] args) {
        String word = "leetcode";
        String[] dictionary = {"leet","leetc","o","d","od","lee","tod","code","le","etco","ode"};
        System.out.println(wordBreak(word, dictionary));    
    }

    public static boolean wordBreak(String s, String[] wordDict){
        return wordBreak(s, wordDict, new HashMap<>());
    }


    public static boolean wordBreak(String s, String[] wordDict, HashMap<String,Boolean> memo){
        
        if(s.length()==0) return true;
        if(memo.containsKey(s)) return memo.get(s);
        
        boolean check = false;
        for(String w : wordDict){
            if(s.startsWith(w)){
                check = check || wordBreak(s.substring(w.length()), wordDict, memo);
            }
        }
        memo.put(s, check);
        return memo.get(s);

    }

}