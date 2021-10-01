import java.util.Locale;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response;
        if (findWord(statement,"no")>= 0)
        {
            response = "Why not?";
        }
        else if (findWord(statement, "mother") >= 0
                || findWord(statement, "father") >= 0
                || findWord(statement, "sister") >= 0
                || findWord(statement, "brother") >= 0) {
            response = "How is your family doing?";
        }
        else if(findWord(statement, "I want")>=0) {
            response = transformIWantStatement(statement).trim();
        }
        else if(findWord(statement, "I ")<findWord(statement, "you")) {
            response = transformIYouStatement(statement).trim();
        }
        else if(findWord(statement, "cat")>=0||findWord(statement, "dog")>=0) {
            response = "Tell me more about your pets";
        }
        else if(findWord(statement, "Mr.")>=0||findWord(statement, "Mrs.")>=0||findWord(statement, "Ms.")>=0) {
            response = "Your teacher sounds interesting, tell me more.";
        }
        else if(findWord(statement, "ice cream")>=0) {
            response = "My favorite ice cream flavor is vanilla.";
        }
        else if(findWord(statement, "video game")>=0) {
            response = "Cool, I like to play Tetris but only on weekends.";
        }
        else if(statement.trim().equals("")) {
            response = "Hello? Are you there?";
        }
        else {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        return response;
    }
    public int findWord(String str, String word) { //for this to work, we need to make sure the word is not surrounded by letters
        str = str.toUpperCase();
        word = word.toUpperCase();
        word = word.trim();
        int index = str.indexOf(word);
        if(index==-1)
        {
            return -1;
        }
        Boolean rclear=false; //checks right side of str for letters
        Boolean lclear=false; //checks left side of str for letters
        if(index==0) //is word at the very beginning? or is the character in front of word not a letter?
        {
            lclear=true;
        }
        else if(!Character.isLetter(str.charAt(index-1)))
        {
            lclear=true;
        }
        if(index==str.length()-word.length()) //is word at very end? or is the character to the right of the str not a lettter
        {
            rclear=true;
        }
        else if(!Character.isLetter(str.charAt(index+word.length())))
        {
            rclear=true;
        }
        if(rclear&&lclear) //left side AND right side must both be clear of letters to return index
        {
            return index;
        }
        return -1;
    }
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        int index = findWord(statement, "I want ");
        return "Would you really be happy if you had " + statement.substring(index+7) + "?";
    }
    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        int index = findWord(statement, "I");
        int index2 = statement.indexOf("you", index);
        return "Why do you " + statement.substring(index+2, index2) + "me?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {
        // your code here
        return "";
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        // your code here
        return "";
    }
}
