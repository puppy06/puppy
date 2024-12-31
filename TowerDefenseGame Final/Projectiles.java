import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass of SniperRound, Tack and Bomb which interacts with Balloons when intersecting with them
 *
 * @author Hank Shi
 * @version June 15 , 2023
 */
public abstract class Projectiles extends Actor
{
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int damage;
    protected int projectileSpeed;
    public void act()
    {
        move(projectileSpeed);
        if (isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        if (getX()>300){
            getWorld().removeObject(this);
            return;
        }

        Balloons b = (Balloons)getOneIntersectingObject(Balloons.class);
        if (b != null){
            b.hitEnemy(damage);
            getWorld().removeObject(this);
            return;

        }
    }
    
    public void rotate(int i){
        setRotation(i);
    }
}
