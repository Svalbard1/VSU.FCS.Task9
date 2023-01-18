import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessingOfFile {
    public static ArrayList<Integer> readListFromFile (String nameFile) throws FileNotFoundException, FileNotFoundException {
        FileReader fr = new FileReader(nameFile); //читает текст из файла
        Scanner scanner = new Scanner(fr);

        String fileLine = scanner.nextLine();
        ArrayList<Integer> listFromFile = new ArrayList<>();

        for (String element : fileLine.split(" ")){
            try {
                listFromFile.add(Integer.valueOf(element));
            } catch (Exception ignored){            };
        }

        return listFromFile;
    }

    public static void writeListIntoFile(ArrayList<Integer> list, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        String s = "";

        for(Integer elem : list){
            s += " ";
            s += String.valueOf(elem);
        }

        fileWriter.write(s);
        fileWriter.close();
    }
}
