import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author mihaimusat
 */

public class DataBase {
    private String dataBaseName; //numele bazei de date
    private int noNodes; //numarul de noduri din baza de date
    private int maxCap; //capacitatea maxima
    private LinkedList<Entity> entities = new LinkedList<>(); //entitatile sunt reprezentate generic
    private LinkedList<LinkedList<Instances>> instances; //baza de date e un fel de hashtable

    public DataBase(String dataBaseName, int noNodes, int maxCap) {
        this.dataBaseName = dataBaseName;
        this.noNodes = noNodes;
        this.maxCap = maxCap;
        this.instances = new LinkedList<>();
        for(int i = 0; i < this.noNodes; i++) {
            LinkedList<Instances> nod = new LinkedList<>();
            instances.add(nod);
        }
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }

    /**
     * Inserarea unei entitati intr-o baza de date
     * @param instance instanta pe care vreau s-o adaug
     */
    public void insert(Instances instance) {
        for(int i = 0; i < instances.size(); i++) {
            if(!instances.get(i).contains(instance) && instances.get(i).size() < maxCap) {
                instances.get(i).add(instance);
                Collections.sort(instances.get(i), new Comp());
                break;
            }
        }
    }

    /**
     * Afisarea unei baze de date la un moment de timp dat
     * @param printWriter fisierul de output in care se face afisarea
     */
    public void snapShot(PrintWriter printWriter) {
        boolean empty = true;
        for(int i = 0; i < instances.size(); i++) {
            if (instances.get(i).size() != 0) {
                empty = false;
            }
        }

        if(empty) {
            printWriter.write("EMPTY DB\n");
        }
        else {
            for (int i = 0; i < instances.size(); i++) {
                if (instances.get(i).size() != 0) {
                    printWriter.write("Nod" + (i + 1) + "\n");
                    for (int j = 0; j < instances.get(i).size(); j++) {
                        instances.get(i).get(j).printInstances(printWriter);
                    }
                }
            }
        }
    }

    /**
     * Actualizeaza copiile instantei pe toate nodurile
     * @param entityName numele entitatii
     * @param id cheia primara
     * @param atribute vectorul de atribute cu care fac update
     * @param timeStamp numarul de ordine pentru introducerea instantei
     */
    public void update(String entityName, String id, String[] atribute, Long timeStamp) {
        for(int k = 3 ; k < atribute.length; k = k +2)  {
            for (int i = 0; i < instances.size(); i++) {
                for (int j = 0; j < instances.get(i).size(); j++) {
                    if (instances.get(i).get(j).getEntityName().equals(entityName) && instances.get(i).get(j).getId().equals(id)) {
                        instances.get(i).get(j).updateInstance(atribute[k+1], atribute[k]);
                        instances.get(i).get(j).setTimeStamp(timeStamp);
                    }
                }
                Collections.sort(instances.get(i), new Comp());
            }
        }
    }

    /**
     * Sterge copiile instantei pe toate nodurile
     * @param entityName numele entitatii
     * @param id cheia primara
     * @param printWriter fisierul de output in care se face afisarea
     */
    public void delete(String entityName, String id, PrintWriter printWriter) {
        boolean found = false;
        for (int i = 0; i < instances.size(); i++) {
            for (int j = 0; j < instances.get(i).size(); j++) {
                if (instances.get(i).get(j).getEntityName().equals(entityName) && instances.get(i).get(j).getId().equals(id)) {
                    instances.get(i).remove(j);
                    found = true;
                }
            }
        }
        if(!found) {
            printWriter.write("NO INSTANCE TO DELETE\n");
        }
    }

    /**
     * Afiseaza o instanta cu valorile si nodurile in care se afla
     * @param entityName numele entitatii
     * @param id cheia primara
     * @param printWriter fisierul de output in care se va face afisarea
     */
    public void get(String entityName, String id, PrintWriter printWriter) {
        Instances aux = null;
        LinkedList<String> auxNod = new LinkedList<>();
        for (int i = 0; i < instances.size(); i++) {
            for (int j = 0; j < instances.get(i).size(); j++) {
                if (instances.get(i).get(j).getEntityName().equals(entityName) && instances.get(i).get(j).getId().equals(id)) {
                    aux = instances.get(i).get(j);
                    auxNod.add("Nod" + (i + 1));
                }
            }
        }
        if(aux != null) {
            for (int i = 0; i < auxNod.size(); i++) {
                printWriter.write(auxNod.get(i) + " ");
            }
            aux.printInstances(printWriter);
        }else {
            printWriter.write("NO INSTANCE FOUND\n");
        }
    }

}