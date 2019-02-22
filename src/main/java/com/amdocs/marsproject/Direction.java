package com.amdocs.marsproject;

/**
 * Represents all the directions where a {@link Rover} can face
 */
public enum Direction
{
    N (0),
    W (1),
    S (2),
    E (3);

    private final int value;

    Direction(int pValue)
    {
        value = pValue;
    }

    /**
     * Get a direction based on its given value, performing a validation before returning the proper direction
     * @param pValue the value of the wanted direction
     * @return the wanted direction
     */
    public static Direction getDirectionWithValue(int pValue)
    {
        if (pValue >= Direction.values().length) pValue = 0;
        if (pValue < 0) pValue = Direction.values().length - 1;
        return Direction.values()[pValue];
    }

    /**
     * The value of the defined direction
     * @return the value of the direction
     */
    public int getValue()
    {
        return value;
    }
}