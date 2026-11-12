import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author YOUR NAME GOES HERE
 * @version 0.9 , needs to be updated
 */


public class SimpleMarkovModel extends BaseMarkovModel  {

    public SimpleMarkovModel(int size){
        super(size);
    }

    public SimpleMarkovModel(){
        this(3);
    }

    @Override 
    protected List<String> getFollows(List<String> context) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < myWordSequence.size() - context.size(); i++) {
            boolean fit = true;
            for (int j = 0; j < context.size(); j++) {
                if (!myWordSequence.get(i + j).equals(context.get(j))) {
                    fit = false;
                    break;
                }
            }
            if (fit) list.add(myWordSequence.get(i + context.size()));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        SimpleMarkovModel mm = new SimpleMarkovModel(2);
        String dirName = "data/shakespeare";

        int size = 500;
        mm.trainDirectory(dirName);
        System.out.printf("seq size: %d\n",mm.getSequence().size());
        String text = mm.generate(size);

        System.out.printf("size of generated text = %d\n",text.length());
        System.out.printf("# different contexts possible = %d\n",mm.differentContexts());
    }
}
