package com.amdocs.marsproject;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link Rover} entity
 */
public class RoverTest
{
    private static Plateau myPlateau;

    /**
     * Initialize the {@link Plateau} to be used by the following tests
     */
    @BeforeClass
    public static void setup()
    {
        myPlateau = new Plateau(5, 5);
    }

    /**
     * Test if the correct message format is returned when request the Rover's current location
     */
    @Test
    public void testGetActualLocation()
    {
        Rover myRover = new Rover(0,0, Direction.N, myPlateau);
        String expectedMessage = "0 0 N";
        String actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                expectedMessage, actualMessage);
    }

    /**
     * test if Rover will reach the desired position when moving straight
     */
    @Test
    public void testWhenMovingStraightWithValidInputs()
    {
        Rover myRover = new Rover(0, 0, Direction.N, myPlateau);
            myRover.sendCommands("MM");
        String expectedMessage = "0 2 N";
        String actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                expectedMessage, actualMessage);
    }

    /**
     * Test if Rover, when turning left, will face the proper {@link Direction}
     */
    @Test
    public void testIfRoverCanSpinLeft()
    {
        Rover myRover = new Rover(0, 0, Direction.N, myPlateau);
        String expectedMessage = "0 0 %s";
        String actualMessage = null;

        // turn 90 degrees CCW from starter point
        myRover.sendCommands("L");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "W"), actualMessage);

        // turn 180 degrees CCW from starter point
        myRover.sendCommands("L");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "S"), actualMessage);

        // turn 270 degrees CCW from starter point
        myRover.sendCommands("L");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "E"), actualMessage);

        // turn 360 degrees CCW from starter point
        myRover.sendCommands("L");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "N"), actualMessage);
    }

    /**
     * Test if Rover, when turning right, will face the proper {@link Direction}
     */
    @Test
    public void testIfRoverCanSpinRight()
    {
        Rover myRover = new Rover(0, 0, Direction.N, myPlateau);
        String expectedMessage = "0 0 %s";
        String actualMessage = null;

        // turn 90 degrees CW from starter point
        myRover.sendCommands("R");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "E"), actualMessage);

        // turn 180 degrees CW from starter point
        myRover.sendCommands("R");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "S"), actualMessage);

        // turn 270 degrees CW from starter point
        myRover.sendCommands("R");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "W"), actualMessage);

        // turn 360 degrees CW from starter point
        myRover.sendCommands("R");
        actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                String.format(expectedMessage, "N"), actualMessage);
    }

    /**
     * Test if a {@link RuntimeException} is thrown when the Rover move outside the {@link Plateau}'s size
     */
    @Test(expected = RuntimeException.class)
    public void testIfRoverCanMoveOutsideThePlateauWithPositiveBoundaries()
    {
        Rover myRover = new Rover(5,5, Direction.E, myPlateau);
        myRover.sendCommands("m");
    }

    /**
     * Test if a {@link RuntimeException} is thrown when the Rover move outside the {@link Plateau}'s size
     */
    @Test(expected = RuntimeException.class)
    public void testIfRoverCanMoveOutsideThePlateauWithNegativeBoundaries()
    {
        Rover myRover = new Rover(0,0, Direction.S, myPlateau);
        myRover.sendCommands("m");
    }

    /**
     * Test if Rover process lowercase commands without case-sensitiveness
     */
    @Test
    public void testIfRoverProcessLowerCaseCommands()
    {
        Rover myRover = new Rover(2,2, Direction.N, myPlateau);
        myRover.sendCommands("lmlmmr");
        String expectedMessage = "1 0 W";
        String actualMessage = myRover.getLocation();

        assertEquals(String.format("Expected message was %s but received %s", expectedMessage, actualMessage),
                expectedMessage, actualMessage);
    }

    /**
     * Test if Rover throws a {@link RuntimeException} when an unknown command is sent
     */
    @Test(expected = RuntimeException.class)
    public void testIfRoverFailsWhenUnknownCommandIsSent()
    {
        Rover myRover = new Rover(0,0, Direction.N, myPlateau);
        myRover.sendCommands("t");
    }
}