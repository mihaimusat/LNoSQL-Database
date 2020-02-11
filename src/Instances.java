import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * @author mihaimusat
 */

public class Instances {
    private Long timeStamp; //pentru ordinea in care adaug in baza de date
    private String id; //cheia primara
    private String entityName; //numele entitatii
    private LinkedList<Attributes> attributes; //reprezint instanta ca o lista de atribute

    /**
     * Constructor pentru clasa Instances
     * @param id cheia primara
     * @param entityName numele entitatii
     * @param atribute lista de atribute
     * @param atribut vector de tipuri de atribute
     * @param timeStamp pentru a retine ordinea
     */
    public Instances(String id, String entityName, LinkedList<Attributes> atribute, String[] atribut, Long timeStamp) {
        this.timeStamp = timeStamp;
        this.id = id;
        this.entityName = entityName;
        attributes = new LinkedList<>();
        int index = 0;
        for(int i = 2; i < atribut.length; i ++) {
            Attributes aux = new Attributes(atribute.get(index).getType(), atribute.get(index).getAttributeName());
            aux.setAttributes(atribut[i]);
            this.attributes.add(aux);
            index++;
        }
    }

    public String getId() {
        return id;
    }

    public String getEntityName() {
        return entityName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Functie pentru afisarea unei instante:
     * numele entitatii, urmata de atributele pe
     * care le contine
     * @param printWriter fisierul de output
     */
    public void printInstances(PrintWriter printWriter) {
        printWriter.write(entityName);
        for(Attributes atribut : attributes) {
            atribut.printAttributes(printWriter);
        }
        printWriter.write('\n');
    }

    /**
     * Functie pentru a da update unei instante
     * @param value valoarea cu care vreau sa fac update
     * @param atributeName numele initial al atributelor
     */
    public void updateInstance(String value, String atributeName) {
        for(Attributes atribut : attributes) {
            if(atribut.getAttributeName().equals(atributeName))
                atribut.setAttributes(value);
        }
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.entityName.equals(((Instances)obj).getEntityName()) && this.id.equals(((Instances)obj).getId()));
    }
}