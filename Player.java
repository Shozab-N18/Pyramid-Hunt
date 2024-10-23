import java.util.Stack;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  The player class is responsible for the interaction of the player with the the rooms, items and characters.
 * 
 * @author  Shozab Anwar Siddique
 * @version 2021.12.03
 */

public class Player 
{
    private Parser parser;

    private Room currentRoom;
    private Stack<Room> stackRooms;

    private HashMap<String, Item>  stringToItem;

    private Inventory inventory;

    private Character mummy, ghost;

    private ArrayList<Room> rooms;

    private Room ghostRoom;
    /**
     * Create the game and initialise its internal map.
     */
    public Player() 
    {
        createObjects();
        parser = new Parser();
        stackRooms = new Stack<>();
        inventory = new Inventory();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createObjects()
    {
        Room pyramidCentre, cave, mummyRoom, artillery, sanctuary, testRoom, teleporter, artefact, hallOfStatues,outside,tomb,sewer,treasureRoom;

        Item boulder, statue, coffin, water, vase, bone, trap, bag, torch, treasure, tracker, diamond;

        // create the rooms
        pyramidCentre = new Room("the middle of nowhere ", false);
        cave = new Room("a dark cave full of bats", false);
        mummyRoom = new Room("the mummy's resting room", false);
        artillery = new Room("a artillery room full of ancient weapons",false);
        sanctuary = new Room("a mysterious sanctuary", false);
        teleporter = new Room("a teleporter room", true);
        artefact = new Room("a room full of ancient artefacts",false);
        hallOfStatues = new Room("the hall of statues. Each item worth millions",false);
        outside = new Room("the entrance to the pyramid", false);
        tomb = new Room("a narrow passegeway that holds a coffin resting on one of the walls", false);
        sewer = new Room("a sewer system pathway",false);
        treasureRoom = new Room("a treasure room",false);

        rooms = new ArrayList<>();

        rooms.add(pyramidCentre);
        rooms.add(cave);
        rooms.add(artillery);
        rooms.add(sanctuary);
        rooms.add(artefact);
        rooms.add(hallOfStatues);
        rooms.add(outside);
        rooms.add(tomb);
        rooms.add(sewer);
        rooms.add(treasureRoom);

        // initialise room exits
        pyramidCentre.setExit("east", cave);
        pyramidCentre.setExit("south", artillery);
        pyramidCentre.setExit("west", mummyRoom);
        pyramidCentre.setExit("north", artefact);

        cave.setExit("west", pyramidCentre);

        mummyRoom.setExit("east", pyramidCentre);
        mummyRoom.setExit("north", teleporter);

        artillery.setExit("north", pyramidCentre);
        artillery.setExit("east", sanctuary);

        teleporter.setExit("north", mummyRoom);
        sanctuary.setExit("west", artillery);
        sanctuary.setExit("down", tomb);

        artefact.setExit("south", pyramidCentre);
        artefact.setExit("up", hallOfStatues);

        hallOfStatues.setExit("down", artefact);
        hallOfStatues.setExit("east", outside);

        outside.setExit("west", hallOfStatues);

        tomb.setExit("up", sanctuary);
        tomb.setExit("west", sewer);

        sewer.setExit("east", tomb);
        sewer.setExit("north",treasureRoom);

        treasureRoom.setExit("south", sewer);

        currentRoom = outside;  // start game outside

        boulder = new Item("Boulder", "Most likely used as a trap to prevent people from \nexploring the pyramid. Looks inactive.", 100);
        water = new Item ("Water", "Water from this fountain apparently gives you the \nability to carry more weight in your inventory",0);
        statue = new Item("Statue"," A statue of a pharaoh, this may be worth a lot.",4);
        bag = new Item("Bag", "Used to carry items. I should take this.",0);
        coffin = new Item("Coffin", "Brightly painted coffin. Not what i expected it to look like.", 21);
        trap = new Item("Trap", "Seems to be used to trap ghosts?? This is some high tech equipment."
            +"\n (To trap a ghost you must give the trap to the ghost) ",2);
        torch = new Item("Torch", "Used to find my way through the dark",1);
        bone = new Item("Bone", "Remains of a skeleton in a worn out pirate costume. Creepy.", 2);
        treasure = new Item("Treasure", "A treasure chest full of gold. Hit the jackpot!",5);
        vase = new Item("Vase", "A broken vase. There might be someone in this pyramid.",0);
        tracker = new Item("Tracker", "Highly advanced tech that can track supernatural activity apparently", 5);
        diamond = new Item("diamond", "This diamond has to be one of the most valuable items "
            +"\ni have seen in all my years as an archaeologist",0);

        mummy = new Character("Mummy", "An old wrapped up mummy. Maybe i can talk with it.", false);
        ghost = new Character("Ghost", "A ghost that doesn't want anyone to get inside his pyramid",false);
        ghostRoom = treasureRoom;

        cave.addItem("boulder",boulder);
        pyramidCentre.addItem("torch",torch);
        artillery.addItem("bag",bag);
        artillery.addItem("trap",trap);
        artillery.addItem("tracker",tracker);
        sanctuary.addItem("water",water);
        tomb.addItem("coffin",coffin);
        sewer.addItem("bone",bone);
        treasureRoom.addItem("treasure",treasure);
        artefact.addItem("vase",vase);
        hallOfStatues.addItem("statue",statue);

        mummyRoom.addCharacter(mummy);

        stringToItem = new HashMap<>();

        stringToItem.put("boulder",boulder);
        stringToItem.put("water", water);
        stringToItem.put("statue",statue);
        stringToItem.put("bag",bag);
        stringToItem.put("coffin",coffin);
        stringToItem.put("trap",trap);
        stringToItem.put("torch",torch);
        stringToItem.put("bone",bone);
        stringToItem.put("vase", vase);
        stringToItem.put("treasure",treasure);
        stringToItem.put("tracker", tracker);
        stringToItem.put("diamond",diamond);

        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("The aim of the game is simple; explore the pyramid and collect all the artifacts.");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Pro Tip: Be sure to read the inspect the item before picking it up");
        System.out.println();
        look();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {

            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                look();
                break;

            case BACK:
                goBack();
                break;

            case TAKE:
                pickUp(command);
                break;

            case DROP:
                drop(command);
                break;

            case INVENTORY:
                inventory.showInventory();
                break;

            case INSPECT:
                inspect(command);
                break;
            case DRINK:
                drink(command);
                break;

            case TALK:
                talk();
                break;

            case GIVE:
                giveItem(command);
                break;

        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in the pyramid.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The user input used to determine where to go.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom.isTransporterRoom()){
            stackRooms.push(currentRoom);
            currentRoom = getRandomRoom();
            System.out.println("What happened?? Have i been teleported?");
            look();
        }
        else {
            stackRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.getItemsInRoom();
            if(currentRoom.containsCharacter(mummy)){
                mummy.printCharacterDetails(); 
                System.out.println("Type talk....");
            }
        }
    }

    /**
     * Allows the player to give an item to a character.
     * @param commmand The user input use to determine what and who to give an item to.
     */
    private void giveItem(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("What would you like to give");
            return;
        }
        else if(!command.hasThirdWord()){
            System.out.println("Who would you like to give this item to?");
            return;
        }

        String characterName = command.getThirdWord();

        String giveItem = command.getSecondWord();

        Item item = getItemFromString(giveItem);

        while(giveItem != null)
        {
            if(item == null)
            {
                System.out.println("There is no item of that name!");
            }
            else if (giveItem.equals("coffin") && characterName.equals("mummy")){
                mummy.toggleSatisfied();
                inventory.remove(giveItem,item);
                System.out.println("You have given the item '"+giveItem+"'to the "+characterName+".");
                talk();
            }
            else if (giveItem.equals("trap") && characterName.equals("ghost")){
                inventory.remove(giveItem,item);
                System.out.println("The "+characterName+" has been trapped inside the "+giveItem+".");
                System.out.println("I will leave this ghost trapping device. It could be dangerous to keep it");
            }
            else if (inventory.contains(giveItem))
            {
                System.out.println("I don't think the " + characterName + " would want that.");
            }
            else {
                System.out.println("You are not carrying an item of that name!");
            }
            return;
        }
    }

