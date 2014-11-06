import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checker extends Actor
{

    //Class variable used to denote which team the checker is on
    public int team;
    private int startX;
    private int startY;

    public Checker(int team){
        this.team = team;
        if(team == 1) {
            setImage(new GreenfootImage("button-red.png"));
        } else if (team == 2) {
            setImage(new GreenfootImage("button-green.png"));
        }
    }

    public void act() 
    {   
        if(Greenfoot.mousePressed(this)) {
            startX = getX();
            startY = getY();
        }
        //Enables the checker to respond to mouse drags
        //Only moves the checker to diagonal posisitons
        if(Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
        
        //gets performed when the user drops the checker on the board
        if(Greenfoot.mouseDragEnded(this)){
            if(!isValidSpot(startX, startY, getX(), getY())){
                setLocation(startX, startY);
            }
            Bomb b = (Bomb)getOneIntersectingObject(Bomb.class);
            if(b != null) {
                getWorld().removeObject(b);
                getWorld().removeObject(this);
            }
        }
    }
    
    //Helper method that checks the validity of a position relative to the checker's start position
    private boolean isValidSpot(int xStart, int yStart, int xMoved, int yMoved) {
        //iterated through all possible x and y positions for diagonal movement, returns true if
        for(int i = 0; i < 9; i++) {
            if(((xMoved == xStart + i) || (xMoved == xStart - i)) &&
               ((yMoved == yStart + i) || (yMoved == yStart - i))) {
                   return true;
                }
        }
        return false;
    }
}
