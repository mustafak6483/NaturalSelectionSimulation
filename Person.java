import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Person class (Extends actor class)
 * 
 */
public class Person extends Actor
{   
    public static final int NORMAL = 10;
    private double height;
    private int daysHungry;
    
    public Person()
    {
        this(NORMAL);
    }

    /**
     * overloads Person Constructor so you can create a giraffe 
     * with or without specifying height
     */
    public Person(double mHeight)
    {
        daysHungry = 0;
        height = mHeight;
        setRotation(randomDirection()); 
        setImage("giraffe-icon.jpg");
    }


    public double getHeight()
    {
        return height;
    }
    public void setHeight(double mHeight)
    {
        height = mHeight;
    }

    public int getDaysHungry()
    {
        return daysHungry;
    }
    public void setDaysHungry(int days)
    {
        daysHungry = days;
    }
    /*
     * Retrieves the nearby giraffes and creates a list of them
     * to enable reproduction in Myworld
     */
    public ArrayList<Double> getNearbyGiraffes() {
        ArrayList<Double> heights = new ArrayList<Double>();
        List<Person> giraffesNearby = getIntersectingObjects(Person.class);
        for (Person giraffe : giraffesNearby) {
            heights.add(giraffe.getHeight());
        }
        return heights;
    }
    /**
     * returns a random direction angle in the range of 0 to 359 degrees
     */
    private int randomDirection()
    {
        return (int)(Math.random() * 360);
    }

    /**
     * Act - A Giraffe moves and makes random turns and also turns at the edge of the world.
     * Giraffes that overlap can reproduce. 
     * 
     * This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(3);
        if (Math.random() < .01)
        {
            randomTurn(45);            
        }
        if (isAtEdge())
        {
            randomTurn(45);
        }
        List<Tree> treesNearby = getIntersectingObjects(Tree.class);
        for (Tree tree : treesNearby) {
            if(tree.getHeight()>height-4&&tree.getHeight()<height+4) {
                daysHungry = 0;
           }
        }
        
    } 
    

    /**
     * turns this Person some degrees in the range of -degrees to +degrees
     */
    private void randomTurn(int degrees)
    {
        int angle = (int)(Math.random() * (2 * degrees + 1)) - degrees;
        turn(angle);       
    }

    
}

