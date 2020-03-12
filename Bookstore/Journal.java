/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.util.ArrayList;

/**
 *
 * @author gooby
 */
public class Journal extends Publication implements Citable
{
    private int volume;
    private int number;
    
    //Constructor for the Journal class utilizing the publication class as a super
    public Journal(ArrayList<Author> authors, Publisher publisher, String venue, String title, int startingPage, int endingPage, int year, int volume, int number)
    {
        super(authors, publisher, venue, title, startingPage, endingPage, year);
        this.volume = volume;
        this.number = number;
    }
    
    //Overrides cite from publications. Uses super and adds on relavent information to the scientific citations
    @Override
    public String Cite()
    {
        return super.Cite() + String.format("%d(%d); %d-%d, %d", this.volume, this.number, this.startingPage, this.endingPage, this.year);
    }
    
}