    /**
     * Allows the user go back to it's previous room location.
     */
    private void goBack()
    {
        if(stackRooms.empty()){
            System.out.println("You can't go back further!");
        }
        else{
            currentRoom = stackRooms.pop();
            look();
        }
    }

    /**
     * Allows the user to pick up items in the room that they are in if the user
     * has a bag in their inventory.
     * @param command The user input used to check what item they want to take.
     */
    private void pickUp(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("What would you like to take?");
        }

        String itemToPickUp = command.getSecondWord();

        Item item = getItemFromString(itemToPickUp);

        while(item != null && currentRoom.contains(item)){
            if(itemToPickUp.contains("tracker") && inventory.canPickUpItem(item)){
                inventory.addItem(itemToPickUp,item);
                currentRoom.remove(itemToPickUp,item);

                System.out.println(itemToPickUp + " has been added to your inventory");
                System.out.println("\nThe device says that there is a ghost in this pyramid.");
                System.out.println("It shows me where the ghost is at all times!");

                setCharacterMovement();
            }
            else if(inventory.canPickUpItem(item)){
                inventory.addItem(itemToPickUp,item);
                currentRoom.remove(itemToPickUp, item);

                System.out.println(itemToPickUp + " has been added to your inventory");
            }
            else if(!inventory.contains("bag")){
                System.out.println("I can't hold items until i find my bag!");
                return;

            }
            else{
                System.out.println("This item is too heavy for me to carry!");
            }
            return;
        }

