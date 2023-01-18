import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame{
    private JButton buttonAddColumn;
    private JButton buttonRemoveColumn;
    private JTable tableResult;
    private JPanel buttonsPanelForTable;
    private JTextField textFieldForReadFromFile;
    private JTextField textFieldForWriteInFile;
    private JButton buttonForReadFromFile;
    private JButton buttonForProcess;
    private JButton buttonForSave;
    private JPanel mainPanel;

    Logic logic = new Logic();
    private DefaultTableModel tableModel;
    private void createUIComponents(){
        tableResult = new JTable(1, 6);
    }

    public Window(){
        this.setTitle("Task 9.12");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(1, 6);
        tableResult.setModel(tableModel);

        buttonForReadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textFieldForReadFromFile.getText();
                try{
                    ArrayList<Integer> list = ProcessingOfFile.readListFromFile(input);
                    fullJTableFromList(list, tableModel);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonForProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> list = getListFromJTable(tableModel);
                ArrayList<Integer> listResult = logic.process(list);
                fullJTableFromList(listResult, tableModel);
            }
        });

        buttonForSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output = textFieldForWriteInFile.getText();
                ArrayList<Integer> listForFile = getListFromJTable(tableModel);
                try {
                    ProcessingOfFile.writeListIntoFile(listForFile, output);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonAddColumn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            }
        });
        buttonRemoveColumn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
            }
        });

        this.pack();
        this.setVisible(true);
    }

    public static void fullJTableFromList (ArrayList<Integer> list, DefaultTableModel tableModel){
        tableModel.setColumnCount(list.size());
        for (int i = 0; i < list.size(); i++){
            tableModel.setValueAt(list.get(i), 0, i);
        }
    }
    public static ArrayList<Integer> getListFromJTable (DefaultTableModel tableModel){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < tableModel.getColumnCount(); i++){
            list.add((Integer) tableModel.getValueAt(0, i));
        }
        return list;
    }

}
