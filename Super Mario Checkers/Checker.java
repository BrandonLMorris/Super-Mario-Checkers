import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    protected int startX;
    protected int startY;
    CheckerBoard cb = (CheckerBoard) getWorld();

    
    public Checker(int team){
        this.team = team;
        if(team == 1) {
            setImage(new GreenfootImage("Mario.png"));
        } else if (team == 2) {
            setImage(new GreenfootImage("Wario.png"));
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
            if(isValidSpot(startX, startY, getX(), getY()) && ((CheckerBoard)getWorld()).playerTurn() == this.team){
                //Eat players jumped over
                jumpCheckers();
                ((CheckerBoard)getWorld()).nextPlayersTurn();

            } else {
                setLocation(startX, startY);
            }

            List<Object> objects = getIntersectingObjects(Object.class); 
            for(Object o : objects) {
                if(o instanceof Bomb) {
                    getWorld().removeObject((Actor)o);
                    getWorld().removeObject(this);
                    break;
                } else if(o instanceof Mushroom) {
                    o = (Mushroom)o;
                    ((Mushroom)o).makeKing(this);
                    break;
                } else if(o instanceof Tunnel) {
                    o = (Tunnel)o;
                    ((Tunnel)o).transport(this);
                    ((Tunnel)o).makeKing(this);
                    break;
                }
            }
        }
    }

    //Helper method that checks the validity of a position relative to the checker's start position
    protected boolean isValidSpot(int xStart, int yStart, int xMoved, int yMoved) {
        //iterated through all possible x and y positions for diagonal movement, returns true if
        for(int i = 0; i < 9; i++) {
            if(((xMoved == xStart + i) || (xMoved == xStart - i)) &&
            ((yMoved == yStart + i) || (yMoved == yStart - i))) {
                //Check that no other checkers are on that space
                List<Checker> cList = getWorld().getObjectsAt(xMoved, yMoved, Checker.class);
                if(cList.size() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //returns the direction of that the checker moved
    // 1 -> Northeast
    // 2 -> Northwest
    // 3 -> Southwest
    // 4 -> Southeast
    private int getDirection() {
        if(startY > getY()) {   //true if checker moved up the board
            if(startX < getX()) {   //true if checker moved right
                return 1;
            } else {
                return 2;
            }
        } else {
            if(startX > getX()) {
                return 3;
            } else {
                return 4;
            }
        }
    }

    protected void jumpCheckers() {
        int direction = getDirection();
        if (direction == 1) {
            int xDist = getX() - startX;
            for(int i = 1; i < xDist; i++) {
                List<Checker> cList = getWorld().getObjectsAt(startX+i, startY-i, Checker.class);
                for( Checker c : cList){
                    if(c != null && c.team != this.team) {
                        getWorld().removeObject(c);
                        Greenfoot.playSound("coin-jump.wav");
                    }
                }
            }
        } else if(direction == 2) {
            int xDist = startX - getX();
            for(int i = 1; i < xDist; i++) {
                List<Checker> cList = getWorld().getObjectsAt(startX-i, startY-i, Checker.class);
                for(Checker c : cList){    
                    if(c != null && c.team != this.team) {
                        getWorld().removeObject(c);
                        Greenfoot.playSound("coin-jump.wav");
                    }
                }
            }
        } else if(direction == 3) {
            int xDist = startX - getX();
            for(int i = 1; i < xDist; i++) {
                List<Checker> cList = getWorld().getObjectsAt(startX-i, startY+i, Checker.class);
                for(Checker c : cList) {
                    if(c != null && c.team != this.team) {
                        getWorld().removeObject(c);
                        Greenfoot.playSound("coin-jump.wav");
                    }
                }
            }
        } else if(direction == 4) {
            int xDist = getX() - startX;
            for(int i = 1; i < xDist; i++) {
                List<Checker> cList = getWorld().getObjectsAt(startX+i, startY+i, Checker.class);
                for(Checker c : cList) {    
                    if(c != null && c.team != this.team) {
                        getWorld().removeObject(c);                        
                        Greenfoot.playSound("coin-jump.wav");
                    }
                }
            }
        }
    }
}
