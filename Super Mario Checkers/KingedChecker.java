import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KingedChecker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KingedChecker extends Checker
{
    /**
     * Act - do whatever the KingedChecker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public KingedChecker(int team){
        super(team);
        if(team == 1) {
            setImage(new GreenfootImage("Crowned-Mario.png"));
        } else if(team == 2) {
            setImage(new GreenfootImage("Crowned-Wario.png"));
        }
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
