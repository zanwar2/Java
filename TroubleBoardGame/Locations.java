import java.util.ArrayList;
public class Locations
{
    int place;
    boolean occupied;
    String occupant;
    //constructor for locations. Gives it a value and starts off unoccupied.
    public Locations(int place)
    {
        this.place = place;
        occupied = false;
        this.occupant = "";
    }
    
    public void setOccupied()
    {
        occupied = true;
    }
    
    public String getOccupant()
    {
        return this.occupant;
    }
    
    public void unoccupied()
    {
        occupied = false;
    }
    
    public boolean occupied()
    {
        return occupied;
    }
    
    public void setOccupant(String x)
    {
        occupant = x;
    }
    
    public void remove()
    {
        occupant = "";
    }
}