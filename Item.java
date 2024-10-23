import java.util.HashSet;
import java.util.Set;

/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private String name;
    private String description;
    private int weight;
    /**
     * Creates an item that take in a name, description and a weight.
     * @param name The string name of the item
     * @param description The item's description.
     * @param weight The int weight value for an item.
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Print's the name, description and the weight for the item.
     */
    public void printItemDetails()
    {
        System.out.println("\nName: " + name);
        System.out.println("Description: " + description);
        System.out.println("Weight: " + weight);
    }

    /**
     * Return's the weight of the item
     * @return weight The int weight value for the item.
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Retrieves the name of the item from the item object.
     * @param item The item object whose string name you are trying to get.
     * @return name The string name of the item.
     */
    public String getName(Item item){
        return name;
    }
}