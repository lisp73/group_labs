package project2;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class File2 {
    public static void main(String[] args) throws IOException {
List<String> mylst = new ArrayList<String>();
/*mylst.add("the");
mylst.add("training");
mylst.add("online");*/
        File file = new File("test.html");
        
        //input line is going to be the var that holds the marked values after the screening.
        String inputLine;
        
        //Wordinput is going to receive the data from the text file buffered by word reader
        String wordinput;
        
        //this is the website we used to load and check the words in the html
       // URL newUrl = new URL("http://www.tutorialspoint.com/index.htm");
        URL newUrl = new URL("http://www.lordlikely.com");
        //We use newURL to open a stream so that it can be ready to load
        BufferedReader in = new BufferedReader(new InputStreamReader(
                newUrl.openStream()));
        //wordreader is the var that eventually holds the buffered text file "words"
        //Step 1 newFileInputStream: obtains input bytes from a file in a file system.
        //FileInputStream is meant for reading streams of raw bytes such as image data
        //Why can't I use File Reader? I thought this would be more effecient.
        BufferedReader wordreader = new BufferedReader(new InputStreamReader(new FileInputStream("words")));

        try {
        	
        	//What is br doing? 
        	//1) FileWriter here is used to for it's list of helper methods later on. 
        	//2) BufferedWriter here is used to improves efficiency because 
        	//it breaks the file into small is in multiple small writes.
        	//With a BufferedWriter, these can all be buffered together and as the default buffer size is 8192 
        	//characters this become just 1 system call to write.
        	//So why did I use BufferedWriter? Two reasons.
        	//1) the writes are small compared with the buffer size.
        	//2) I have multiple writes between flush/close as I change the html tags for the highlighting 
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            
            
            //in this while loop I assign the list of words to wordinput then I 
            //add it to an ArrayList called mylst for later screening on the outputed html file. 
            while((wordinput =wordreader.readLine())!=null){
            	mylst.add(wordinput);
            	
            }
            
            
            //as Mentioned above, The inputLine is used here to replace the marked string
            //with highlights chosen from the ArrayList of words. 
            while ((inputLine = in.readLine()) != null) {
            	for(String string : mylst){
                inputLine = inputLine.replace(string, "<mark>"+string+"</mark>");
            	}
            	//here I finally write the changes I made above into my test.html
                br.write(inputLine);
            }
            //If Any files cannot be read during runtime, an error will be thrown. 
        } catch (IOException e) {
            System.out.println("Unable to read file" + file.toString());
        }
        //This step was very important, if I didn't close it, the file will not be overwritten 
        //to the file. This was causeing a bug that took me over an hour to find out what 
        //was happening. 
        in.close();

    }

}
