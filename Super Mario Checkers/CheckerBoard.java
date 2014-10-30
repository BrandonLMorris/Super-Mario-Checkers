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
        super(9, 9, 50); 
        addCheckers();
    }
    private void addCheckers(){
        addObject(new Checker(1), 0,0);
    }
}
