import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        System.out.println("Analyzer started...");

        Analyzer analyzer = new Analyzer();

        analyzer.analyze("D:\\Work\\Facultate\\school-repo\\LFTC\\LexicalScannerV2\\src\\source_code", "D:\\Work\\Facultate\\school-repo\\LFTC\\LexicalScannerV2\\src\\codification_table.txt");

        analyzer.printAftermath();

        System.out.println("Analyzer done.");

    }
}
