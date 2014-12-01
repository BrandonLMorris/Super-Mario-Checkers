import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class CheckerBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CheckerBoard extends World
{
    /**
     * Constructor for objects of class CheckerBoard.
     * 
     */


    //used to keep up with which turn it is
    public int playerTurn;

    //Constructs the board
    public CheckerBoard()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(9, 9, 50, true); 
        addCheckers();
        addTunnels();
        playerTurn = 1;
        setPaintOrder(Checker.class, Tunnel.class);
    }

    //Helper methods
    private void addCheckers(){
        addObject(new Checker(1), 0,1);
        addObject(new Checker(1), 2,1);
        addObject(new Checker(1), 4,1);
        addObject(new Checker(1), 6,1);
        addObject(new Checker(1), 8,1);
        addObject(new Checker(2), 0,7);
        addObject(new Checker(2), 2,7);
        addObject(new Checker(2), 4,7);
        addObject(new Checker(2), 6,7);
        addObject(new Checker(2), 8,7);
    }

    private void addTunnels(){
        addObject(new Tunnel(), 0,0);
        addObject(new Tunnel(), 1,0);
        addObject(new Tunnel(), 2,0);
        addObject(new Tunnel(), 3,0);
        addObject(new Tunnel(), 4,0);
        addObject(new Tunnel(), 5,0);
        addObject(new Tunnel(), 6,0);
        addObject(new Tunnel(), 7,0);
        addObject(new Tunnel(), 8,0);
        addObject(new Tunnel(), 0,8);
        addObject(new Tunnel(), 1,8);
        addObject(new Tunnel(), 2,8);
        addObject(new Tunnel(), 3,8);
        addObject(new Tunnel(), 4,8);
        addObject(new Tunnel(), 5,8);
        addObject(new Tunnel(), 6,8);
        addObject(new Tunnel(), 7,8);
        addObject(new Tunnel(), 8,8);
    }

    //setter and getter for turn variable
    public int playerTurn() {
        return playerTurn;
    }

    public void nextPlayersTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        int checkerCount1 = 0;
        int checkerCount2 = 0;
        
        //Count all checker on the board by team, if either is zero the game is over
        List<Checker> cList = getObjects(Checker.class);
        for(Checker c : cList) {
            if(c.team == 1){
                checkerCount1++;
            } else if(c.team == 2) {
                checkerCount2++;
            }
        }
        if(checkerCount1 == 0 || checkerCount2 == 0) {
            Greenfoot.playSound("game-complete.wav");
            Greenfoot.stop();
        }

        //Adds Mushroom after random number of turns to random location
        if (Greenfoot.getRandomNumber(100) < 10) {
            //x and y to add object to
            int randX = Greenfoot.getRandomNumber(9);
            int randY = Greenfoot.getRandomNumber(9)-1;
            //check that no other object is at x y, if there is generate new x and y
            //also checks that x y is a moveable space (odd numbers)
            List<Object> objs = getObjectsAt(randX, randY, Object.class);
            while((objs.size() != 0) || (randX % 2 != 0) || (randY % 2 == 0)) {
                randX = Greenfoot.getRandomNumber(9);
                randY = Greenfoot.getRandomNumber(9);
                objs = getObjectsAt(randX, randY, Object.class);
            }
            
            addObject (new Mushroom(), randX, randY);
        }

        //Adds Bomb after random number of turns to random location
        if (Greenfoot.getRandomNumber(100) < 10) {
            //x and y to add new object to
            int randX = Greenfoot.getRandomNumber(9);
            int randY = Greenfoot.getRandomNumber(8)+1;
            //check that no other object is at x y, if there is generate new x and y
            List<Object> objs = getObjectsAt(randX, randY, Object.class);
            while(objs.size() != 0 || (randX % 2 != 0) || (randY % 2 == 0)) {
                randX = Greenfoot.getRandomNumber(9 );
                randY = Greenfoot.getRandomNumber(8)+1;
                objs = getObjectsAt(randX, randY, Object.class);
            }
           
            addObject (new Bomb(), randX, randY);
        }
    }
}
