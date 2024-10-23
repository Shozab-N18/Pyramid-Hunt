
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2021.12.03
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * 
     */
    public static void main (String[] args){
        Game game = new Game();
        game.play();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        //printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            if(player.winGame() == true){
                finished = player.winGame();
                System.out.println("Congratulations, you have completed the game!!");
            }
            else{
                Command command = parser.getCommand();
                finished = player.processCommand(command);
            }
        }
        System.out.println("Thank you for playing. Good bye.");
    }
}