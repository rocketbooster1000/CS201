// package APT3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StringCuts {
    public String[] filter(String[] list, int minLength) {
        ArrayList<String> a = new ArrayList<>();
        HashSet<String> s = new HashSet<>();
        for (String str : list) {
            if (str.length() >= minLength && !s.contains(str)) {
                a.add(str);
                s.add(str);
            }
        }
        String[] arr = new String[a.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = a.get(i);
        }
        return arr;
    }

    // public static void main(String[] args) {
    //     String[] list = {
    //         "huge", "enormous", "big", "gigantic"
    //     }

    //     ;
    //     int minLength = 5

    //     ;

    //     printArr(new StringCuts().filter(list, minLength));

    //     // System.out.println(new StringCuts().filter(list, minLength));
    // }

    // public static void printArr(String[] arr) {
    //     for (String s : arr) {
    //         System.out.println(s);
    //     }
    // }
}