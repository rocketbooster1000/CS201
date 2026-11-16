// package APT3;

import java.util.HashMap;

public class CounterAttack {
    public int[] analyze(String str, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();

        String[] strArr = str.split(" ");

        for (String s : strArr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int[] intArr = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            intArr[i] = map.getOrDefault(words[i], 0);
        }
                
        return intArr;
    }

//     public static void main(String[] args) {
//         String str = 
// "one two one two one two vorpal blade"        ;

//         String[] words = 
// {"snicker", "one", "blade", "runner"}
// ;
//         printArr(new CounterAttack().analyze(str, words));
//     }

//     private static void printArr(int[] arr) {
//         for (int i : arr) {
//             System.out.print(i + " ");
//         }
//     }
}
