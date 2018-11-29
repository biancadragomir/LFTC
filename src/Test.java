import java.util.Iterator;
import java.util.Vector;

public class Test {
    static void testSymTConst(){
        SymTableConst symTableConst = new SymTableConst();

        int testAdd;
        symTableConst.addElement(3);
        symTableConst.addElement(1);

        testAdd = symTableConst.addElement(5);
        System.out.println(testAdd + "test add");
        symTableConst.addElement(7);
        testAdd = symTableConst.addElement(5);
        System.out.println(testAdd + "test add");

        symTableConst.addElement(0);
        System.out.println(symTableConst.addElement(5) + " test add");

        Vector<Integer> list = symTableConst.getList();

        Iterator<Integer> it = list.iterator();

        int count = 0;
        System.out.println("Vector contents: ");
        while(it.hasNext()){
            System.out.println(count + " "  +  it.next());
            count++;
        }
    }

    static void testSymTId(){
        SymTableIdentifier symTableIdentifier = new SymTableIdentifier();

        symTableIdentifier.addElement("D");
        symTableIdentifier.addElement("A");
        symTableIdentifier.addElement("F");
        System.out.println("add b" + symTableIdentifier.addElement("B"));
        symTableIdentifier.addElement("E");
        System.out.println("add again! "+        symTableIdentifier.addElement("B"));

        Vector<String> list = symTableIdentifier.getList();

        Iterator<String> it = list.iterator();

        int count = 0;
        System.out.println("Vector contents: ");
        while(it.hasNext()){
            System.out.println(count + " "  +  it.next());
            count++;
        }

    }
}