        if(inventory.contains(itemToPickUp)){
            System.out.println("You are already carrying this item");
        }
        else{
            System.out.println("There is no such item of that name in this room!");
        }
    }

    /**
     * Allows the user to drop desired items in the room that they are in.
     * @param The user input used to check what item they want to drop.
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("What would you like to drop?");
        }

        String itemToDrop = command.getSecondWord();

        Item item = getItemFromString(itemToDrop);

        while(itemToDrop != null)
        {
            if(item == null)
            {
                System.out.println("There is no item of that name!");
            }
            else if (inventory.contains(itemToDrop))
            {
                inventory.remove(itemToDrop,item);
                currentRoom.addItem(itemToDrop,item);
                System.out.println(itemToDrop + " has been dropped from your inventory");
            }
            else 
            {
                System.out.println("You are not carrying this item!");
            }
            return;
        }
    }

    /**
     * Allows the user to display the item descriptions of a specific item.
     * @param command The user input used to check what item they want to inspect.
     */
    private void inspect(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("What would you like to inspect?");
        }

        String inspectItem = command.getSecondWord();

        Item item = getItemFromString(inspectItem);

        boolean canInspect = inventory.contains(inspectItem) || currentRoom.contains(item);

        if(item == null)
        {
            System.out.println("There is no item of that name!");
        }
        else if (canInspect == true){
            item.printItemDetails();
        }
    }

    /**
     * Allows the user to drink specific items only.
     * @param The user input used to check what item they want to drink.
     */
    private void drink(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("What would you like to drink?");
        }

        String itemName = command.getSecondWord();

        Item item = getItemFromString(itemName);

        boolean canDrinkItem = inventory.contains(itemName) || currentRoom.contains(item);

        while(item != null && canDrinkItem)
        {
            if(itemName.equals("water"))
            {
                inventory.increaseCapacity();
                currentRoom.remove(itemName, item);
                inventory.remove(itemName,item);
                System.out.println("You can now carry a total of 35 weight!");
            }
            else
            {
                System.out.println("I can't drink that!");
            }
            return;
        }

        if(item == null)
        {
            System.out.println("There is no item of that name!");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Display's what room the user is currently in and the items that
     * are present in that room.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
        currentRoom.getItemsInRoom();
    }

    /**
     * Return's the item object from its respective string.
     * @param item The string of the item.
     * @return The item object from the string.
     */
    private Item getItemFromString(String item)
    {
        return stringToItem.get(item);   // Used to prevent the use of additional item fields.
    }

    /**
     * Simulates speech with the character mummy.
     */
    private void talk()
    {
        while(currentRoom.containsCharacter(mummy)){
            if(mummy.isSatisfied()){
                System.out.println("Thanks. Well i gave my word so i'll give you the diamond."
                    +"\n\nYou received a new item!");
                inventory.addItem("diamond",getItemFromString("diamond"));
                getItemFromString("diamond").printItemDetails();
                return;
            }
            else if(!inventory.contains("coffin")){
                System.out.println("\nWhat are you here to do, take the riches of the pyramid?. "
                    +"\nIt just happened to be that i am currently in possesion of the most"
                    +"\nvaluable item in the pyramid, this massive diamond. You won't get it from me."
                    +"\n\nHowever if you can find my coffin, i might consider letting you in."
                    +"\nCoffin's tend to be heavy so you might to find the magic "
                    +"\nwater fountain of youth that gives you super strenght "
                    +"and makes you look younger."
                    +"\n\nGo north to get teleported. If you're lucky you'll get closer to the coffin.");
                return;
            }
            System.out.println("I see you found my coffin. Give me the coffin if you want the diamond.");       
            return;
        }
        System.out.println("There is no one to talk to in this room!");
    }

    /**
     * End's the game after all the valuable items have been collected.
     * @return A boolean response of whether all the items have been collected.
     */
    public boolean winGame()
    {
        if((inventory.getSize() == (stringToItem.size()-4) && !inventory.contains("coffin") && !inventory.contains("tracker")) ){
            return true;
        }
        return false;
    }

    /**
     * Generates a random room from a collection of rooms.
     * @return The randomly selected room.
     */
    public Room getRandomRoom()
    {
        Random roomIndex = new Random();
        int numberOfRooms = rooms.size();
        return rooms.get(roomIndex.nextInt(numberOfRooms));
    }

    /**
     * Makes a particular character move in a randomly generated neighbouring room
     * after at set time intervals.
     */
    private void setCharacterMovement()
    {
        Timer timer = new Timer();
        if (inventory.contains("tracker")){
            timer.scheduleAtFixedRate(new TimerTask() { 
                    @Override  
                    public void run() {
                        ghostRoom = ghostRoom.getRandomExit();
                        System.out.println("The ghost has moved to " + ghostRoom.getShortDescription());
                        if(currentRoom == ghostRoom){
                            System.out.println("A ghost has appeared in this room!");
                            System.out.println("(To trap a ghost you must give the trap to the ghost)");
                            System.out.println("\n   # Capture ghost if you have the item 'trap'");
                            System.out.println("   # Change room's and come back when you have the item 'trap' !! ");
                            timer.cancel();
                        }
                    }
                }, 0, 20000);
        }
    }
}