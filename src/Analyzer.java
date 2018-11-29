import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {

    private SymTableIdentifier symTableIdentifier;
    private SymTableIdentifier symTableConst;
    private ProgramInternalForm pif;
    public Analyzer(){
        this.symTableConst = new SymTableIdentifier();
        this.symTableIdentifier = new SymTableIdentifier();
        this.pif = new ProgramInternalForm();
    }

    public void analyze(String sourceCodeFile, String codificationTableFile) {
        String line;
        String[] tokens;
        List<Pair<String, Integer>> codificationTable = new ArrayList<>(); // name - code
        List<String> codifTableKeys = new ArrayList<>();  // just the reserved words and characters from the language
        int lineCount = 1;

        Pattern numberPattern =  Pattern.compile("^[1-9][0-9]*[;]?$"); // ex. 123 but not 0123
        Pattern identifierPattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$"); // abc123 abc1ab1
        Pattern operatorPatter = Pattern.compile("^<|>|(=>)|(<=)|\\+|\\-|\\*|/$");
        Pattern characterPattern = Pattern.compile("^'(\\w|\\d)'$");

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(codificationTableFile));
            line = br1.readLine();
            while (line != null) {
                tokens = line.split("\\s+");// this will cause any number of consecutive spaces to split the string into tokens.
                codificationTable.add(new Pair<>(tokens[0], Integer.parseInt(tokens[1])));
                codifTableKeys.add(tokens[0]);

                //read the next line
                line = br1.readLine();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {

            BufferedReader br2 = new BufferedReader(new FileReader(sourceCodeFile));
            line = br2.readLine();
            boolean isReservedWord, isSthElse; int added;

            while (line != null) {
                tokens = line.split("\\s+|\\;|\\:|\\{|\\}|\\(|\\)|\\\\[|\\\\]|(\\s;)");
                for(int i=0; i<tokens.length;i++){
                    isReservedWord = false;
                    isSthElse = false;
                    added = -1;

                    if(codifTableKeys.contains(tokens[i])) {
                        isReservedWord = true;
                    }

                    else{

                        Matcher matcher = numberPattern.matcher(tokens[i]);

                        if(matcher.find()){
                            isSthElse = true;
                            added = symTableConst.addElement(tokens[i]);
                            if(added == -1){
                                pif.add(new Pair<>(1,  symTableConst.getPosition(tokens[i], 0)));
                            }
                        }

                        matcher = identifierPattern.matcher(tokens[i]);
                        if(matcher.find()){
                            isSthElse = true;
                            added = symTableIdentifier.addElement(tokens[i]);
                            if(added == -1){
                                pif.add(new Pair<>(1,  symTableIdentifier.getPosition(tokens[i], 0)));
                            }
                        }

                        matcher = operatorPatter.matcher(tokens[i]);
                        if(matcher.find()){
                            isSthElse = true;
                            pif.add(new Pair<>(codifTableKeys.indexOf(tokens[i]), -1));
                        }

                        matcher = characterPattern.matcher(tokens[i]);
                        if(matcher.find()){
                            isSthElse = true;
                            added = symTableConst.addElement(tokens[i]);
                            if(added == -1){
                                pif.add(new Pair<>(1,  symTableConst.getPosition(tokens[i], 0)));
                            }
                            //TODO create sym t const s.t you can also add strings there
                        }

                    }

                    if(!isSthElse && !isReservedWord)
                        System.err.println("Error on line " + lineCount);
                }
                line=br2.readLine();
                lineCount++;

            }
        }catch(IOException e){
            System.err.println(e.getMessage());
            }

        }

        public void printAftermath(){
            System.out.println("Sym table const: ");
            Iterator<String> it = this.symTableConst.getList().iterator();

            int count = 0;
            while(it.hasNext()){
                System.out.println(count + " "  +  it.next());
                count++;
            }

            System.out.println("------------------");
            System.out.println("Sym table identifier: ");
            Iterator<String> it2 = this.symTableIdentifier.getList().iterator();

            count = 0;
            while(it2.hasNext()){
                System.out.println(count + " "  +  it2.next());
                count++;
            }

            System.out.println("-----------------");
            System.out.println("PIF contents:");
            pif.printPifContent();

        }

}
