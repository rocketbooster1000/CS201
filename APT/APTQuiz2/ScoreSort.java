// package APTQuiz2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ScoreSort {
    public String[] rank(String[] names, ListNode scores) {
        TreeMap<String, Integer> map = new TreeMap<>();
        int i = 0;
        ListNode pointer = scores;
        while (pointer != null) {
            map.put(names[i], map.getOrDefault(names[i], 0) + pointer.info);
            i++;
            pointer = pointer.next;
        }

        List<Entry<String, Integer>> list = new ArrayList<>();

        for (Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry);
        }

        list.sort((a, b) -> b.getValue() - a.getValue());

        String[] arr = new String[list.size()];

        for (int index = 0; index < arr.length; index++) {
            arr[index] = list.get(index).getKey();
        }

        return arr;
    }
}
