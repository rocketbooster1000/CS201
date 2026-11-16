// package APT2;

public class SandwichBar {
    public int whichOrder(String[] available,
                            String[] orders){
        // fill in code here
        for (int i = 0; i < orders.length; i++) {
            String[] subOrder = orders[i].split(" ");
            boolean skip = false;
            for (String s : subOrder) {
                boolean ingredientFound = false;
                for (String ingredient : available) {
                    if (ingredient.equals(s)) {
                        ingredientFound = true;
                        break;
                    }
                }
                if (!ingredientFound) {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;
            else return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] ingredients = 
{ "foo", "bar", "baz", "gazonk", "quux", "bat", "xyzzy",
  "shme", "hukarz", "grault", "waldo", "bleh" };
        String[] order = 
{ "kalatehas", "spam eggs", "needle haystack", "bleh blarg", "plugh", 
  "the best sandwich in the universe" }        ;
        System.out.println(new SandwichBar().whichOrder(ingredients, order));
    }
}
