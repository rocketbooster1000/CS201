// package APTQuiz1;

import java.util.ArrayList;

public class Between {
    public String[] sandwiched(String[] words, int low, int high) {
        // you write code here
        ArrayList<String> valid = new ArrayList<>();
        for (String s : words) {
            if (s.length() >= low && s.length() <= high)
                valid.add(s);
        }
        String[] arr = new String[valid.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = valid.get(i);
        }
        return arr;
    }
}
