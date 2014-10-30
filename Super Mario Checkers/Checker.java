import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checker extends Actor
{
    /**
     * Act - do whatever the GreenChecker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //Class variable used to denote which team the checker is on
    public int team;

    public Checker(int team){
        this.team = team;
    }

    public void act() 
    {   
        //Enables the checker to respond to mouse drags
        //Only moves the checker to diagonal posisitons
        if(Greenfoot.mouseDragged(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if((mouse.getX() + mouse.getY()) % 2 == 0) {
                setLocation(mouse.getX(), mouse.getY());
            }
        }
        
        //gets performed when the user drops the checker on the board
        if(Greenfoot.mouseDragEnded(this)){
            Bomb b = (Bomb)getOneIntersectingObject(Bomb.class);
            if(b != null) {
                getWorld().removeObject(b);
                getWorld().removeObject(this);
            }
        }
    }
}
