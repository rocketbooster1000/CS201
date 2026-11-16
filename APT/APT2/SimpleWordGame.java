// package APT2;

import java.util.HashSet;

public class SimpleWordGame {
    public int points(String[] player, 
                    String[] dictionary) {
        // you write code here
        HashSet<String> playerSet = new HashSet<>();
        for (String s : player) playerSet.add(s);
        HashSet<String> dict = new HashSet<>();
        for (String s : dictionary) dict.add(s);
        
        int score = 0;
        for (String s : playerSet) {
            if (dict.contains(s)) score += (s.length() * s.length());
        }
        return score;
    }

    public static void main(String[] args) {
        String[] player = 
{ "lidi", "o", "lidi", "gnbewjzb", "kten",
  "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn"};

        String[] dict = { "nava", "wk", "kfkcdn", "lidi", "gptsvqx",
  "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx"};
        System.out.println(new SimpleWordGame().points(player, dict));
    }
}