import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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

    //     public void act() 
    //     {
    //     //Add your action code here.
    //     }

    protected boolean isValidSpot(int xStart, int yStart, int xMoved, int yMoved) {
        List<Checker> cList = getWorld().getObjectsAt(xMoved, yMoved, Checker.class);
        if(cList.size() == 1) {
            //return true;

            for(int i = 0; i < 9; i++) {
                if((((xMoved == xStart + i) || (xMoved == xStart -i)) &&
                    ((yMoved == yStart + i) || (yMoved == yStart -i))) ||
                ((xMoved == xStart + i) && (yMoved == yStart)) ||
                ((xMoved == xStart - i) && (yMoved == yStart)) ||
                ((yMoved == yStart + i) && (xMoved == xStart)) ||
                ((yMoved == yStart - i) && (xMoved == xStart))) {
                    return true;
                }
            }            
        }
        return false;
    }

    @Override
    protected void jumpCheckers() {
        int xMoved = getX();
        int yMoved = getY();
        int xStart = startX;
        int yStart = startY;
        
        if(xMoved > xStart){
            //Piece moved right direction
            if(yMoved > yStart) {
                //Piece moved down and to the right
                for(int i = 1; i < (xMoved - xStart); i++){
                    removeCheckersAt(xStart+i, yStart+i);
                }
            } else if(yMoved < yStart) {
                //Piece moved up and to the right
                for(int i = 1; i < (xMoved - xStart); i++) {
                    removeCheckersAt(xStart+i, yStart-i);
                }
            } else if(yMoved == yStart) {
                //Piece moved right only
                for(int i = 1; i < (xMoved - xStart); i++) {
                    removeCheckersAt(xStart+i, yStart);
                }
            }
        } else if(xMoved < xStart) {
            //Piece moved left direction
            if(yMoved > yStart) {
                //Piece moved down and to the left
                for(int i = 1; i < (yMoved - yStart); i++) {
                    removeCheckersAt(xStart-i, yStart+i);
                }
            } else if(yMoved < yStart) {
                //Piece moved up and to the left
                for(int i = 1; i < (yStart - yMoved); i++) {
                    removeCheckersAt(xStart-i, yStart-i);
                }
            } else if(yMoved == yStart) {
                //Piece moved left only
                for(int i = 1; i < (xStart - xMoved); i++) {
                    removeCheckersAt(xStart - i, yStart);
                }
            }
        } else if(xMoved == xStart) {
            //Piece moved up or down
            if(yMoved > yStart) {
                //Piece moved down
                for(int i = 1; i < (yMoved - yStart); i++) {
                    removeCheckersAt(yStart+i, xStart);
                }
            } else if(yMoved < yStart) {
                //Piece Moved up
                for(int i = 1; i < (yStart - yMoved); i++) {
                    removeCheckersAt(yStart-i, xStart);
                }
            }
        }         
    }
    
    //removes checkers at given location that are on opposing team
    private void removeCheckersAt(int x, int y) {
        List<Checker> cList = getWorld().getObjectsAt(x, y, Checker.class);
        for(Checker c : cList) {
            if(c != null && c.team != this.team) {
                getWorld().removeObject(c);
                Greenfoot.playSound("coin-jump.wav");
            }
        }
    }
}