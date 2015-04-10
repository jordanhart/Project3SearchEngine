package popCultureReference;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

interface MaintanceInterface {

	  
    void addFile();

    void rebuildData();

    void removeFile();

}

public class MaintenanceGUI implements MaintanceInterface {

	FileCreator fc = new FileCreator();
	private JFrame frame;


	MaintenanceGUI(){
		System.out.println("Maintenance Initialized");
		try {initialize();} catch (IOException e) {e.printStackTrace();}
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

		//JList
		JPanel top = new JPanel();
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension (600,350));
		top.add(listScroller);

		list.addListSelectionListener(
				new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {

					}
				}
		);

		frame.add(top, BorderLayout.CENTER);

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
	public void addFile(){

		/*
		This disposes of the old frame (closes)
 		*/
		frame.setVisible(false);
		frame.dispose();

		/*
		Brings up a filechooser that only selects .txt files
		If the user force it to choose a non .txt file it pulls up a dialog box telling them that only text files can be selected
		Also if they choose a non .txt, it's not added into anything (reference text, arrays, etc.)
		 */
		FileCreator fc = new FileCreator();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files Only", "txt");
		chooser.setFileFilter(filter);
		if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File chosenFile = chooser.getSelectedFile();
			StringBuilder sb = new StringBuilder(chosenFile.getName());

			if(!sb.subSequence(sb.length()-4, sb.length()).equals(".txt")){
				JOptionPane.showMessageDialog(null ,"Only text files with the extension '.txt' can be selected.\nClose this dialog box and try again, fool!");
			}
			else {
				try {fc.FileWriterAwesome(chosenFile);} catch (IOException e) {e.printStackTrace();}
			}
		}

		/*
		This re-initializes the updated maintenance gui so that it shows files that have been added
		 */
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

   @Override
   public void rebuildData() {
      // TODO Auto-generated method stub

   }
   @Override
   public void removeFile() {
      // TODO Auto-generated method stub

   }

	public void dummy(){
		System.out.println("Dummy");
	}



}
