package bookstore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;

//Enumeration with all allowed publisher names
enum Publisher{ELSEVIER, SPRINGER, IEEE, TAYLORFRANCIS, WILEY, ACM}; 

public class DigitalLibrary {
    
   private ArrayList<Publication> publications = new ArrayList<>();
   
   
   //Loads the publications and sorts each either a journal or a proceeding which are also cited
   public void loadPublications() throws FileNotFoundException, IllegalArgumentException
    {
        
        File input = new  File("publications.txt"); 
        Scanner in = new Scanner(input);
        
        while(in.hasNext())
        {
            String[] field = in.nextLine().split(";");
            ArrayList<Author> authorList = new ArrayList();
            if(field.length == 9)
            {
                String[] authors = field[0].split(",");
                for(int i = 0; i < authors.length; i++)
                {
                    String[] name = authors[i].split(" ");
                    authorList.add(new Author(name[0],name[1]));
                }
                
                try{
                    Publisher.valueOf(field[3].toUpperCase());
                }
                catch (IllegalArgumentException e){
                throw e;
            }
                Publisher publish = Publisher.valueOf(field[3].toUpperCase());
                int start = Integer.parseInt(field[6]);
                int end = Integer.parseInt(field[7]);
                int year = Integer.parseInt(field[8]);
                int volume = Integer.parseInt(field[4]);
                int number = Integer.parseInt(field[5]);
                Publication j  = new Journal(authorList,publish,field[2],field[1],start,end,year,volume,number);
                publications.add(j);
            }
            
            if(field.length == 8)
            {
                String[] authors = field[0].split(",");
                for(int i = 0; i < authors.length; i++)
                {
                    String[] name = authors[i].split(" ");
                    authorList.add(new Author(name[0],name[1]));
                }
                Publisher publish = Publisher.valueOf(field[3].toUpperCase());
                int start = Integer.parseInt(field[5]);
                int end = Integer.parseInt(field[6]);
                int year = Integer.parseInt(field[7]);
                Publication p  = new Proceeding(authorList,publish,field[2],field[1],start,end,year,field[4]);
                publications.add(p);
            }
        }
    }
   
   
   //Lists the publications in order. 
   public void listPublications()
   {
       Collections.sort(publications);
       
       for(int i = 0; i < publications.size(); i++)
       {
           System.out.printf("[%d] %s\n",i+1,publications.get(i).Cite());
       }
	   
   }
   
   
   //Saves the citations to a file on the computer
   public void saveCitations(String fileName) throws FileNotFoundException
   {
       PrintWriter prw = new PrintWriter(fileName + ".txt");
       for(int i = 0; i < publications.size(); i++)
       {
           prw.printf("[%d] %s\n",i+1,publications.get(i).Cite());
       }
       System.out.println(publications.size() + " citations have been saved to the file " + fileName + ".txt");
       prw.close();
   }
   
   
   
   
}
