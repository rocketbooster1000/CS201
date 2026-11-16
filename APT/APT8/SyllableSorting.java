import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SyllableSorting {
    public String[] sortWords(String[] words) {
        ArrayList<Word> wordList = new ArrayList<>();
        for (String w : words) {
            wordList.add(new Word(w));
        }
        Collections.sort(wordList, (a, b) -> {
            int result = compareSyllables(a.sortedSyl, b.sortedSyl);
            if (result != 0) return result;
            return compareSyllables(a.syl, b.syl);
        });
        String[] arr = new String[wordList.size()];
        int i = 0;
        for (Word word : wordList) {
            arr[i] = word.s;
            i++;
        }
        return arr;
    }

    private int compareSyllables(
        ArrayList<String> a, ArrayList<String> b) {
        int limit = Math.min(a.size(), b.size());

        for (int i = 0; i < limit; i++) {
            int result = a.get(i).compareTo(b.get(i));
            if (result != 0) return result;
        }

        return Integer.compare(a.size(), b.size());
    }

    public static class Word {
        public String s;

        public ArrayList<String> sortedSyl;
        public ArrayList<String> syl;

        public Word(String s) {
            this.s = s;
            sylable();
        }

        public String sortedString() {
            String s = "";
            for (String syllable : sortedSyl) {
                s += syllable;
            }
            return s;
        }

        private void sylable() {
            syl = new ArrayList<>();

            String s = "";

            char prev = 0;
            for (char c : this.s.toCharArray()) {
                if (!isVowel(c) && isVowel(prev)) {
                    syl.add(s);
                    s = "";
                }
                s += c;
                prev = c;
            }
            syl.add(s);

            sortedSyl = new ArrayList<>(syl);

            Collections.sort(sortedSyl);
        }

        @Override 
        public boolean equals(Object o) {
            if (o.getClass() != Word.class) return false;
            Word w = (Word) (o);
            return w.sortedString().equals(this.sortedString());
        }

        private boolean isVowel(char c) {
            if ("aeiou".indexOf(c) >= 0) return true;
            return false;
        }


    }
}
