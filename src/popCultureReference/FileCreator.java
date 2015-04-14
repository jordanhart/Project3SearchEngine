package popCultureReference;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by brannoncenteno on 3/19/15.
 */


public class FileCreator {


    FileCreator() {
        System.out.println("File Creator Initialized");
    }

    ///////////////////////////////////////////////////////////
    /*
    Creates a file in the "File Reference Directory" folder
    Writes the path name of the selected file to the created .txt
    Every time a new file is selected it adds a new path name
     */
    public void FileWriterAwesome(File myFile) throws IOException {
      /*
      Before the path name is written to the .txt file I want
      to check to see if the path name already exists in the .txt.
       */


        StringBuilder sb = new StringBuilder();
        sb.append(myFile.getAbsolutePath());
        File temp = new File("./File Reference Directory/File Reference.txt");
        FileWriter writer = new FileWriter(temp, true);
        writer.write(sb.toString() + "\n");
        writer.flush();
    }
    /////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////
    /*
    Reads the reference text
    Takes the path names line by line in the reference text and puts it into a String array
    Then it takes the path names and converts them into File references and places them into a file array
     */
    public ArrayList<File> ArrayListCreator() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("./File Reference Directory/File Reference.txt"));
        String str;

        List<String> list = new ArrayList<>();
        ArrayList<File> fileList = new ArrayList<>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        for (String x : list) {

            File file = new File(x);
            fileList.add(file);

        }
        return fileList;
    }
    /////////////////////////////////////////////////////////////////


    public ArrayList<String> FileToString(File file) throws IOException {
        int iterator = 0;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String string = wordSeparator(in.readLine());
        Scanner scanner = new Scanner(string);

        while (scanner.hasNextLine()) {
            arrayList.add(iterator, scanner.nextLine());
            iterator++;
        }


        return arrayList;
    }

    //This method takes in a string and changes it
    //so that there's only one word per a line
    public String wordSeparator(String str) {
        return str.replace(' ', '\n');
    }

}


