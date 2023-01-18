import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    Logic logic = new Logic();

    public Console() throws IOException {
        //Получение путей на ввод/вывод из консоли
        InputArgs inputArgs = parseCmdParameters();

        //Чтение из файла
        ArrayList<Integer> inputList = ProcessingOfFile.readListFromFile(inputArgs.getInputFilePath());

        //Обработка данных
        ArrayList<Integer> outputList = logic.process(inputList);

        ProcessingOfFile.writeListIntoFile(outputList, inputArgs.getOutputFilePath());
    }

    public InputArgs parseCmdParameters(){
        System.out.println("Введите Input-файл и Output-файл через пробел: ");
        Scanner scanner = new Scanner(System.in);
        String[] filePaths = scanner.nextLine().split(" ");
    return new InputArgs(filePaths[0], filePaths[1]);
    }
}
