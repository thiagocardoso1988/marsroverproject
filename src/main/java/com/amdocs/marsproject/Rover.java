package com.amdocs.marsproject;

import com.amdocs.marsproject.exceptions.RoverOutOfBoundsException;

/**
 * A robot sent to Mars to perform an interplanetary researchs, which can be commanded from Earth after its landing
 * in the desired position into Mars' plateau
 */
public class Rover
{
    private int posX;
    private int posY;
    private Direction direction;
    private Plateau plateau;

    /**
     * Create a new Rover in the {@link Plateau} and place it in the desired position, facing the passed {@link Direction}
     * @param pPosX the column where the Rover is on
     * @param pPosY the line where the Rover is on
     * @param pDirection the {@link Direction} where the Rover is looking at
     * @param pPlateau the {@link Plateau} where the Rover is on
     */
    public Rover(int pPosX, int pPosY, Direction pDirection, Plateau pPlateau)
    {
        posX = pPosX;
        posY = pPosY;
        direction = pDirection;
        plateau = pPlateau;
    }

    /**
     * Turn the Rover to the passed side
     * @param pRotation side to where the Rover shall look (left if equals "L", right otherwise)
     */
    private void turn(String pRotation)
    {
        if (pRotation.equalsIgnoreCase("L"))
        {
            direction = Direction.getDirectionWithValue(direction.getValue() + 1);
        }
        else
        {
            direction = Direction.getDirectionWithValue(direction.getValue() - 1);
        }
    }

    /**
     * Step the Rover one square toward its actual direction and validate its position toward the {@link Plateau}'s size
     */
    private void move()
    {
        switch (direction)
        {
            case N:
                posY++;
                break;
            case W:
                posX--;
                break;
            case S:
                posY--;
                break;
            case E:
                posX++;
                break;
        }
        if (!plateau.validateLocation(posX, posY))
        {
          String errorMsg = "Rover is out of the plateau's boundaries (Plateau width: %d, height: %d - " +
                  "Rover Position X: %d, Y: %d)";
          throw new RoverOutOfBoundsException(String.format(errorMsg, plateau.getWidth(), plateau.getHeight(), posX, posY));
        };
    }

    /**
     * Process a sequence of characters telling what the Rover shall do.
     * Valid inputs:
     * - L -> Turn to its left side
     * - R -> Turn to its right side
     * - M -> Move 1 square ahead (based on the {@link Direction} it is looking to)
     * Any other command will raise a {@link IllegalArgumentException }
     * @param pCommands commands sequence to be processed
     */
    public void sendCommands(String pCommands)
    {
        for (char command : pCommands.toCharArray())
        {
            switch (Character.toUpperCase(command))
            {
                case 'L':
                case 'R':
                    turn(String.valueOf(command));
                    break;
                case 'M':
                    move();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown command was sent: %c", command));
            }
        }
        System.out.println(getLocation());
    }

    /**
     * Return the Rover's current location and the {@link Direction} it is facing.
     */
    public String getLocation()
    {
        String messageTemplate = "%d %d %s";
        return String.format(messageTemplate, posX, posY, direction);
    }
}