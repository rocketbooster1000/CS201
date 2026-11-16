// package APT3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BigWord {
    public String most(String[] sentences) {
        HashMap<String, Integer> set = new HashMap<>();

        for (String s : sentences) {
            for (String word : s.split(" ")) {
                // System.out.println(word);
                set.put(word.toLowerCase(), set.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }
        // System.out.println(set);
        return Collections.max(set.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

//     public static void main(String[] args) {
//         String[] sentences = 
// {
// "THIS is the way", "THIS is the way", "this is this", "this is a story", "THIS IS an exampe", "THIS is a fish", "THIS IS THE ONLY WAY TO FLY", "this isn't right" 

// };
//         System.out.println(new BigWord().most(sentences));
//     }
}
