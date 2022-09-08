import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Tree Class, used to create new Tree objects
 * 
 */
public class Tree extends Actor
{   
    private double height;

    public Tree()
    {
        height = 5;
    }

    /**
     * Overloads Constructor so object can be constructed with 
     * or without specifying height
     */
    public Tree(double mHeight)
    {
        height = mHeight;
        setRotation(randomDirection()); 
        setImage("tree-icon.png");
    }

    public double getHeight() {
        return height;
    }
       

    /**
     * returns a random direction angle in the range of 0 to 359 degrees
     */
    private int randomDirection()
    {
        return (int)(Math.random() * 360);
    }
    
    
 

    /**
     * turns this Person some degrees in the range of -degrees to +degrees
     */
    //private void randomTurn(int degrees)
    //{
    //    int angle = (int)(Math.random() * (2 * degrees + 1)) - degrees;
    //    turn(angle);       
    //}

    /*
     * List<Person> giraffesNearby = getIntersectingObjects(Person.class);
        for (Person giraffe : giraffesNearby) {
            if(giraffe.getHeight()>height-5&&giraffe.getHeight()<height+4) {
                giraffe.setDaysHungry(0);
           }
        }
     */
    
}

