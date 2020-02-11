import java.util.Comparator;

/**
 * @author mihaimusat
 */

public class Comp implements Comparator<Instances> {
    public int compare(Instances o1, Instances o2) {
        if(o1.getTimeStamp() > o2.getTimeStamp()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}