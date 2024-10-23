/**
 * List of commands for the game
 *
 * @author Shozab Anwar Siddique K21054573
 * @version 2021.12.03
 */
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?") ,LOOK("look"),
    BACK("back"), DROP("drop"),TAKE("take"),INVENTORY("inventory"),
    INSPECT("inspect"),DRINK("drink"),TALK("talk"),GIVE("give");
    
    private String commandString;
    
    /**
     * Initialise with the command string.
     * @param commandString The input command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * Converts to string.
     * @return command word to string.
     */
    public String toString()
    {
        return commandString;
    }
}
