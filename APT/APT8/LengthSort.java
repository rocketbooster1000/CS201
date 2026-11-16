// package APT8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LengthSort {
    public String[] rearrange(String[] values){
        List<String> l = Arrays.asList(values);
        Collections.sort(l, Comparator.comparing(String::length)
                            .thenComparing(Comparator.naturalOrder()));
        return l.toArray(new String[]{});
    }
}
