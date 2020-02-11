import java.util.LinkedList;

/**
 * @author mihaimusat
 */

public class Entity {
    private String entityName; //numele entitatii
    private int rf; //factorul de replicare
    private LinkedList<Attributes> attributes; //reprezint entitatea ca o lista de atribute

    /**
     * Constructor pentru clasa Entity
     * @param entityName numele entitatii
     * @param rf factor de replicare
     * @param types vector de tip String de atribute
     */

    public Entity(String entityName, int rf, String[] types) {
        attributes = new LinkedList<>();
        this.entityName = entityName;
        this.rf = rf;
        for(int i = 4; i<types.length; i = i + 2) {
            attributes.add(new Attributes(types[i+1], types[i]));
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public int getRf() {
        return rf;
    }

    public LinkedList<Attributes> getAttributes() {
        return attributes;
    }
}