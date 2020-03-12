import java.util.ArrayList;

public class Player
{
    char icon = ' ';
    ArrayList<Pegs> pawns = new ArrayList<Pegs>();
    ArrayList<Pegs> outOfBase = new ArrayList<Pegs>();
    ArrayList<Pegs> safePegs = new ArrayList<Pegs>();
    char[] letters = {'A','B','C','D'};
    int[] start = {0,7,14,21};
    int[] end = {27,6,13,20};
    int[]homeSpace = {30,31,32,33};
    int home;
    int begin;
    int index;
    int safe;
    //player constructor. Runs a for loop creating an arraylist of Pegs for each player created.
    public Player(int index)
    {
        this.index = index;
        this.icon = letters[index];
        this.begin = start[index];
        this.home = end[index];
        this.safe = homeSpace[index];
        for(int i = 0; i<4; i++)
        {
            pawns.add(new Pegs(icon,i));
        }
    }
    
    public int getBegin()
    {
        return this.begin;
    }
    
    //creates an arraylist of the pegs outside of base
    public ArrayList<Pegs> checkOut()
    {
        return this.outOfBase;
    }
    
    public ArrayList<Pegs> safety()
    {
        return this.safePegs;
    }
    
    //creates a string of the pegs outside of base
    public String pegsOutOfBase()
    {
        String outtie = "";
        for(int i = 0; i<this.getPawns().size();i++)
        {
            if(!(this.getPawns().get(i).getSpot() == -1) && !(this.getPawns().get(i).getSpot() == this.safe))
            {
                outtie += "Peg " + Integer.toString(i) + ", ";
            }
        }
        return outtie;
    }
    
    public String pegsInBase()
    {
        String innie = "";
        for(int i = 0; i<this.getPawns().size();i++)
        {
            if(this.getPawns().get(i).getSpot() == -1)
            {
                innie += "Peg " + Integer.toString(i) + ", ";
            }
        }
        return innie;
    }
    
    //checks to see if the peg is at home spot or not
    public boolean pegAtHome(int x)
    {
        if(this.choosePeg(x).getSpot() == this.home)
        {
            this.choosePeg(x).setSpot(this.safe);
            safePegs.add(this.choosePeg(x));
            return true;
        }
        return false;
    }
    
    public boolean pegAtSafe(int x)
    {
        if(this.choosePeg(x).getSpot() == this.safe)
        {
            return true;
        }
        return false;
    }
    
    
    public int getIndex()
    {
        return this.index;
    }
    
    //gets the icon of the player.
    public char getIcon()
    {
        return this.icon;
    }
    
    //gets the whole list of pawns for the specific player.
    public ArrayList<Pegs> getPawns()
    {
        return pawns;
    }
    
    // gets the icon of a peg that is put as the parameter and checks to see if it is the same as the icon of the player the method is called on.
    public int iconToIndex(char icon)
    {
        int result = -1;
        if(this.getIcon() == icon)
        {
            result = this.getIndex();
        }
        return result;
    }
    
    // a method that lets the player choose what peg he wants to move
    public Pegs choosePeg(int i)
    {
        return this.getPawns().get(i);
    }
    
    public void movePeg(int x,Pegs p)
    {
            this.rollDice();
            if(p.getSpot() > -1 || p.getSpot() == this.safe)
            {
                p.changeSpot(roll);
            }
    }
    
    public void leaveBase(int x)
    {
        this.getPawns().get(x).setSpot(this.begin);
        outOfBase.add(this.getPawns().get(x));
    }
    
    public boolean hasWon()
    {
        if(safePegs.size() == 4)
        {
            return true;
        }
        return false;
    }

    //has a player roll dice.
    int roll = 0;
    public int rollDice()
    {
        roll = Randomizer.nextInt(1,6);
        return roll;
    }
}
