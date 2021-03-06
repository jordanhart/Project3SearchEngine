package popCultureReference;

/* Search Engine created by Group Pop Culture Reference
 *
 * Comprising of Jordan Hart, Brannon Centeno, Kish Moore,
 * Steven B,  Alexis Herrera.
*/


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.*;
import java.util.prefs.Preferences;


interface MainWindow {


   void allSearchTerms(String terms);

   void anySearchTerms(String terms);

   void exactPhrase(String terms);

}


public class SearchEngine implements MainWindow {

   private JFrame frame;
   private int selection;
   private FileCreator fc = new FileCreator();
   private JTextArea textArea;
   private GetsAndSets getsAndSets = new GetsAndSets();


   /**
    * Launch the application.
    */
   public static void main(String[] args) throws IOException {


      if (Files.notExists(Paths.get("./File Reference Directory/File Reference.txt"))) {
         new File("./File Reference Directory").mkdir();
         File temp = new File("./File Reference Directory/File Reference.txt");
         FileWriter writer = new FileWriter(temp, true);
      }


      SearchEngine window = new SearchEngine();
      window.frame.setVisible(true);
   }


   class ViewHelpMenuListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {

         JOptionPane.showMessageDialog(null, "Need to create help menu");
      }
   }


   /**
    * Create the application.
    */
   public SearchEngine() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {


      frame = new JFrame();
      frame.setTitle("Search Engine");
      frame.setBounds(100, 100, 600, 400);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      JMenuBar menuBar = new JMenuBar();
      frame.setJMenuBar(menuBar);

      ///////////////////////////The File & Help menus//////////////////////////////////////////////
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);

      JMenuItem maintenanceItem = new JMenuItem("Maintenance");
      fileMenu.add(maintenanceItem);
      maintenanceItem.addActionListener(e -> new MaintenanceGUI());

      JMenuItem exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(e -> System.exit(0));
      fileMenu.add(exitItem);


      JMenu helpMenu = new JMenu("Help");
      menuBar.add(helpMenu);

      JMenuItem aboutItem = new JMenuItem("About");
      aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, " Created by Jordan Hart, Brannon Centeno, Kish Moore, Steven B, and Alexis Herrera"));
      helpMenu.add(aboutItem);

      frame.getContentPane().setLayout(new BorderLayout(0, 0));
      ///////////////////////////////////////////////////////////////////////////////
      /* Top Bar */

      JPanel searchBarPanel = new JPanel();
      frame.getContentPane().add(searchBarPanel, BorderLayout.NORTH);

      JLabel searchLabel = new JLabel("Search Terms");
      searchBarPanel.add(searchLabel);

      JTextField searchField = new JTextField("", 28);
      searchBarPanel.add(searchField);

      JButton searchButton = new JButton("Search");
      searchBarPanel.add(searchButton);
      searchButton.addActionListener(e -> {
         String terms = Normalizer.normalize(searchField.getText(), Normalizer.Form.NFKC);


         switch (selection) {
            case 1:
               allSearchTerms(terms);
               textArea.setText(getsAndSets.getTextAreaDisplayer());
               break;

            case 2:
               anySearchTerms(terms);
               textArea.setText(getsAndSets.getTextAreaDisplayer());
               break;

            case 3:
               exactPhrase(terms);

               textArea.setText(getsAndSets.getTextAreaDisplayer());
               break;

            default:
               selection = 0;

         }
      });


      /////////////////Center JText Area//////////////////////////////////////
      JPanel resultPanel = new JPanel();
      frame.getContentPane().add(resultPanel);

      textArea = new JTextArea(15, 49);
      textArea.setEditable(false);

      resultPanel.add(textArea);

      JTextArea resultArea = new JTextArea();
      resultArea.setEditable(false);
      resultPanel.add(resultArea);
      //textArea.setText();
      /////////////////////////////////////////////////////////////////////////


      //////////////Radio Button Stuff (South)////////////////////////////////////////////////////////////////////////////////////////////////

      JPanel searchTypePanel = new JPanel();


      JLabel searchTypeLabel = new JLabel("Search Type:");
      searchTypePanel.add(searchTypeLabel);

      JRadioButton allSearchTermButton = new JRadioButton();
      allSearchTermButton.setText("All Search Terms");

      JRadioButton anySearchButton = new JRadioButton();
      anySearchButton.setText("Any Search Terms");

      JRadioButton exactPhraseButton = new JRadioButton();
      exactPhraseButton.setText("Exact Phrase");
      searchTypePanel.add(exactPhraseButton);

      ButtonGroup group = new ButtonGroup();
      group.add(allSearchTermButton);
      group.add(anySearchButton);
      group.add(exactPhraseButton);
      searchTypePanel.add(allSearchTermButton);
      searchTypePanel.add(anySearchButton);
      searchTypePanel.add(exactPhraseButton);

      frame.getContentPane().add(searchTypePanel, BorderLayout.SOUTH);

      /*ActionListeners for the Radio Buttons, which change the output*/
      allSearchTermButton.addActionListener(e -> {
         selection = 1;
         System.out.println("All Search Activated");
      });

      anySearchButton.addActionListener(e -> {
         selection = 2;
         System.out.println("Any Search Activated");

      });


      exactPhraseButton.addActionListener(e -> {
         selection = 3;
         System.out.println("Exact Phrase Search Activated");
      });

      //////////////////////////////////////////////////////////////////////////////////////////////////////////////


   }

   /* allSearchTerms method takes in the terms from the Search Bar, compares it to the
    * ArrayList containing each word from the files uploaded by the user in the Maintenance
    * menu.
    *
    * The method compares the search terms to the files based on whether all of the search
    * terms appear in the files, but does not take into account their order.
    */
   @Override
   public void allSearchTerms(String terms) {
      terms = fc.wordSeparator(terms);
      Set<String> isInFile = new HashSet<>();
      ArrayList<String> enteredSearchTerms = new ArrayList<>();
      Scanner scanner = new Scanner(terms);
      while (scanner.hasNextLine()) {
         enteredSearchTerms.add(scanner.nextLine());

      }

      System.out.println(enteredSearchTerms);

      try {
         for (File x : fc.ArrayListCreator()) {
            isInFile.add(x.getName());

         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      for (String x : enteredSearchTerms) {

         StringBuilder sb = new StringBuilder();
         try {
            for (File y : fc.ArrayListCreator()) {
               List<String> termsInFile = fc.FileToString(y);

               for (String string : termsInFile) {
                  sb.append(string + "\n");
               }

            }


            ArrayList<String> al = new ArrayList<>();
            String s = sb.toString();
            int it = 0;


            Scanner sc = new Scanner(s);

            while (sc.hasNextLine()) {
               al.add(it, sc.nextLine());
               it++;
            }

            System.out.println("Contents of ArrayList:" + al);

            for (File y : fc.ArrayListCreator()) {

               List<String> ese = fc.FileToString(y);

               if (ese.contains(x) == false) {
                  isInFile.remove(y.getName());
               }

            }

         } catch (Exception e) {
            System.out.println("Exception");
         }


      }

      System.out.println("And:" + isInFile);

      getsAndSets.setContainsTheWord(isInFile);
      getsAndSets.setTextAreaDisplayer(fc.changeHashSetIntoText(getsAndSets.getContainsTheWord()));

   }

   /* anySearchTerms method takes in the terms from the Search Bar, compares it to the
   * ArrayList containing each word from the files uploaded by the user in the Maintenance
   * menu.
   *
   * The method compares the search terms to the files based on whether they appear in the files
   * and does not take into account any kind of order.
   */

   @Override
   public void anySearchTerms(String terms) {
      terms = fc.wordSeparator(terms);
      HashSet<String> isInFile = new HashSet<>();
      ArrayList<String> enteredSearchTerms = new ArrayList<>();
      Scanner scanner = new Scanner(terms);
      while (scanner.hasNextLine()) {
         enteredSearchTerms.add(scanner.nextLine());

      }

      for (String x : enteredSearchTerms) {


         try {
            for (File y : fc.ArrayListCreator()) {

               List<String> termsInFile = fc.FileToString(y);

               if (termsInFile.contains(x) == true) {
                  isInFile.add(y.getName());
               }

            }

         } catch (IOException e) {
            e.printStackTrace();
         }

      }
      getsAndSets.setContainsTheWord(isInFile);
      getsAndSets.setTextAreaDisplayer(fc.changeHashSetIntoText(getsAndSets.getContainsTheWord()));

   }

   /* exactPhrase method takes in the terms from the Search Bar, compares it to the
    * ArrayList containing each word from the files uploaded by the user in the Maintenance
    * menu.
    *
    * The method compares the search terms to the files based on whether all of the search
    * terms appear in the files and the order in which they appear.
    */

   @Override
   public void exactPhrase(String terms) {
      try {
         String[] separatedTerms = terms.split(" ");
         Set<String> fileMatches = new HashSet<>();
         List<File> files = fc.ArrayListCreator();


         for(File x: files){
            int iterator = 0;
            List<String> fileString = fc.FileToString(x);

            if(fileString.contains(separatedTerms[0])){
               int startingIndex = fileString.indexOf(separatedTerms[0]);
               System.out.println(startingIndex);


             System.out.println(fileString);
               List<String> lister = fileString.subList(startingIndex, startingIndex + (separatedTerms.length));
               String[] fileStringArray = Arrays.copyOf(lister.toArray(), lister.toArray().length, String[].class);

               for(int i = 0; separatedTerms.length > i; i++){
                  System.out.println(fileStringArray[i]);
                  System.out.println(separatedTerms[i]);


               }

               List<Boolean> bool = new ArrayList<>();
               int ear = 0;
              for(String fuccboi: fileStringArray){

                 if(fileStringArray[ear].equals(separatedTerms[ear])){
                    bool.add(ear ,true);
                 }

                 else{
                    bool.add(ear, false);
                 }

                 System.out.println(fileStringArray[ear]);
                 System.out.println(separatedTerms[ear]);


                  ear++;

                 System.out.println(bool);

              }

               if(!bool.contains(false)){
                  fileMatches.add(x.getName());
               }






            }






         }






         getsAndSets.setContainsTheWord(fileMatches);
         getsAndSets.setTextAreaDisplayer(fc.changeHashSetIntoText(getsAndSets.getContainsTheWord()));



      }catch (Exception e){
         e.printStackTrace();
      }



   }


}