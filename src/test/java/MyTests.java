import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTests {

    
    @Test
    public void testFindWord() {
        Magpie x = new Magpie();
        assertEquals(-1, x.findWord("I know all the state capitals", "no"), "Looking for the world \"no\" in the String \"I know all the state capitals\" should return: " + "-1");
        assertEquals(7, x.findWord("I have no pineapples", "no"), "Looking for the world \"no\" in the String \"I have no pineapples\" should return: " + "7");
        assertEquals(0, x.findWord("No way!", "no"), "Looking for the world \"no\" in the String \"No way!\" should return: " + "0");
        assertEquals(14, x.findWord("The answer is no", "no"), "Looking for the world \"no\" in the String \"The answer is no\" should return: " + "14");
    }

    @Test
    public void testTransformIWantStatement() {
        Magpie x = new Magpie();
        assertEquals("Would you really be happy if you had apples?", x.transformIWantStatement("I want apples"), "Expected output: " + "Would you really be happy if you had apples?");
    }

    @Test
    public void testTransformIYouStatement() {
        Magpie x = new Magpie();
        assertEquals("Why do you like me?", x.transformIYouStatement("I like you"), "Expected output: " + "Why do you like me?");
    }

    @Test
    public void testTransformIWantToStatement() {
        Magpie x = new Magpie();
        assertEquals("What would it mean to eat lunch?", x.transformIWantToStatement("I want to eat lunch"), "Expected output: " + "What would it mean to eat lunch?");
    }

    @Test
    public void testTransformYouMeStatement() {
        Magpie x = new Magpie();
        assertEquals("What makes you think that I like you?", x.transformYouMeStatement("Do you like me"), "Expected output: " + "What makes you think that I like you?");
    }
}
