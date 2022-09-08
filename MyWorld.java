import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * MyWorld (Natural Selection Simulation.)
 * 
 */
public class MyWorld extends World
{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final double REPRODUCE_RATE = .005;
    private static final double HUNGER_LIMIT = 30;
    private static final double TREES_POPULATION = 30;
    

    private List<Double> deadCount = new ArrayList<Double>();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1); 
    }

    /**
     * The populate method populates the world with trees, and then asks the user
     * how many girrafes to add, and what height to make the giraffes. After adding
     * the group, they will be prompted to add another group or continue.
     */
    public void populate()
    {
        for (int i = 1; i <= 200; i++)
        {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            addObject(new Tree(23-(double)(Math.random()*6)), x, y);
        }
        Boolean isTrue = true;
        while(isTrue) {
            String answer = Greenfoot.ask("How many giraffes would you like to add?");
            int amount = Integer.parseInt(answer);
            String answer2 = Greenfoot.ask("What height do you want to set to these giraffes?");
            int height = Integer.parseInt(answer2);
            for (int i = 1; i <= amount; i++)
            {
                int x = (int)(Math.random() * getWidth());
                int y = (int)(Math.random() * getHeight());
                
                addObject(new Person(height), x, y);
            } 
            String answer3 = Greenfoot.ask("Add more giraffes?");
            if(!answer3.equalsIgnoreCase("yes"))
                isTrue = false;
        }
    }

    /**
     * The act method will gather all the giraffes in the world and put them in the List all.
     * This list will be passed to the processHunger method.  The displayCounts method will
     * then be called to update the status of all people in the world.
     */
    public void act()
    {
        List<Person> all = getObjects(Person.class);
        processHunger(all);
        processReproduction(all);
        displayCounts();
    }

    /**
     * This method is used to ensure that hunger is constantly updated for every animal, and it also ensures
     * that animals who have reached a certain limit are removed from the world.
     */
    
    private void processHunger(List<Person> all)
    {
        

        for (Person p : all)
        {
            if (p.getDaysHungry() >= HUNGER_LIMIT)
            {
                deadCount.add(p.getHeight());
                removeObject(p);
            }
            else
            {
                p.setDaysHungry(p.getDaysHungry() +1);
            }
        }
    }
    /** The processReproduction method causes giraffes that intersect
     * to have a probability of creating a new giraffe, with a height that is 
     * the average height of the two giraffes
     * 
     */

    private void processReproduction(List<Person> all) {
        for(Person giraffe : all) {
            if(Math.random()<REPRODUCE_RATE) {
                int x = (int)(Math.random() * getWidth());
                int y = (int)(Math.random() * getHeight());                    
                addObject(new Person(giraffe.getHeight()), x, y);
            }
        }
    }
    
    /**
     * Displays the counts of the alive and dead giraffes, and the average height of each group.
     */
    public void displayCounts()
    {
        List<Person> all = getObjects(Person.class);
        int left = all.size();
        int dead = deadCount.size();
        double avgAliveHeight =0;
        for(Person p : all) {
            avgAliveHeight += p.getHeight();
        }
        avgAliveHeight /= left;
        double avgDeadHeight = 0;
        for(double d : deadCount) {
            avgDeadHeight += d;
        }
        avgDeadHeight/=deadCount.size();
        showText("Alive: " + left, 80, 100);
        showText("Average Alive Height: " + avgAliveHeight, 80, 200);
        showText("Dead: " + dead, 80, 300);
        showText("Average Dead Height: " + avgDeadHeight, 80, 400);

    }
}



    /*private void processReproduction(List<Person> all) {
        
        for(Person giraffe : all) {
            ArrayList<Double> heights = giraffe.getNearbyGiraffes();
            for(Double i : heights) {
                if(Math.random() < REPRODUCE_RATE) {
                    int x = (int)(Math.random() * getWidth());
                    int y = (int)(Math.random() * getHeight());
                    double z = i + giraffe.getHeight();
                    
                    addObject(new Person(z/2), x, y);
                }
            }
            
            
        }
    }
    */
