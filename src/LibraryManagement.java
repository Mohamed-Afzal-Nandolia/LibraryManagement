import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryManagement extends JFrame implements ActionListener{
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JTextField textField1,textField2, textField3, textField4, textField5, textField6, textField7;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private JPanel panel;
    private ArrayList<String[]> books = new ArrayList<>();

    public LibraryManagement(){
        setTitle("Library Management System");//name of the application
        setSize(600,300);//dimensions
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //here we set the name of all the labels
        label1 = new JLabel("Book ID");
        label2 = new JLabel("Book Title");
        label3 = new JLabel("Author");
        label4 = new JLabel("Publisher");
        label5 = new JLabel("Year of Publication");
        label6 = new JLabel("ISBN");
        label7 = new JLabel("Number of Copies");

        //here we set the field where the user can input
        textField1 = new JTextField(10);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(10);
        textField6 = new JTextField(20);
        textField7 = new JTextField(10);

        //these are the action buttons, which will perform some action when pressed
        addButton = new JButton("Add");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton=new JButton("Exit");

        //to perform some action we need to know when the user clicks the button,
        //therefore we add actionListener to this buttons
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        //we create a new panel and all the labels, texfields, buttons to this panel
        panel = new JPanel(new GridLayout(10,2));
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(label5);
        panel.add(textField5);
        panel.add(label6);
        panel.add(textField6);
        panel.add(label7);
        panel.add(textField7);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        panel.add(exitButton);

        //after adding all labels, texfields, buttons to the panel, add the panel itself and set visibility to true
        add(panel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            String[] book = new String[7];//we are storing all the values inputted by the user in the textFields
            book[0] = textField1.getText();
            book[1] = textField2.getText();
            book[2] = textField3.getText();
            book[3] = textField4.getText();
            book[4] = textField5.getText();
            book[5] = textField6.getText();
            book[6] = textField7.getText();

            books.add(book);//now adding the array book to ArrayList<String[]> books
            //this is prebuild messageDialog which will display "Book added successfully"
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();//then clear all the text from textFields
        }
        else if(e.getSource() == viewButton){
            String[] columns = {"Book ID", "Book Title", "Author", "Publisher", "Year of Publication", "ISBN", "Number of Copies"};
            Object[][] data = new Object[books.size()][7];

            for(int i = 0; i < books.size(); i++){//gathering all the data
                data[i][0] = books.get(i)[0];
                data[i][1] = books.get(i)[1];
                data[i][2] = books.get(i)[2];
                data[i][3] = books.get(i)[3];
                data[i][4] = books.get(i)[4];
                data[i][5] = books.get(i)[5];
                data[i][6] = books.get(i)[6];
            }
            JTable table = new JTable(data, columns);//This class is used to arrange the components in a rectangular grid.
            JScrollPane scrollPane = new JScrollPane(table);//JScrollPane is used to give a scrollable view to your component. When the screen size is small or limited
            JFrame frame = new JFrame("View Books");//Creating a new JFrame and giving a title
            frame.add(scrollPane);
            frame.setSize(800, 400);
            frame.setVisible(true);
        }
        else if(e.getSource() == editButton){
            //this will pop a miniscreen that will ask for bookID to edit
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to edit:");

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookID)) {
                    String[] book = new String[7];
                    book[0] = bookID;
                    book[1] = textField2.getText();
                    book[2] = textField3.getText();
                    book[3] = textField4.getText();
                    book[4] = textField5.getText();
                    book[5] = textField6.getText();
                    book[6] = textField7.getText();
                    books.set(i, book);
                    JOptionPane.showMessageDialog(this, "Book edited successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        }
        else if(e.getSource() == deleteButton){
            //asking for the id from the user
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete:");

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookID)) {
                    books.remove(i);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        }
        else if(e.getSource() == clearButton){
            clearFields();
        }else if(e.getSource() == exitButton){
            System.exit(0);//this will exit the program
        }

    }
    private void clearFields(){
        //clearing all the textfields so that it can be used next time
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }
    public static void main(String[] args){
        new LibraryManagement();
    }
}