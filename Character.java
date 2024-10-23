/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    private String name;
    private String characterDescription;
    private boolean isSatisfied;
    /**
     * Constructor for objects of class Item
     */
    public Character(String name, String characterDescription, boolean isSatisfied)
    {
        this.name = name;
        this.characterDescription = characterDescription;
        this.isSatisfied = isSatisfied;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void printCharacterDetails()
    {
        System.out.println("There is a " + name + " in this room. "+ characterDescription); 
    }

    /**
     * 
     */
    public void toggleSatisfied()
    {
        isSatisfied = !isSatisfied;
    }
    
    /**
     * 
     */
    public boolean isSatisfied(){
        return isSatisfied;
    }
}
