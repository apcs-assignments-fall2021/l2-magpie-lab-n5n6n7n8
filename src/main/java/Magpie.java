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
        int r = (int) (Math.random()*3);
        if(r==0)
        {
            return "Hello, let's talk.";
        }
        else if(r==1)
        {
            return "Hi. How are you?";
        }
        else if(r==2)
        {
            return "Greetings. Tell me about your favorite things.";
        }
        return "Hello. What are your plans for today?";
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
        else if(findWord(statement, "I want to")>=0) {
            response = transformIWantToStatement(statement).trim();
        }
        else if(findWord(statement, "I want")>=0) {
            response = transformIWantStatement(statement).trim();
        }
        else if(findWord(statement, "I feel")>=0||findWord(statement, "I am feeling")>=0)
        {
            response = transformIFeelStatement(statement).trim();
        }
        else if(findWord(statement, "I ")>=0&&findWord(statement, "you")>=0&&(findWord(statement, "I ")<findWord(statement, "you"))) {
            response = transformIYouStatement(statement).trim();
        }
        else if(findWord(statement, "You ")>=0&&findWord(statement, "me")>=0&&(findWord(statement, "You ")<findWord(statement, "me"))) {
            response = transformYouMeStatement(statement).trim();
        }
        else if(findWord(statement, "cat")>=0||findWord(statement, "dog")>=0) {
            response = "Tell me more about your pets";
        }
        else if(findWord(statement, "Mr.")>=0||findWord(statement, "Mrs.")>=0||findWord(statement, "Ms.")>=0) {
            response = "Your teacher sounds interesting, tell me more.";
        }
        else if(findWord(statement, "ice cream")>=0) {
            response = "My favorite ice cream flavor is vanilla. What other foods do you like?";
        }
        else if(findWord(statement, "book")>=0) {
            response = "My favorite books are from the Hunger Games series. Which literature teachers were the most fun during school?";
        }
        else if(findWord(statement, "music")>=0) {
            response = "Speaking of music, I like to listen to classic rock and Rnb. How much do you know about celebrities?";
        }
        else if(findWord(statement, "sport")>=0) {
            response = "Speaking of sports, what do you think of basketball?";
        }
        else if(findWord(statement, "video game")>=0) {
            response = "Cool, I like to play Tetris but only on weekends. Do you play any sports?";
        }
        else if(statement.trim().equals("")) {
            response = "Hello? Are you there?";
        }
        else if(statement.length()==1)
        {
            response = "Come again?";
        }
        else if(statement.equalsIgnoreCase("OK"))
        {
            response = "Tell me more please.";
        }
        else if(statement.length()==2)
        {
            response = "I don't understand.";
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
        final int NUMBER_OF_RESPONSES = 6;
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
        else if (whichResponse == 4)
        {
            response = "Elaborate please.";
        }
        else if (whichResponse == 5)
        {
            response = "By the way, what do you do in your free time?";
        }
        return response;
    }
    public int findWord(String str, String word) { //for this to work, we need to make sure the word is not surrounded by letters
        str = str.toUpperCase(); //You mesmerize me
        word = word.toUpperCase().trim();
        Boolean rclear=false; //checks right side of str for letters
        Boolean lclear=false; //checks left side of str for letters
        int index = str.indexOf(word);
        Boolean repeating=true;
        if (index==-1) { //false
            return -1;
        }
        while(repeating) {
            if (index == 0) //is word at the very beginning? or is the character in front of word not a letter?
            {
                lclear = true;
            } else if (!Character.isLetter(str.charAt(index - 1))) {
                lclear = true;
            }
            if (index == str.length() - word.length()) //is word at very end? or is the character to the right of the str not a lettter
            {
                rclear = true;
            } else if (!Character.isLetter(str.charAt(index + word.length()))) {
                rclear = true;
            }
            if (rclear && lclear) //left side AND right side must both be clear of letters to return index
            {
                return index;
            }
            //If this point is reached, the initial word variable is not in str and COULD be somewhere later in str
            if(str.indexOf(word, index+1)==-1) //are there any more instances of word in string from this point on
            {
                repeating=false;
            }
            else {
                index = str.indexOf(word, index + 1);
            }

        }
        return- 1;
    }
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
        int index2 = findWord(statement, "you");
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
        int index = findWord(statement, "I want to");
        return "What would it mean to" + statement.substring(index+9) + "?";
    }

    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        int index = findWord(statement, "you");
        int index2 = findWord(statement, "me");
        return "What makes you think that I" + statement.substring(index+3, index2) + "you?";
    }

    public String transformIFeelStatement(String statement)
    {
        int index = findWord(statement, "I feel");
        int toAdd = 6;
        if(index==-1)
        {
            index=findWord(statement, "I am feeling");
            toAdd = 12;
        }
        double random = Math.random()*4;
        int r = (int) random;
        String location="";
        if(r==0)
        {
            location="on vacations";
        }
        else if(r==1)
        {
            location="after school";
        }
        else if(r==2)
        {
            location="on weekends";
        }
        else if(r==3)
        {
            location="after listening to my favorite song";
        }
        return "I can relate. I also feel" + statement.substring(index+toAdd) + ", but especially " + location;
    }
}
