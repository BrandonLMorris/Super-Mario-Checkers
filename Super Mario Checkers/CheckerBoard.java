import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public CheckerBoard()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(9, 9, 50, true); 
        addCheckers();
        addTunnel();
    }
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
    private void addTunnel(){
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
}
