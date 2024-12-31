import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class of tacks which go in 8 directions and spread out on the map
 * @author Marcus Yeung
 * @author Hank Shi
 * @author Abdullah Qureshi
 * @version June 15 , 2023
 */
public class Tack extends Projectiles
{
    private int speed = 5; 
    protected boolean projectiled, canAttack;
    /**
     * Constructor of Tack.
     * @param int - the direction the tack faces
     */
    public Tack(int direction){
        projectiled = false;
        canAttack = true;
        // Faces Tack in correct direction
        if(direction==1){
            setRotation(0);
        }else if(direction==2){
            setRotation(45);
        }else if(direction==3){
            setRotation(90);
        }else if(direction==4){
            setRotation(135);
        }else if(direction==5){
            setRotation(180);
        }else if(direction==6){
            setRotation(225);
        }else if(direction==7){
            setRotation(270);
        }else if(direction==8){
            setRotation(315);
        }
    }

    public void act()
    {
        // Tack exits world
        if(this.isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        move(speed);
        findBalloon();

    }
    /**
     * Detects if Tack has hit a balloon
     */
    public void findBalloon(){
        Balloons intersectingBalloon = (Balloons)getOneIntersectingObject(Balloons.class);
        MyWorld w = (MyWorld)getWorld();
        // Balloon is found
        if(intersectingBalloon!=null){
            intersectingBalloon.decreaseHealth();
            // Pops Balloon
            if(intersectingBalloon.getHealth()<0){
                if(canAttack == true){
                    w.addMoney(intersectingBalloon.getCash());
                    projectiled = true;
                    canAttack = false;
                }
                if(projectiled == true && getIntersectingObjects(Balloons.class)==null){
                    canAttack = true;
                }
                intersectingBalloon.pop();
                getWorld().removeObject(this);
                return;
            // Changes color of Balloon except Cohen
            }else if(intersectingBalloon.getHealth()>0&&!intersectingBalloon.getBoss()){
                intersectingBalloon.setImage(intersectingBalloon.getBalloonsList()[(int)intersectingBalloon.getHealth()]);
                if(canAttack == true){
                    w.addMoney(intersectingBalloon.getCash());
                    projectiled = true;
                    canAttack = false;
                }
                if(projectiled == true && getIntersectingObjects(Balloons.class)==null){
                    canAttack = true;
                }
                getWorld().removeObject(this);
                return;
            }
        }

    }
}
