// package APT6;

import java.util.Arrays;
import java.util.HashSet;

public class AlphaLength {
    public ListNode create (String[] words) {
        // Arrays.sort(words);
        HashSet<String> set = new HashSet<>();
        for (String s : words) {
            set.add(s);
        }

        String[] setWords = new String[set.size()];

        int i = 0;
        for (String s : set) {
            setWords[i] = s;
            i++;
        }

        Arrays.sort(setWords);

        ListNode pointer = new ListNode(setWords[0].length(), null);
        ListNode first = pointer;

        for (int j = 1; j < setWords.length; j++) {
            pointer.next = new ListNode(setWords[j].length());
            pointer = pointer.next;
        }

        return first;
        // return null;
    }

}
