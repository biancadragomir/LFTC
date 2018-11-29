import java.util.Vector;

public class SymTableIdentifier {
    private Vector<String> vector;
    public SymTableIdentifier(){
        vector = new Vector<>();
    }

    /**
     * Add a new element to the sym table const.
     * Return -1 if it was added or a positive int if it has just been added
     */
    public int addElement(String elem){

        //TODO first check if the element is already contained
        if( !vector.contains(elem)) {
            if (vector.size() > 0) {
                int nextAvailable = -1;
                if (elem.compareToIgnoreCase(vector.get(0)) < 0) {
                    nextAvailable = findFirstEmptyLeft(1, elem);
                } else {
                    if (elem.compareToIgnoreCase(vector.get(0)) > 0){
                        nextAvailable = findFirstEmptyRight(2, elem);
                    }
                }
                if (nextAvailable != -1) {
                    vector.add(nextAvailable, elem);
                    vector.setSize(nextAvailable * 2 + 3);
                    vector.add(nextAvailable * 2 + 1, "-1");
                    vector.add(nextAvailable * 2 + 2, "-1");

                }
                return -1;
            } else {
                vector.add(elem);
                vector.add("-1");
                vector.add("-1");
            }
            return -1;
        }
        return getPosition(elem, 0);

    }

    private int findFirstEmptyLeft(int pos, String elem){
        if(vector.get(pos).equals("-1"))
            return pos;
        else
        {
            if(elem.compareToIgnoreCase(vector.get(pos))<0)
                return findFirstEmptyLeft(2*pos + 1, elem);
            else
                return findFirstEmptyRight(2*pos+2, elem);

        }
    }

    private int findFirstEmptyRight(int pos, String elem){
        if(vector.get(pos).equals("-1"))
            return pos;
        else{
            if(elem.compareToIgnoreCase(vector.get(pos))<0)
                return findFirstEmptyLeft(2*pos + 1, elem);
            else
                return findFirstEmptyRight(2*pos+2, elem);
        }
    }

    private boolean contains(String elem, int pos){
        if(vector.get(pos).equals(elem))
            return true;
        else{
            if(elem.compareToIgnoreCase(vector.get(pos)) <0)
                return contains(elem, pos+1);
            else return contains(elem, pos+2);
        }
    }

    public Vector<String> getList(){
        return this.vector;
    }

    public int getPosition(String elem, int pos){
        if(vector.get(pos).equals(elem))
            return pos;
        else{
            if(elem.compareToIgnoreCase(vector.get(pos))<0)
                return getPosition(elem, pos*2+1);
            else
                return getPosition(elem, pos*2+2);
        }
    }

}
