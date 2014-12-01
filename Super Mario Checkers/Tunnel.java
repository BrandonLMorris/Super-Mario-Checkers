import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tunnel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tunnel extends Actor
{
    /**
     * Act - do whatever the Tunnel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }

    public void makeKing(Checker c) {
        if((this.getY() == 0 && c.team == 2) || (this.getY() == 8 && c.team == 1)){
            getWorld().addObject(new KingedChecker(c.team), c.getX(), c.getY());
            getWorld().removeObject(c);
        }
    }

    public void transport(Checker c) {
        if(this.getY() == 0 && c.team == 2) {
            c.setLocation(this.getX(), 7);
            Greenfoot.playSound("tunnel.wav");
        } else if(this.getY() == 8 && c.team == 1) {
            c.setLocation(this.getX(), 1);
            Greenfoot.playSound("tunnel.wav");
        }
    }
}
