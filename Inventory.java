import java.util.HashMap;
import java.util.Set;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    private HashMap<String,Item> items;
    private int maximumWeight;
    private int totalWeight;
    /**
     * Creates an inventory
     */
    public Inventory()
    {
        items = new HashMap<>();
        maximumWeight = 20;
        totalWeight = 0;
    }

    /**
     * Add's an item to the inventory class.
     * @param itemString A string representation of the item object.
     * @param item The actual item object that is to be added to the inventory.
     */
    public void addItem(String itemString, Item item)
    {
        items.put(itemString,item);
        totalWeight += item.getWeight();
    }

    /**
     * Remove's an item from the inventory class.
     * @param itemString The string representation of the item to be removed.
     * @param item The actual item object that is to be removed from the inventory.
     */
    public void remove(String itemString,Item item)
    {
        items.remove(itemString,item);
        totalWeight -= item.getWeight();
    }

    /**
     * Return's a string of all the items in the inventory.
     * @return Details of the items in the inventory.
     */
    public String stringOfItems() 
    {
        String returnString = "Inventory:";
        Set<String> itemsInRoom = items.keySet();
        for(String item : itemsInRoom) {
            returnString += " " + item;
        }
        return returnString;
    }

    /**
     * Return's the number of items that are present in the inventory.
     * @return The number of items.
     */
    public int getSize()
    {
        return items.size();
    }

    /**
     * Check's whether the inventory contains a specified item.
     * @param The string representation of the item we are checking for.
     * @return A boolean response to the check.
     */
    public boolean contains(String item)
    {
        boolean containsItem = false;
        if(items.containsKey(item))
        {
            containsItem = true;
        }
        return containsItem;
    }

    /**
     * Displays the items that are present in the inventory as well as
     * the total weight of the items and the remaining space. 
     */
    public void showInventory()
    {
        if(items.isEmpty())
        {
            System.out.println("Currently not carrying anything!");
        }
        else
        {
            System.out.println(stringOfItems());
            System.out.println("Total Weight: "+totalWeight);

            int spaceLeft = maximumWeight - totalWeight;
            System.out.println("Remaining Space:" +spaceLeft);
        }
    }

    /**
     * Increases the maximum weight of items that can be carried by the inventory.
     */
    public void increaseCapacity()
    {
        maximumWeight = 35;
    }

    /**
     * Check's whether the inventory contains the item 'bag' if not
     * then it won't allow the user to carry items.
     * @param The item the user is trying to add to the inventory.
     * @return A boolean response to the check.
     */
    public boolean canPickUpItem(Item item)
    {
        boolean canPickUp = false;
        while(!items.containsKey("bag")){
            if(item.getName(item).equals("Bag")){
                canPickUp = true;
                System.out.println("Finally found my bag! Time to start the hunt!");
            }
            return canPickUp;
        }
        
        if(totalWeight + item.getWeight() <= maximumWeight){
            canPickUp = true;
        }
        return canPickUp;
    }
}
