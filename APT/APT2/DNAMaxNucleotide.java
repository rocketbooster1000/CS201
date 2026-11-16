// package APT2;

public class DNAMaxNucleotide {
    public String max(String[] strand, String nuc) {
        int maxOccurence = 0;
        String maString = "";
        for (String s : strand) {
            int occurences = charInsanceOf(s, nuc.charAt(0));
            // System.out.println("occurences: " + occurences);
            if (occurences != 0 && maxOccurence <= occurences) {
                // System.out.println("String: " + s + " | char: " + nuc);
                if (maxOccurence < occurences || (maxOccurence == occurences && maString.length() < s.length())) {
                    maString = s;
                }
                maxOccurence = occurences;
            }
            System.out.println("max: " + maxOccurence);
            System.out.println();
        }
        return maString;

    }

    private int charInsanceOf(String s, char c) {
        int instance = 0;
        for (char cs : s.toCharArray()) {
            if (cs == c) instance++;
        }
        return instance;
    }

    public static void main(String[] args) {
        DNAMaxNucleotide dna = new DNAMaxNucleotide();
        String s = "cccccc";
        // System.out.println(dna.charInsanceOf(s, "c".charAt(0)));
        String character = "c";
        String[] arr = {"cgatcgatcgatcgat", "ggggttttggggtttt", "tttttttttttttttttggggggggggggggggggg", "ggggggggggggggggtttttttttttttttttttt", "aaaaaaaaaaaaaaaaaaaaaaaaaacccccccccc", "agtc", "agtc", "agtc", "agtc", "cccccccccccccccccccccttt", "ccccccccccccccccccccctt"};
        System.out.println(dna.max(arr, character));
    }
}
