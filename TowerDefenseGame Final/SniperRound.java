import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class of SniperRound which removes a balloon when it has touched it
 * @author Marcus Yeung
 * @author Hank Shi
 * @author Abdullah Qureshi
 * @version June 15 , 2023
 */
public class SniperRound extends Projectiles
{
    private GreenfootImage sniperRound = new GreenfootImage("images/SniperRound.png");
    private GreenfootSound rifleShot;
    private int distance;
    private int direction;
    protected boolean projectiled, canAttack;
    public SniperRound(int direction)
    {
        //sniperRound.scale(sniperRound.getHeight(),sniperRound.getWidth());
        //setScale(getHeight()/2,getWidth()/2);
        projectiled = false;
        canAttack = true;
        this.distance = distance;
        this.direction = direction;
        setImage(sniperRound);
        getImage().scale(5,10);
        rifleShot = new GreenfootSound("sounds/rifle shot.wav");
        setRotation(direction);
    }

    public void act()
    {
        if(direction==2){
            setLocation(getX(),getY()-(int)(distance/2));
        }else if(direction==4){
            setLocation(getX(),getY()+(int)(distance/2));            
        }else if(direction==3){
            setLocation(getX()+(int)(distance/2),getY());            
        }else if(direction==1){
            setLocation(getX()-(int)(distance/2),getY());            
        }
        findBalloon();

    }

    public void findBalloon(){
        Balloons intersectingBalloon = (Balloons)getOneIntersectingObject(Balloons.class);
        MyWorld w = (MyWorld)getWorld();
        if(intersectingBalloon!=null){
            if(canAttack == true){
                w.addMoney(intersectingBalloon.getCash());
                projectiled = true;
                canAttack = false;
            }
            if(projectiled == true && getIntersectingObjects(Balloons.class)==null){
                canAttack = true;
            }
            intersectingBalloon.pop();
            rifleShot.play();
            w.removeObject(this);
            return;
        }
        move(40);
    }
}
