//always try it once more if it says you can't sometimes its stupid even though it shouldn't say that.
//sometimes it breaks. Just reload it then it works fine.
import java.util.ArrayList;
public class MyProgram extends ConsoleProgram
{

    public void run()
    {
        Board trouble = new Board();
        System.out.println("WELCOME TO TROUBLE..."+ "\n" + "YOUR OBJECTIVE IS TO GET ALL YOUR PAWNS TO HOME(THE SPACE RIGHT BEFORE YOUR START SPACE)");
        System.out.println(trouble);
        makePlayers();
        while(!gameOver())
        {
            for(int i = 0; i<players.size();i++)
            {
                if(gameOver())
                {
                    break;
                }
                playerTurn(players.get(i),trouble);
                System.out.println("Pegs Out: " + players.get(i).checkOut().size());
                System.out.println("Pegs at home: " + players.get(i).safety().size());
                System.out.println(trouble);
            }
        }
        for(int i = 0; i<players.size();i++)
        {
            if(players.get(i).hasWon())
            {
                System.out.println("Player " + (players.get(i).getIndex()+1) + "has won");
            }
        }
    }
    
    public ArrayList<Player> players = new ArrayList<Player>();
    //creates the players for the game
    public ArrayList<Player> makePlayers()
    {
        for(int i = 0; i<4;i++)
        {
            Player p = new Player(i);
            players.add(p);
        }
        return players;
    }
    //checks for game over.
    public boolean gameOver()
    {
        for(int i = 0; i<players.size();i++)
        {
            if(players.get(i).hasWon())
            {
                return true;
            }
        }
        return false;
    }
    //player turn
    public void playerTurn(Player p, Board b)
    {
        String go = readLine("Player " + (p.getIndex()+1) + " turn. press enter to continue");
        int check = -1;
        boolean choosen = false;
        int roll = p.rollDice();
        //int roll = readInt("Test");
        String pick = "";
        int peggie;
        int choose;
        System.out.println("You rolled a " + roll);
        //if the player rolls anything below a 6 and they already have a peg out they will be prompted to choose what peg they want to move.
        if(roll < 6 && p.checkOut().size()>0)
        {
            pick = readLine("Would you like to move a peg that is out of base? Enter Y ");
            while(!pick.equalsIgnoreCase("y"))
            {
                pick = readLine("Don't be stupid... Just enter Y ");
            }
            if(pick.equalsIgnoreCase("y"))
            {
                choose = readInt("Which peg would you like to move" + p.pegsOutOfBase());
                //a loop that will end when the peg chosen can be moved.
                while(!(p.choosePeg(choose).getSpot() > -1) || p.pegAtHome(choose))
                {
                    System.out.println(p.choosePeg(choose).getSpot());
                    choose = readInt("You cannot move that peg. Choose another.");
                }
                //while the peg hasn't moved yet it will loop.
                while(!choosen)
                {
                    int original = p.choosePeg(choose).getSpot();
                    if(original > 27)
                    {
                        original -=27;
                    }
                    int move = roll + original;
                    if(move > 27)
                    {
                        move = move - 27;
                    }
                    if(b.getLoc(move).occupied)
                    {
                        String own = b.getLoc(move).getOccupant();
                        for (int i = 0; i < players.size();i++)
                        {
                            //moving code for peg if there is a peg on the space that is not owned by the player.
                            check = players.get(i).iconToIndex(players.get(i).getIcon());
                            if(!(check == -1)&& !(check == p.getIndex()))
                            {
                                choosen = true;
                                for(int j = 0; j<players.get(check).getPawns().size();j++)
                                {
                                    if(players.get(check).getPawns().get(j).getSpot() == (move))
                                    {
                                        players.get(check).getPawns().get(j).ger();
                                        players.get(check).checkOut().remove(j);
                                        p.choosePeg(choose).changeSpot(roll);
                                        if(p.pegAtHome(choose))
                                        {
                                            p.choosePeg(choose).setSpot(p.safe);
                                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                        }
                                        else
                                        {   
                                            p.choosePeg(choose).takeSpace(b.getLoc(move));
                                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                        }
                                    }
                                }
                            }
                            if(check == p.getIndex())
                            {
                                choose = readInt("You cannot move that peg. Choose another.");
                            }
                        }
                    }
                    //movement of peg if there is no other peg on the location
                    if(!b.getLoc(move).occupied)
                    {
                        choosen = true;
                        p.choosePeg(choose).changeSpot(roll);
                        if(p.pegAtHome(choose))
                        {
                            p.choosePeg(choose).setSpot(p.safe);
                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                        }
                        else
                        {
                            p.choosePeg(choose).takeSpace(b.getLoc(move));
                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                        }
                    }
                }
            }
        }
        //if a 6 is rolled
        if(roll == 6)
        {
            // asks player if they would like to move a peg out of base or move a peg that is outside of base
            pick = readLine("would you like to move a peg out of base or move a peg outside? Base/Move");
            while(!pick.equalsIgnoreCase("Move") && !pick.equalsIgnoreCase("Base"))
            {
                pick = readLine("PLEASE DON'T BE LIKE THIS. PICK BASE OR MOVE IF YOU CAN MOVE. ");
            }
            if(pick.equalsIgnoreCase("Move") && !(p.checkOut().size()> 0))
            {
                pick = readLine("Seems like you don't have any pegs out of base. Looks like you gotta move one out...just type Base");
            }
            if(pick.equalsIgnoreCase("Move") && (p.checkOut().size()>0))
            {
                choose = readInt("Which peg would you like to move" + p.pegsOutOfBase());
                while(!(p.choosePeg(choose).getSpot() > -1) || p.pegAtHome(choose))
                {
                    System.out.println(p.choosePeg(choose).getSpot());
                    choose = readInt("You cannot move that peg. Choose another.");
                }
                while(!choosen)
                {
                    int original = p.choosePeg(choose).getSpot();
                    if(original>27)
                    {
                        original -=27;
                    }
                    int move = original + roll;
                    if((move)>27)
                    {
                        move = move - 27;
                    }
                    if(b.getLoc(move).occupied)
                    {
                        String own = b.getLoc(move).getOccupant();
                        for (int i = 0; i < players.size();i++)
                        {
                            //moving code for peg if there is a peg on the space that is not owned by the player.
                            check = players.get(i).iconToIndex(players.get(i).getIcon());
                            if(!(check == -1)&& !(check == p.getIndex()))
                            {
                                choosen = true;
                                for(int j = 0; j<players.get(check).getPawns().size();j++)
                                {
                                    if(players.get(check).getPawns().get(j).getSpot() == (move))
                                    {
                                        players.get(check).getPawns().get(j).ger();
                                        players.get(check).checkOut().remove(j);
                                        p.choosePeg(choose).changeSpot(roll);
                                        if(p.pegAtHome(choose))
                                        {
                                            p.choosePeg(choose).setSpot(p.safe);
                                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                        }
                                        else
                                        {
                                            p.choosePeg(choose).takeSpace(b.getLoc(move));
                                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                        }
                                    }
                                }
                            }
                            if(check == p.getIndex())
                            {
                                choose = readInt("You cannot move that peg. Choose another.");
                            }
                        }
                    }
                    if(!b.getLoc(move).occupied)
                    {
                        choosen = true;
                        p.choosePeg(choose).changeSpot(roll);
                        if(p.pegAtHome(choose))
                        {
                            p.choosePeg(choose).setSpot(p.safe);
                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                        }
                        else
                        {
                            p.choosePeg(choose).takeSpace(b.getLoc(move));
                            p.choosePeg(choose).leaveSpace(b.getLoc(original));
                        }
                    }
                    System.out.println(b);
                }
            }    
            //asks which peg they would like to move
            if(pick.equalsIgnoreCase("Base"))
            {
                peggie = readInt("Which peg would you like to move? " + p.pegsInBase());
                p.leaveBase(peggie);
                p.choosePeg(peggie).takeSpace(b.getLoc(p.getBegin()));
            }
            go = readLine("roll again. Press enter");
            roll = p.rollDice();
            //roll = readInt("TEST");
            System.out.println("You rolled a " + roll);
            choose = readInt("Choose which peg to move" + p.pegsOutOfBase());
            choosen = false;
            while(p.choosePeg(choose).getSpot() == -1 || p.pegAtHome(choose))
            {
                choose = readInt("You cannot move that peg. Choose another.");
            }
            //this all a copy of the previous movement of pegs. Will try to make a method out of this later to simplify.
            while(!choosen)
            {
                int original = p.choosePeg(choose).getSpot();
                if(original>27)
                {
                    original -= 27;
                }
                System.out.println(original);
                int move = original + roll;
                if((move)>27)
                {
                    move = move - 27;
                }
                if(b.getLoc(move).occupied)
                {
                    String own = b.getLoc(move).getOccupant();
                    for (int i = 0; i < players.size();i++)
                    {
                        check = players.get(i).iconToIndex(players.get(i).getIcon());
                        if(!(check == -1)&& !(check == p.getIndex()))
                        {
                            choosen = true;
                            for(int j = 0; j<players.get(check).getPawns().size();j++)
                            {
                                if(players.get(check).getPawns().get(j).getSpot() == (move))
                                {
                                    players.get(check).getPawns().get(j).ger();
                                    players.get(check).checkOut().remove(j);
                                    p.choosePeg(choose).changeSpot(roll);
                                    if(p.pegAtHome(choose))
                                    {
                                        p.choosePeg(choose).setSpot(p.safe);
                                        p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                    }
                                    else
                                    {
                                        p.choosePeg(choose).takeSpace(b.getLoc(move));
                                        p.choosePeg(choose).leaveSpace(b.getLoc(original));
                                    }
                                }
                            }
                        }
                        if(check == p.getIndex())
                        {
                            choose = readInt("You cannot move that peg. Choose another.");
                        }
                    }
                }
                if(!b.getLoc(move).occupied)
                {
                    choosen = true;
                    p.choosePeg(choose).changeSpot(roll);
                    if(p.pegAtHome(choose))
                    {
                        p.choosePeg(choose).setSpot(p.safe);
                        p.choosePeg(choose).leaveSpace(b.getLoc(original));
                    }
                    else
                    {
                        p.choosePeg(choose).takeSpace(b.getLoc(move));
                        p.choosePeg(choose).leaveSpace(b.getLoc(original));
                    }
                }
            }
        }
    }
}
