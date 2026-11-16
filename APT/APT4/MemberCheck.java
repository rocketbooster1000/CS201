// package APT4;

import java.util.Arrays;
import java.util.HashSet;

public class MemberCheck {
    public String[] whosDishonest(String[] club1, 
                                String[] club2, 
                                String[] club3) {
        // TODO: fill in code here
        HashSet<String> map1 = new HashSet<>(), map2 = new HashSet<>(), map3 = new HashSet<>();

        for (String s : club1) map1.add(s);
        for (String s : club2) map2.add(s);
        for (String s : club3) map3.add(s);

        HashSet<String> map12 = new HashSet<>(), map23 = new HashSet<>(), map13 = new HashSet<>(), map123 = new HashSet<>();

        map12.addAll(map1);
        map12.retainAll(map2);
        map13.addAll(map1);
        map13.retainAll(map3);
        map23.addAll(map2);
        map23.retainAll(map3);

        map123.addAll(map12);
        map123.addAll(map23);
        map123.addAll(map13);

        // return (String[]) (map123.toArray());
        String[] arr = new String[map123.size()];
                                    
        int i = 0;
        for (String s : map123) {
            arr[i] = s;
            i++;
        }

        // System.out.println(map12);
        Arrays.sort(arr);
        return arr;
    }

//     public static void main(String[] args) {
//         String[] club1 = 
// {"PEG","ROBERT","PAM","JOHN","DAVID","JOHN","ROBERT",
// "GEORGE","HUGH","WILL","JAMES","JAMES","BOBBY","BOBBY","SANDRA"}
//         ;

//         String[] club2 = 
// {"SANDRA","BOB","PAM","JAMES","WILL","DAVID","BOBBY","GEORGE",
// "WILL","LIZ","BOBBY","ROBERT","WILL","BOB","BOBBY","ELIZABETH","HUGH"}
//         ;
//         String[] club3 = 
// {"WILL","PEG","ELIZABETH","DAVID","HUGH","BOBBY","JOHN","SANDRA","ELIZABETH",
// "ELIZABETH","SANDRA","GEORGE","PAM","ELIZABETH","BOBBY","DAVID","PAM"}
//         ;
//         printArray(new MemberCheck().whosDishonest(club1, club2, club3));
//     }

//     private static void printArray(String[] arr) {
//         System.out.print("{");
//         for (String s : arr) {
//             System.out.print(" \"" + s + "\" ");
//         }
//         System.out.print("}");
//     }
}