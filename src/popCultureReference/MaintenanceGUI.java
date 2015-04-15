package popCultureReference;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;


interface MaintanceInterface {


   void addFile();

   void rebuildData();

   void removeFile();

}

public class MaintenanceGUI implements MaintanceInterface {


   private FileCreator fc = new FileCreator();
   private JFrame frame;


   MaintenanceGUI() {
      System.out.println("Maintenance Initialized");
      try {
         initialize();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void initialize() throws IOException {
      //table data initialization
      frame = new JFrame();
      frame.setTitle("Maintenance");
      frame.setBounds(100, 100, 600, 400);
      frame.setResizable(false);
      //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setLayout(new BorderLayout());
    


      JTable table = new JTable();
      table.setRowHeight(30); //Will probably be the length of the array or something
      JScrollPane scrollPane = new JScrollPane(table);
      frame.add(scrollPane, BorderLayout.CENTER);


      //South Buttons
      JPanel bottom = new JPanel();
      JButton addFileButton = new JButton("Add File");
      JButton deleteFileButton = new JButton("Delete File");
      JButton rebuildFileButton = new JButton("Rebuild File");
      bottom.add(addFileButton);
      bottom.add(deleteFileButton);
      bottom.add(rebuildFileButton);
      frame.add(bottom, BorderLayout.SOUTH);


      //South Button ActionListeners
      addFileButton.addActionListener(e -> addFile());


      deleteFileButton.addActionListener(e -> dummy());


      rebuildFileButton.addActionListener(e -> dummy());

   }


   /*
   Is called when the "Add File" button is clicked
   A new instance of FileCreator is created
   A file chooser that only allows for the selection of .txt files is brought up
   The selected file is then passed into the method "FileWriterAwesome" which is located in the FileCreator class

   What FileWriterAwesome does:
   Creates a file in the "File Reference Directory" folder
    Writes the path name of the selected file to the created .txt
    Every time a new file is selected it adds a new path name

    */
   @Override
   public void addFile() {



         JFileChooser chooser = new JFileChooser();
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files Only", "txt");
         chooser.setFileFilter(filter);
         if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            StringBuilder sb = new StringBuilder(chosenFile.getName());

            if (!sb.subSequence(sb.length() - 4, sb.length()).equals(".txt")) {
               JOptionPane.showMessageDialog(null, "Only text files with the extension '.txt' can be selected.\nClose this dialog box and try again, fool!");

            } else {


               try {
                  fc.FileWriterAwesome(chosenFile);
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }
         }

   }

   /* Rebuilds/Refreshes the files in the maintenance menu, based on whether
    * or not there have been changes to the files */
   @Override
   public void rebuildData() {
      // TODO Auto-generated method stub

   }

   /* Removes selected files from the Maintenance menu*/
   @Override
   public void removeFile() {
      // TODO Auto-generated method stub

   }

   public void dummy() {
      System.out.println("Dummy");
   }


}
