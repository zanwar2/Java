package bookstore;

import java.util.ArrayList;

public class Proceeding extends Publication implements Citable
{
    private String city;
    
    //Constructor for the proceeding class utilizing the publication class as a super.
    public Proceeding(ArrayList<Author> authors, Publisher publisher, String venue, String title, int startingPage, int endingPage, int year, String city)
    {
        super(authors, publisher, venue, title, startingPage, endingPage, year);
        this.city = city;
    }
    
    //Overrides cite from publications. Uses super and adds on relavent information to the scientific citations
    @Override
    public String Cite()
    {
        return super.Cite() + String.format("%s, %d, pp:%d-%d", this.city,this.year,this.startingPage,this.endingPage);
    }
}
