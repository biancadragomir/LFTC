import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {
    private List<Pair<Integer, Integer>> pairs; // code in the codification table, position in ST

    public ProgramInternalForm(){
        this.pairs = new ArrayList<>();
    }

    public void add(Pair<Integer, Integer> pair){
        this.pairs.add(pair);
    }

    public void printPifContent(){
        System.out.println("\tThe contents of the PIF:");
        for(Pair<Integer, Integer> pair: this.pairs){
            System.out.println(pair.getKey() +" -> " + pair.getValue());
        }
    }
}
