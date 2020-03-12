import java.util.ArrayList;
public class Pegs
{
    char icon = ' ';
    int spot;
    String name = "";
    //Constructor for each peg
    public Pegs(char icon,int index)
    {
        this.icon = icon;
        this.spot = -1;
        this.name = icon + Integer.toString(index);
    }
    
    //changes where the peg is by adding a value to spot which is a psuedo location
    public int changeSpot(int x)
    {
        if(this.spot == 27)
        {
            this.spot = 0;
            this.spot += (x-1);
        }
        else
        {
            this.spot += x;
        }
        return spot;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int setSpot(int x)
    {
        this.spot = x;
        return spot;
    }
    //gives the current spot
    public int getSpot()
    {
        return spot;
    }
    //method used to set pegs location back to home when landed on by enemy peg
    public void ger()
    {
        spot = -1;
    }
    //returns icon which is essentially the owner.
    public char getIcon()
    {
        return this.icon;
    }
    
    public void takeSpace(Locations l)
    {
        l.setOccupied();
        l.setOccupant(this.getName());
    }
    
    public void leaveSpace(Locations l)
    {
        l.unoccupied();
        l.remove();
    }
    
    
}    
