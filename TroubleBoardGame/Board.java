import java.util.*;
import java.io.*;

public class Board extends ConsoleProgram
{
    //makes a board that creates both an arrayList of players and locations.
    char[] icons;
    ArrayList<Locations> spaces = new ArrayList<Locations>();
    ArrayList<Player> Players = new ArrayList<Player>();
    public Board()
    {
        for(int i = 0; i<28;i++)
        {
            spaces.add(new Locations(i));
        }
    }
    
    public ArrayList<Locations> getSpaces()
    {
        return spaces;
    }
    
    public Locations getLoc(int x)
    {
        return spaces.get(x);
    }
    
    //creates board
    public String toString()
    {
        String squirrel = "    AS";
        squirrel += "\n" + "     " + this.check(0) + this.check(1) + this.check(2) + this.check(3) + this.check(4) + this.check(5) + this.check(6) + "   ";
        squirrel += "\n" + "   " + this.check(27) + "                   " + this.check(7) + "BS";
        squirrel += "\n" + "   " + this.check(26) + "                   " + this.check(8);
        squirrel += "\n" + "   " + this.check(25) + "                   " + this.check(9);
        squirrel += "\n" + "   " + this.check(24) + "                   " + this.check(10);
        squirrel += "\n" + "   " + this.check(23) + "                   " + this.check(11);
        squirrel += "\n" + "   " + this.check(22) + "                   " + this.check(12);
        squirrel += "\n" + "DS " + this.check(21) + "                   " + this.check(13);
        squirrel += "\n" + "     " + this.check(20) + this.check(19) + this.check(18) + this.check(17) + this.check(16) + this.check(15) + this.check(14) + "   ";
        squirrel += "\n" +"                     CS";
        return squirrel;
    }
    
    //checks to see if the specific location is occupied or not.
    public String check(int index)
    {
        String result = "";
        if(!spaces.get(index).occupied())
        {
            result = "XX ";
        }
        else
        {
            String own = spaces.get(index).getOccupant();
            result = own + " ";
        }
        return result;
    }
    
    /* 
            AS
            X X X X X X X
        X                   X BS
        X                   X
        X                   X
        X                   X
        X                   X
        X                   X
     DS X                   X
            X X X X X X X
                        CS
    */
}