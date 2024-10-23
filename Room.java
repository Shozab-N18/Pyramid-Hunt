import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private HashMap<String,Item> itemsInRoom;
    private HashSet<Character> charactersInRoom;
    private boolean isTransporter;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * Takes into consideration whether the room is a teleporter
     * type or not.
     * @param description The room's description.
     * @param isTransporter The room type.
     */
    public Room(String description, boolean isTransporter) 
    {
        this.description = description;
        this.isTransporter = isTransporter;
        exits = new HashMap<>();
        itemsInRoom = new HashMap<>();
        charactersInRoom = new HashSet<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are in " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "\nExits:";        
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Return a randomly generated room exit from all the possible exit points.
     * @return Random room exit.
     */
    public Room getRandomExit()
    {
        ArrayList<Room> possibleExits = new ArrayList<>();
        Random randomInt = new Random();

        Set<String> directions = exits.keySet();

        for(String direction : directions){
            possibleExits.add(getExit(direction));
        }

        int numberOfExits = possibleExits.size();
        return possibleExits.get(randomInt.nextInt(numberOfExits));
    }

    /**
     * Add's an item to the room.
     * @param itemString The string representation of the item.
     * @param item The actual item object.
     */
    public void addItem(String itemString,Item item)
    {
        itemsInRoom.put(itemString,item);
    }

    /**
     * Print's a list of items in the current room.
     */
    public void getItemsInRoom(){
        if(!itemsInRoom.isEmpty()){
            String returnString = "Items:";        
            Set<String> itemsInRoomString = itemsInRoom.keySet();
            for(String item : itemsInRoomString) {
                returnString += " " + item;
            }
            System.out.println(returnString);
        }
        else{
            System.out.println("There are no items in this room.");
        }
    }

    /**
     * Checks whether a room contains a specified item.
     * @param item The item you are trying to check for.
     * @return A boolean answer to the check.
     */
    public boolean contains(Item item)
    {
        return itemsInRoom.containsValue(item);
    }

    /**
     * Removes the item object and the item string from the current room.
     * @param itemString The string representation of the item.
     * @param item The actual item object.
     */
    public void remove(String itemString, Item item)
    {
        itemsInRoom.remove(itemString, item);
    }

    /**
     * Add's a character to a specified room
     * @param character The character that is to be added to a room.
     */
    public void addCharacter(Character character)
    {
        charactersInRoom.add(character);
    }

    /**
     * Check's whether a specified character is in a room.
     * @param character The character that is being checked.
     * @return A boolean response to the check.
     * 
     */
    public boolean containsCharacter(Character character)
    {
        return charactersInRoom.contains(character);
    }

    /**
     * Checks whether a room is of a teleporter type.
     * @return A boolean evaluation to the check.
     */
    public boolean isTransporterRoom()
    {
        return isTransporter;
    }
}
