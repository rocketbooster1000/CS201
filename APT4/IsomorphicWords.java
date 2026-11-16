// package APT4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IsomorphicWords {
    public int countPairs(String[] words) {
        ArrayList<HashMap<Integer, List<Integer>>> list = new ArrayList<>();
        
        for (String word : words) {
            HashMap<Integer, List<Integer>> map = new HashMap<>();

            for (int i = 0; i < word.length(); i++) {
                ArrayList<Integer> reoccurences = new ArrayList<>();
                for (int j = i + 1; j < word.length(); j++) {
                    if (word.charAt(i) == word.charAt(j)) {
                        reoccurences.add(j);
                    }
                }
                map.put(i, reoccurences);
            }
            list.add(map);
        }

        int numValid = 0;
        for (int i = 0; i < list.size(); i++) {
            HashMap<Integer, List<Integer>> map = list.get(i);

            for (int j = i + 1; j < list.size(); j++) {
                HashMap<Integer, List<Integer>> jMap = list.get(j);

                if (map.equals(jMap)) numValid++;
            }
        }  

        return numValid;
    }

    public static void main(String[] args) {
        String[] words = 
{ "cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb",
  "bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad",
  "dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca" }
        ;
        System.out.println(new IsomorphicWords().countPairs(words));
    }
}
