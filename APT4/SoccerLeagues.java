// package APT4;

public class SoccerLeagues {
    public int[] points(String[] matches) {
        int[] scores = new int[matches.length];

        for (int i = 0; i < matches.length; i++) {
            String fieldRecord = matches[i];
            for (int j = 0; j < fieldRecord.length(); j++) {
                // System.out.println(fieldRecord.charAt(j));
                switch (fieldRecord.charAt(j)) {
                    case 'W':
                        scores[i] += 3;
                        break;
                    case 'D':
                        scores[i] += 1;
                        scores[j] += 1;
                        break;
                    case 'L':
                        scores[j] += 3;
                        break;
                    default:
                        break;
                }
            }
        }
        return scores;
    }

    public static void main(String[] args) {
        String[] matches = 

{"-LWWLWDLDWWWWWWDDWDW",
 "D-WWLDDWDWDLWDDWLWDD",
 "LL-DLDWDLDLDWWWLWDDW",
 "LDD-LLLDLWLWWWWDWDWL",
 "LWWW-DWDLWDWDWWWDWDW",
 "DLLWD-WWLLDDDLWWDWWW",
 "WWLWDL-LLDWWWWWDWWLW",
 "LLLLLDW-LDLWDDLLLDWL",
 "DWWWWDDD-DWWWWDWWWDW",
 "WWWWLLLWL-LWWWWWLWWW",
 "DWWWWWWWLW-WDWWWWWWW",
 "DDDLLLDWWWL-DDWDWLDD",
 "LWLWLDLLLDLW-DDDWWDD",
 "LLWWLWDDLWLWL-WWWDLL",
 "WWWWLLDDDWLWDD-WWWLW",
 "DLDLLLWWLLLWWLW-DWLL",
 "DLWWWLDLWWDWWDWL-WWD",
 "LLDDLLWLLWLWLDLWW-WW",
 "LLWLLLWWLWLWWDWWLD-W",
 "LLWDLWDWDWLLWWDDWWL-"}

        ;

        int[] arr = new SoccerLeagues().points(matches);

        for (int i : arr) {
            System.out.print(i + " | ");
        }
        
    }
}
