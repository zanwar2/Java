
package bookstore;

import java.util.ArrayList;
import java.util.Collections;


public class Publication implements Citable, Comparable<Publication>
{
    private ArrayList<Author> authors;
    private Publisher publisher;
    private String venue;
    private String title;
    protected int startingPage;
    protected int endingPage;
    protected int year;
    
    //Constructor for the publication class
    public Publication(ArrayList<Author> authors, Publisher publisher, String venue, String title, int startingPage, int endingPage, int year)
    {
        this.authors = authors;
        this.publisher = publisher;
        this.venue = venue;
        this.title = title;
        this.startingPage = startingPage;
        this.endingPage = endingPage;
        this.year = year;
    }
    
    //Comparing method to sort by the last name of the author, then compares venues, then compares years. The venues and authors are compared alphabetically and year numerically.
    @Override
    public int compareTo(Publication other)
    {
        if(this.authors.get(0).compareTo(other.authors.get(0)) != 0)
        {
            return this.authors.get(0).compareTo(other.authors.get(0));
        }
        else if(this.venue.compareTo(other.venue) != 0)
        {
            return this.venue.compareTo(other.venue);
        }
        else
        {
            return Integer.compare(this.year,other.year);
        }
    }
    
    //Created the acronym for the venues of the publication
    public String acronym()
    {
        String temp = "" + venue.charAt(0);
        for(int i = 1; i < venue.length()-1; i++)
        {
            if(venue.charAt(i) == ' ')
            {
                temp += venue.charAt(i+1);
            }
        }
        return temp.toUpperCase();
    }
    
    //Shortened the author name by only providing the intial of the first name and the full last name
    public String authorNames()
    {
        String temp = authors.get(0).firstName.charAt(0)+ ". " + authors.get(0).lastName;
        int numAuthors = authors.size();
        switch(numAuthors)
        {
            case 1: //only 1 author
            {
                return temp;
            }
            case 2: //only 2 authors
            {
                temp += " and " + authors.get(1).firstName.charAt(0)+ ". " + authors.get(1).lastName; break;
            }
            default: //more than 2 authors
            {
                for(int i = 1; i<authors.size() - 1; i++)
                {
                    temp += ", " + authors.get(i).firstName.charAt(0)+". " + authors.get(i).lastName;
                }
                temp += ", and " + authors.get(authors.size()-1).firstName.charAt(0)+". " + authors.get(authors.size()-1).lastName; break;
            }
        }
        return temp;
    }
    
    
    //creates a citation method specific for publications to be put into scientific form
    @Override
    public String Cite()
    {
        Collections.sort(authors);
        
        return String.format(" %s, \"%s\", %s(%s), %s, ", this.authorNames(), this.title, this.venue, this.acronym(), this.publisher);
    }

}
