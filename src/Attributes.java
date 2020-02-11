import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 * @author mihaimusat
 */

public class Attributes {
    private String string; //tipul de date string
    private Float aFloat; //tipul de date float
    private Integer integer; //tipul de date intreg
    private String atributName; //numele atributului
    private String type; //tipul atributului

    /**
     * Constructor pentru clasa Attributes
     * @param type pentru a indica ce tip are atributul
     * @param atributName pentru a indica numele atributului
     */
    public Attributes(String type, String atributName) {
        this.atributName = atributName;
        this.type = type;
    }

    public String getAttributeName() {
        return atributName;
    }

    public String getType() {
        return type;
    }

    public void setAttributes(String value) {
        if (type.equals("Float")) {
            aFloat = Float.parseFloat(value);
        } else if (type.equals("Integer")) {
            integer = Integer.parseInt(value);
        } else if (type.equals("String")) {
            string = value;
        }
    }

    /**
     * Afiseaza atributele in functie de tipul de date pe care il au
     * @param printWriter fisierul in care se va face afisarea
     */
    public void printAttributes(PrintWriter printWriter) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (type.equals("Float")) {
            printWriter.write(" " + atributName + ":" + decimalFormat.format(aFloat));
        } else if (type.equals("String")) {
            printWriter.write(" " + atributName + ":" + string);
        } else if (type.equals("Integer")) {
            printWriter.write(" " + atributName + ":" + integer);
        }
    }
}