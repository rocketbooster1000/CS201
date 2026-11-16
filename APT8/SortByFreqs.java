// package APT8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class SortByFreqs {
    public String[] sort(String[] data) {
        HashMap<String, Struct> freqMap = new HashMap<>();

        for (String dat : data) {
            if (!freqMap.containsKey(dat)) {
                freqMap.put(dat, new Struct(dat, 0));
            }
            freqMap.get(dat).freq += 1;
        }

        List<Struct> list = new ArrayList<>();
        for (Struct s : freqMap.values()) {
            list.add(s);
        }

        Collections.sort(list, Comparator.comparing(Struct::getFreq).reversed().thenComparing(
            (a) -> a.s
        ));

        String[] arr = new String[list.size()];

        int i = 0;
        for (Struct struct : list) {
            arr[i] = struct.s;
            i++;
        }

        return arr;
    }

    public static class Struct {
        public String s;
        public int freq;
        public Struct(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }

        public int getFreq() {
            return freq;
        }
    }
}
