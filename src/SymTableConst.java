import java.util.Iterator;
import java.util.Vector;

public class SymTableConst {
    private Vector<Integer> vector;
    public SymTableConst(){
        vector = new Vector<>();
    }

    public int getPosition(int elem, int pos){
        if(vector.get(pos) == elem)
            return pos;
        else{
            if(elem< vector.get(pos))
                return getPosition(elem, pos*2+1);
            else
                return getPosition(elem, pos*2+2);
        }
    }

    /**
     * Add a new element to the sym table const.
     * Return -1 if it was added or a positive int if it has just been added
     */
    public int addElement(int elem){

        //TODO first check if the element is already contained
        if( !vector.contains(elem)) {
            if (vector.size() > 0) {
                int nextAvailable = -1;
                if (elem < vector.get(0)) {
                    nextAvailable = findFirstEmptyLeft(1, elem);
                } else {
                    if (elem > vector.get(0)) {
                        nextAvailable = findFirstEmptyRight(2, elem);
                    }
                }
                if (nextAvailable != -1) {
                    vector.add(nextAvailable, elem);
//                    System.out.println(vector.size());
                    vector.setSize(nextAvailable * 2 + 3);
                    vector.add(nextAvailable * 2 + 1, -1);
                    vector.add(nextAvailable * 2 + 2, -1);

                }
                return -1;
            } else {
                vector.add(elem);
                vector.add(-1);
                vector.add(-1);
            }
            return -1;
        }

        return getPosition(elem, 0);

        /**for getting the pos
        for(int i=0; i<vector.size(); i++)
        {
            if ( vector.get(i) == elem)
                return i;
        }
        */

        //TODO case when the element is already contained by the tree
    }

    private int findFirstEmptyLeft(int pos, int elem){
        if(vector.get(pos)== -1)
            return pos;
        else
        {
            if(elem < vector.get(pos))
                return findFirstEmptyLeft(2*pos + 1, elem);
            else
                return findFirstEmptyRight(2*pos+2, elem);

        }
    }

    private int findFirstEmptyRight(int pos, int elem){
        if(vector.get(pos) == -1)
            return pos;
        else{
            if(elem < vector.get(pos))
                return findFirstEmptyLeft(2*pos + 1, elem);
            else
                return findFirstEmptyRight(2*pos+2, elem);
        }
    }

    private boolean contains(int elem, int pos){
        if(vector.get(pos) == elem)
            return true;
        else{
            if(elem<vector.get(pos))
                return contains(elem, pos+1);
            else return contains(elem, pos+2);
        }
    }

    public Vector<Integer> getList(){
        return this.vector;
    }

    /**
     * Return the position on which an element is, or -1 if it is not contained
     * @param elem
     * @return
     */

}
