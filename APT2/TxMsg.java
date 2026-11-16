// package APT2;

import java.util.ArrayList;

public class TxMsg {
    public String getMessage(String original) {
        if (allVowel(original)) return original;

        String f = "";
        String[] words = original.split(" ");
        ArrayList<String> stitchedWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            if (allVowel(word)) {
                stitchedWords.add(word);
                continue;
            } else {
                // System.out.println(word);
                String s = "";
                for (int j = 0; j < word.length(); j++) {
                    String character = word.substring(j, j + 1);
                    if (isVowel(character)) continue;
                    // System.out.println(j);
                    if (j == 0 || !isCons(word.substring(j - 1, j))) {
                        s += character;
                    }
                }
                stitchedWords.add(s);
            }
        }
        for (String s : stitchedWords) {
            if (s.length() == 0) continue;
            f += (s + " ");
        }
        f = f.substring(0, f.length() - 1);
        return f;
    }

    private boolean allVowel(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isVowel(s.substring(i, i+1))) return false;
        }
        return true;
    }

    private boolean isVowel(String c) {
        return "aeiou".indexOf(c) >= 0;
    }

    private boolean isSpace(String s) {
        return s.equals(" ");
    }

    private boolean isCons(String s) {
        return !isVowel(s) && !isSpace(s);
    }

    public static void main(String[] args) {
        String original =
"aeiou bcdfghjklmnpqrstvwxyz"
;

        System.out.println(new TxMsg().getMessage(original));
        // System.out.println(new TxMsg().isCons(" "));
        String abcs =         "abcdefghijklmnopqrstuvwxyz";

        // for (int i = 0; i < abcs.length(); i++) {
        //     String character = abcs.substring(i, i + 1);
        //     System.out.println("char: " + character + " | is cons?: " + new TxMsg().isCons(character));
        //     // System.out.println("is vowel: " + new TxMsg().isCons(abcs));
        // }
    }
}
