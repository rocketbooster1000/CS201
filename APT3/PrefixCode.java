// package APT3;

import java.util.Collections;
import java.util.HashSet;

public class PrefixCode { 
    public String isOne(String[] words) {
        int minIndex = -1;
        int minLength = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String wordA = words[i], wordB = words[j];
                if (wordB.indexOf(wordA) == 0) {
                    minIndex = i;
                    set.add(i);
                    // minLength = wordB.length();
                } 
                if (wordA.indexOf(wordB) == 0) {
                    minIndex = j;
                    set.add(j);
                    // minLength = wordA.length();
                }
            }
        }
        if (minIndex == -1) return "Yes";
        return "No, " + Collections.min(set);
    }

//     public static void main(String[] args) {
//         String[] words = 
// {
// "T5Cia72L", "4k", "UAGi", "hbhxDT", "ZmRF", "MeWRoEztGK", "I2x", "C", "Ty", "I2", "UAG", "I", "MeWR"}

//         ;

//         System.out.println(new PrefixCode().isOne(words));
//     }
}
