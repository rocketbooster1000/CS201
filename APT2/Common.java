// package APT2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Common {
    public int count (String a, String b) {
        // TODO: fill in code here
        char[] ca = a.toCharArray();
        ArrayList<Character> cb = new ArrayList<>();
        for (char c : b.toCharArray()) {
            cb.add(c);
        }

        Map<Character, Integer> mapA = mapOfString(a);
        Map<Character, Integer> mapB = mapOfString(b);

        int common = 0;
        
        for (Entry<Character, Integer> entry : mapA.entrySet()) {
            common += Math.min(
                entry.getValue(),
                mapB.getOrDefault(entry.getKey(), 0)
            );
        }
        

        return common;
    }

    private Map<Character, Integer> mapOfString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        String a = 
        "toots" 

        ;
        String b = 
"stout"

        ;

        System.out.println(new Common().count(a, b));
    }
}
