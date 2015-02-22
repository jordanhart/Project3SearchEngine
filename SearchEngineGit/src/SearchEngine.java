/* Search Engine created by Group Pop Culture Reference
 *
 * Comprising of Jordan Hart, Brannon Centeno, Kish Moore
 * and Alexis Herrera.
 *
 * This program takes in text files and allows users to 
 * search for terms in said files
 *
 * EDIT: Alexis Herrera - 7:12PM
 * This version is done completely in Swing 
 * and renamed SearchEngine.java to differentiate it from the Main
 * in the master branch
 *  
 */


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;



public class SearchEngine {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					SearchEngine window = new SearchEngine();
					window.frame.setVisible(true);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		/* Menu Bar
		 * 
		 * 
		 */
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		/* File Menu containing Maintenance and Exit Menu Options */
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem maintenanceItem = new JMenuItem("Maintenance");
		fileMenu.add(maintenanceItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		
		/* Help Menu Bar containing View Help and About Menu Options */
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem viewHelpItem = new JMenuItem("View Help");
		helpMenu.add(viewHelpItem);
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		/* Search Results Panel */
		
		JPanel resultPanel = new JPanel();
		frame.getContentPane().add(resultPanel);
			
			JTextArea textArea = new JTextArea("", 18, 70);
			textArea.setEditable(false);
			textArea.setRows(15);
			resultPanel.add(textArea);
		
			JTextArea resultArea = new JTextArea();
			resultArea.setEditable(false);
			resultPanel.add(resultArea);
		
		/* Top Bar */
		
		JPanel searchBarPanel = new JPanel();
		frame.getContentPane().add(searchBarPanel, BorderLayout.NORTH);
		
			JLabel searchLabel = new JLabel("Search Terms");
			searchBarPanel.add(searchLabel);
		
			JTextField searchField = new JTextField(" ", 28);
			searchBarPanel.add(searchField);
		
			JButton searchButton = new JButton("Search");
			searchBarPanel.add(searchButton);
		
		/* Low Bar */
		
		JPanel searchTypePanel = new JPanel();
		frame.getContentPane().add(searchTypePanel, BorderLayout.SOUTH);
		
		JLabel searchTypeLabel = new JLabel("Search Type:");
		searchTypePanel.add(searchTypeLabel);
		
		JRadioButton searchTermRButton = new JRadioButton("All Search Terms");
		searchTypePanel.add(searchTermRButton);
		
		JRadioButton anySearchRButton = new JRadioButton("Any Search Terms");
		searchTypePanel.add(anySearchRButton);
		
		JRadioButton exactPhraseRButton = new JRadioButton("Exact Phrase");
		searchTypePanel.add(exactPhraseRButton);
		
		
	}

}