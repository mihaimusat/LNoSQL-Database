import java.io.*;

/**
 * @author mihaimusat
 */

public class Tema2 {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(args[0] + "_out");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String lines = null;
            DataBase dataBase = null ;
            while((lines = bufferedReader.readLine()) != null) {
                String[] splits = lines.split(" ");
                if(splits[0].equals("CREATEDB")) {
                    dataBase = new DataBase(splits[1], Integer.parseInt(splits[2]), Integer.parseInt(splits[3]));

                }else if(splits[0].equals("CREATE")) {
                    dataBase.getEntities().add(new Entity(splits[1], Integer.parseInt(splits[2]), splits));

                }else if(splits[0].equals("INSERT")) {
                    int index ;
                    for(index = 0; index < dataBase.getEntities().size(); index++) {
                        if(dataBase.getEntities().get(index).getEntityName().equals(splits[1])) {
                            break;
                        }
                    }

                    for(int i = 0; i < dataBase.getEntities().get(index).getRf(); i++) {
                        dataBase.insert(new Instances(splits[2], splits[1], dataBase.getEntities().get(index).getAttributes(), splits,System.nanoTime()));
                    }


                }else if(splits[0].equals("DELETE")) {
                    dataBase.delete(splits[1], splits[2], printWriter);

                }else if(splits[0].equals("UPDATE")) {
                    dataBase.update(splits[1], splits[2], splits, System.nanoTime());

                }else if(splits[0].equals("GET")) {
                    dataBase.get(splits[1], splits[2], printWriter);

                }else if(splits[0].equals("SNAPSHOTDB")) {
                    dataBase.snapShot(printWriter);
                }
            }

            fileReader.close();
            bufferedReader.close();
            fileWriter.close();
            printWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + args[0] + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + args[0] + "'");
        }
    }

}